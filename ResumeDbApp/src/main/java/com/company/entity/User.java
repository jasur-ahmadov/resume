package com.company.entity;

import java.sql.Date;
import java.util.List;
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
    private String profileDesc;
    private String address;
    private Date birthDate;
    private Country birthPlace;
    private Country nationality;
    private List<UserSkill> userSkills;

    public User(int id, String name, String surname, String email, String phone, String profileDesc, String address, Date birthDate, Country birthPlace, Country nationality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.profileDesc = profileDesc;
        this.address = address;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
    }

    public User(int id) {
        this.id = id;
    }
}