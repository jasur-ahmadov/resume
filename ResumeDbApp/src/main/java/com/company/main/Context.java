package com.company.main;

import com.company.dao.impl.*;
import com.company.dao.inter.*;

public class Context {

    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }

    public static SkillDaoInter instanceSkillDao() {
        return new SkillDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }

    public static UserEmpHistoryDaoInter instanceUserEmploymentHistoryDao() {
        return new UserEmpHistoryDaoImpl();
    }
}