package com.company.dto;

import com.company.entity.UserSkill;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSkillDTO {

    private Integer id;
    private int power;
    private SkillDTO skill;

    public UserSkillDTO(UserSkill userSkill) {
        this.id = userSkill.getId();
        this.power = userSkill.getPower();
        this.skill = new SkillDTO(userSkill.getSkill());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public SkillDTO getSkill() {
        return skill;
    }

    public void setSkill(SkillDTO skill) {
        this.skill = skill;
    }
}