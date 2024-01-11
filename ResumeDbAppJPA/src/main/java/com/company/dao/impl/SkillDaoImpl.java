package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.dao.inter.*;
import java.sql.*;
import java.util.*;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        try (Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("select * from skill");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                skills.add(new Skill(id, name));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return skills;
    }

    @Override
    public boolean insertSkill(Skill skill) {
        try (Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("insert into skill(name) values (?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skill.getName());
            boolean b = stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            while (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            }
            return b;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Skill getById(int userId) {
        Skill skill = null;
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM skill WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                skill = new Skill(id, name);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return skill;
    }

    @Override
    public boolean updateSkill(Skill u) {
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE skill SET name=? WHERE id= ?");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean removeSkill(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM skill WHERE id=?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public List<Skill> getByName(String sname) {
        List<Skill> list = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM skill WHERE name LIKE ?");
            stmt.setString(1, sname);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new Skill(id, name));
            }
        } catch (Exception ex) {
            System.err.println("Ops, we have a problem");
        }
        return list;
    }
}