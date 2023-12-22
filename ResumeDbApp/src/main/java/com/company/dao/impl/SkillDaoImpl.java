package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.dao.inter.*;
import java.sql.*;
import java.util.*;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public List<Skill> getAll() {

        List<Skill> skills = new ArrayList<>();
        try ( Connection c = getConnection()) {
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
}