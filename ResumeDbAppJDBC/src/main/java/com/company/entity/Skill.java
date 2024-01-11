package com.company.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    private int id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}