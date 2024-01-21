package com.company.dao.inter;

import java.util.List;

import com.company.entity.Skill;

public interface SkillDaoInter {

    List<Skill> getAll();

    boolean insertSkill(Skill skill);

    Skill getById(int id);

    boolean updateSkill(Skill u);

    boolean removeSkill(int id);

    List<Skill> getByName(String name);
}