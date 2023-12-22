package com.company.main;

import com.company.bean.User;
import com.company.dao.inter.UserDaoInter;

public class Main {

    public static void main(String[] args) {

        UserDaoInter userDao = Context.instanceUserDao(); // tightly/loosely coupling
        User user = new User(0, "Sarkhan", "Rasullu", "sarkhanrasullu@gmail.com", "+994702333322");
        userDao.addUser(user);
    }
}