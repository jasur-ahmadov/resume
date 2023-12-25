package com.company.dao.impl;

import com.company.entity.*;
import com.company.dao.inter.*;
import java.sql.*;
import java.util.*;
import java.sql.Date;

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
            if (u.getBirthDate() != null) {
                ps.setDate(7, u.getBirthDate());
            }
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

    @Override
    public boolean addUser(User u) {
        try ( Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("insert into user(name, surname, "
                    + " email, phone, profile_description, address, "
                    + " birthdate, birthplace_id, nationality_id ) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPhone());
            ps.setString(5, u.getProfileDesc());
            ps.setString(6, u.getAddress());
            ps.setDate(7, u.getBirthDate());
            ps.setInt(8, u.getBirthPlace().getId());
            ps.setInt(9, u.getNationality().getId());
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

    @Override
    public User findByEmail(String email) {
        User log = null;
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT *,n.name as nationality_name,c.name as country_name FROM user u"
                    + "  LEFT JOIN country c "
                    + "  ON u.birthplace_id = c.id "
                    + "  LEFT JOIN country n "
                    + "  ON u.nationality_id = n.id "
                    + "  WHERE u.email = ?");
            stmt.setString(1, email);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                log = getUser(rs);
            }
        } catch (Exception ex) {
            System.err.println("ops, we have a problem");
        }
        return log;
    }
}