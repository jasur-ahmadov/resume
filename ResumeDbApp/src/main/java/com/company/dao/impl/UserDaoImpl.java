package com.company.dao.impl;

import com.company.bean.User;
import com.company.dao.inter.*;
import java.sql.*;
import java.util.*;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try ( Connection c = getConnection()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from user");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                users.add(new User(id, name, surname, email, phone));
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
            Statement stmt = c.createStatement();
            stmt.execute("select * from user where id = " + userId);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                user = new User(id, name, surname, email, phone);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
}