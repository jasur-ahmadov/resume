package com.company.dao.inter;

import java.util.List;

import com.company.entity.EmploymentHistory;

public interface EmploymentHistoryDaoInter {

    List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);

    List<EmploymentHistory> getAll();

    boolean insertEmpHistory(EmploymentHistory u);

    boolean updateEmpHistory(EmploymentHistory u);

    boolean removeEmpHistory(int id);
}