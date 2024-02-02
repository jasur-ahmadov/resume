package com.company.dto;

import java.util.ArrayList;
import java.util.List;

import com.company.entity.User;
import com.company.entity.UserSkill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;
    private String name;
    private String surname;
    private String password;
    private List<UserSkillDTO> skills;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.password = user.getPassword();
        List<UserSkill> list1 = user.getUserSkills();
        List<UserSkillDTO> list2 = new ArrayList<>();
        for (UserSkill us : list1) {
            list2.add(new UserSkillDTO(us));
        }
        this.skills = list2;
    }
}