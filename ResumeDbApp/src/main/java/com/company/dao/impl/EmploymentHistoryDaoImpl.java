package com.company.dao.impl;

import com.company.dao.inter.*;
import com.company.entity.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {

        List<EmploymentHistory> empHistories = new ArrayList<>();
        try ( Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                empHistories.add(getEmpHistory(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return empHistories;
    }

    private EmploymentHistory getEmpHistory(ResultSet rs) throws Exception {
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDescription = rs.getString("job_description");
        int userId = rs.getInt("user_id");
        User user = new User(userId);
        return new EmploymentHistory(null, header, beginDate, endDate, jobDescription, user);
    }

    @Override
    public List<EmploymentHistory> getAll() {
        return null;
    }

    @Override
    public boolean insertEmpHistory(EmploymentHistory u) {
        return false;
    }

    @Override
    public boolean updateEmpHistory(EmploymentHistory u) {
        return false;
    }

    @Override
    public boolean removeEmpHistory(int id) {
        return false;
    }
}