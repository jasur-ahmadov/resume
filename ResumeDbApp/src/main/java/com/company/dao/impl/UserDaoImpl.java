package com.company.dao.impl;

import com.company.entity.*;
import com.company.dao.inter.*;
import java.sql.*;
import java.util.*;
import java.sql.Date;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Verifyer;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try ( Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "	u.*, "
                    + "	n.nationality_name AS nationality, "
                    + "	c.NAME AS birthplace "
                    + "FROM "
                    + "	USER u "
                    + "	LEFT JOIN country n ON u.nationality_id = n.id "
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id ");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                users.add(getUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByEmailAndPassword(String email, String password){
        User user = null;
        try(Connection c = getConnection()){
            PreparedStatement stmt = c.prepareStatement("select * from user where email= ? and password= ? ");
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                user = getUserSimple(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> users = new ArrayList<>();
        try ( Connection c = getConnection()) {

            String sql = "SELECT "
                    + "	u.*, "
                    + "	n.nationality_name AS nationality, "
                    + "	c.NAME AS birthplace "
                    + "FROM "
                    + "	USER u "
                    + "	LEFT JOIN country n ON u.nationality_id = n.id "
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where 1=1 ";

            if(name!=null && !name.trim().isEmpty()){
                sql+=" and u.name = ? ";
            }

            if(surname!=null && !surname.trim().isEmpty()){
                sql+=" and u.surname = ? ";
            }

            if(nationalityId!=null){
                sql+=" and u.nationality_id = ? ";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i=1;
            if(name!=null && !name.trim().isEmpty()){
                stmt.setString(i, name);
                i++;
            }

            if(surname!=null && !surname.trim().isEmpty()){
                stmt.setString(i, surname);
                i++;
            }

            if(nationalityId!=null){
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                users.add(getUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateUser(User u) {
        try ( Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE USER "
                    + "	SET NAME = ?, "
                    + "	surname = ?, "
                    + "	email = ?, "
                    + "	phone = ?, "
                    + "	profile_description = ?, "
                    + "	address = ?, "
                    + "	birthdate = ?, "
                    + "	birthplace_id = ?, "
                    + "	nationality_id = ? WHERE id = ?");

            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPhone());
            ps.setString(5, u.getProfileDesc());
            ps.setString(6, u.getAddress());
            ps.setDate(7, u.getBirthDate());
            ps.setInt(8, u.getBirthPlace().getId());
            ps.setInt(9, u.getNationality().getId());
            ps.setInt(10, u.getId());
            return ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try ( Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("delete from user where id = ?");
            ps.setInt(1, id);
            return ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User user = null;
        try ( Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "	u.*, "
                    + "	n.nationality_name AS nationality, "
                    + "	c.NAME AS birthplace "
                    + "FROM "
                    + "	USER u "
                    + "	LEFT JOIN country n ON u.nationality_id = n.id "
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where u.id = " + userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                user = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    BCrypt.Hasher myVerifyer = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        try ( Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("insert into user(name, surname, "
                    + " email, phone, profile_description, address, "
                    + " birthdate, birthplace_id, nationality_id, password ) values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPhone());
            ps.setString(5, u.getProfileDesc());
            ps.setString(6, u.getAddress());
            ps.setDate(7, u.getBirthDate());
            ps.setInt(8, u.getBirthPlace().getId());
            ps.setInt(9, u.getNationality().getId());
            ps.setString(10, myVerifyer.hashToString(4, u.getPassword().toCharArray()));
            return ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String description = rs.getString("profile_description");
        String address = rs.getString("address");
        Date birthDate = rs.getDate("birthdate");
        int birthPlaceId = rs.getInt("birthplace_id");
        int nationalityId = rs.getInt("nationality_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthplace");
        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthPlaceId, birthPlaceStr, null);
        return new User(id, name, surname, email, phone, description, address, birthDate, birthPlace, nationality);
    }

    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String description = rs.getString("profile_description");
        String address = rs.getString("address");
        Date birthDate = rs.getDate("birthdate");
        String password = rs.getString("password");
        User user = new User(id, name, surname, email, phone, description, address, birthDate, null, null);
        user.setPassword(password);
        return user;
    }

    @Override
    public User findByEmail(String email){
        User user = null;
        try(Connection c = getConnection()){
            PreparedStatement stmt = c.prepareStatement(
                    "select * from user where email= ? ");
            stmt.setString(1, email);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                user = getUserSimple(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
}