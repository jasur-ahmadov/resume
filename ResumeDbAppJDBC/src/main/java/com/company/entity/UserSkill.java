package com.company.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSkill {

    private Integer id;
    private User user;
    private Skill skill;
    private int power;
}