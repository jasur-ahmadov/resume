package com.company.dto;

import java.util.ArrayList;
import java.util.List;

import com.company.entity.User;
import com.company.entity.UserSkill;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int id;
    private String name;
    private String surname;
    private List<UserSkillDTO> skills;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        List<UserSkill> list1 = user.getUserSkills();
        List<UserSkillDTO> list2 = new ArrayList<>();
        for(UserSkill us : list1){
            list2.add(new UserSkillDTO(us));
        }
        this.skills = list2;
    }

    public List<UserSkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkillDTO> skills) {
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}