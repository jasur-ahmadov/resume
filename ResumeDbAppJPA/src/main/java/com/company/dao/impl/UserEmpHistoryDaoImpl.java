package com.company.dao.impl;

import com.company.dao.inter.*;
import com.company.entity.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class UserEmpHistoryDaoImpl extends AbstractDAO implements UserEmpHistoryDaoInter {

    public EmploymentHistory getUserEmpHistory(ResultSet rs) throws Exception {
        int id = rs.getInt("em_id");
        int userId = rs.getInt("id");
        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
//        EmploymentHistory us = new EmploymentHistory(id, new User(userId), header, beginDate, endDate, jobDescription);
//        return us;
        return null;
    }

    @Override
    public List<EmploymentHistory> getAllEmpHistoryByUserId(int id) {
        List<EmploymentHistory> list = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT "
                    + " u.id,"
                    + " em.id as em_id, "
                    + " em.header,"
                    + " em.begin_date,"
                    + " em.end_date,"
                    + " em.job_description"
                    + " FROM employment_history em "
                    + "  LEFT JOIN user u"
                    + " ON em.user_id = u.id "
                    + " WHERE em.user_id = ? ");
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                EmploymentHistory us = getUserEmpHistory(rs);
                list.add(us);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
