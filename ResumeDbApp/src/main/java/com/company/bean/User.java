package com.company.bean;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
}