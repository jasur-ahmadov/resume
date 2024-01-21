package com.company.dao.inter;

import java.util.List;

import com.company.entity.EmploymentHistory;

public interface UserEmpHistoryDaoInter {

    List<EmploymentHistory> getAllEmpHistoryByUserId(int id);
}