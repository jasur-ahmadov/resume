package com.company.dao.inter;

import java.util.List;

import com.company.entity.UserSkill;

public interface UserSkillDaoInter {

    List<UserSkill> getAllSkillByUserId(int userId);

    boolean insertUserSkill(UserSkill u);

    boolean updateUserSkill(UserSkill u);

    boolean removeUserSkill(int id);
}