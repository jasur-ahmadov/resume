package com.company.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {

    private int id;
    private String name;
    private String nationalityName;
}