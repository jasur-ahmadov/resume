package com.company.dao.inter;

import com.company.entity.EmploymentHistory;
import java.util.List;

public interface UserEmpHistoryDaoInter {

    public List<EmploymentHistory> getAllEmpHistoryByUserId(int id);
}