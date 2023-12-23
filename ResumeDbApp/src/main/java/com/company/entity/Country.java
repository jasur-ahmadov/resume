package com.company.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private int id;
    private String name;
    private String nationalityName;

    @Override
    public String toString() {
        return name + "{" + nationalityName + "}";
    }
}