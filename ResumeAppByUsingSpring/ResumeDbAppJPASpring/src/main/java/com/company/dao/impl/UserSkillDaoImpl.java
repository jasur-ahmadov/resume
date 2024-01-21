package com.company.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> userskills = new ArrayList<>();
        try (Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "SELECT " + "	us.id as userSkillId, " + "	u.*, " + "	s.id AS skill_id, "
                            + "	s.NAME AS skill_name, " + "	power " + "FROM " + "	user_skill us "
                            + "	LEFT JOIN USER u ON us.user_id = u.id " + "	LEFT JOIN skill s ON us.skill_id = s.id "
                            + "WHERE " + "	u.id = ? ");
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
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");
        User user = new User(userId);
        Skill skill = new Skill(skillId, skillName);
//        return new UserSkill(userSkillId, user, skill, power);
        return null;
    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO user_skill (skill_id , user_id ,power) VALUES (?, ?, ?)");
            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());
            return stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE user_skill SET skill_id = ?, user_id = ?, power = ?  WHERE id = ?");
            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());
            stmt.setInt(4, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean removeUserSkill(int id) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_skill WHERE ID=?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
}