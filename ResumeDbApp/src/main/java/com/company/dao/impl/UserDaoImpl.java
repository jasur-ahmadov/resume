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
            PreparedStatement ps = c.prepareStatement("update user set name = ?, surname = ?, email = ?, phone = ? where id = ?");
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPhone());
            ps.setInt(5, u.getId());
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
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where id = " + userId);
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
            PreparedStatement ps = c.prepareStatement("insert into user(name, surname, email, phone) values(?,?,?,?)");
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPhone());
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
}