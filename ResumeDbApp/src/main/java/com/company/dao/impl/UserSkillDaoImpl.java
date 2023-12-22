package com.company.dao.impl;

import com.company.dao.inter.*;
import com.company.entity.*;
import java.sql.*;
import java.util.*;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> userskills = new ArrayList<>();
        try ( Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "	u.*, "
                    + "	s.id AS skill_id, "
                    + "	s.NAME AS skill_name, "
                    + "	power "
                    + "FROM "
                    + "	user_skill us "
                    + "	LEFT JOIN USER u ON us.user_id = u.id "
                    + "	LEFT JOIN skill s ON us.skill_id = s.id "
                    + "WHERE "
                    + "	u.id = ? ");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                userskills.add(getUserSkill(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userskills;
    }

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");
        User user = new User(userId);
        Skill skill = new Skill(skillId, skillName);
        return new UserSkill(null, user, skill, power);
    }
}