package com.company.main;

import com.company.dao.inter.*;

public class Main {

    public static void main(String[] args) throws Exception {

        EmploymentHistoryDaoInter userDao = Context.instanceEmploymentHistoryDao();
        System.out.println(userDao.getAllEmploymentHistoryByUserId(1));
    }
}