package com.company.dao.inter;

import com.company.entity.Skill;
import java.util.List;

public interface SkillDaoInter {

    public List<Skill> getAll();

    public boolean insertSkill(Skill skill);

    public Skill getById(int id);

    public boolean updateSkill(Skill u);

    public boolean removeSkill(int id);

    public List<Skill> getByName(String name);
}