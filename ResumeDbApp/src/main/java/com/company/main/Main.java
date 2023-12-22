package com.company.main;

import com.company.bean.User;
import com.company.dao.inter.UserDaoInter;

public class Main {

    public static void main(String[] args) {

        UserDaoInter userDao = Context.instanceUserDao(); // tightly/loosely coupling
        User user = userDao.getById(3);
        user.setName("Marry");
        userDao.updateUser(user);
    }
}