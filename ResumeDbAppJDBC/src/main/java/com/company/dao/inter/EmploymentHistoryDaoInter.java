package com.company.dao.inter;

import com.company.entity.EmploymentHistory;
import java.util.List;

public interface EmploymentHistoryDaoInter {

    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);

    public List<EmploymentHistory> getAll();

    public boolean insertEmpHistory(EmploymentHistory u);

    public boolean updateEmpHistory(EmploymentHistory u);

    public boolean removeEmpHistory(int id);
}