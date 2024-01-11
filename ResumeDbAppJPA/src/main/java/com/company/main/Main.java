package com.company.main;

import com.company.dao.inter.UserDaoInter;

public abstract class Main {

    public static void main(String[] args) throws IllegalArgumentException {

        UserDaoInter userDao = Context.instanceUserDao();
        System.out.println(userDao.findByEmail("cesurehmedov594@gmail.com").getName());
    }
}