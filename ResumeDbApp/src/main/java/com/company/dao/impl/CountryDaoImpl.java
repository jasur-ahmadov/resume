package com.company.dao.impl;

import com.company.entity.Country;
import java.util.List;
import com.company.dao.inter.*;
import java.sql.*;
import java.util.*;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    @Override 
    
    public List<Country> getAll() {

        List<Country> countries = new ArrayList<>();
        try ( Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("select * from country");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nationName = rs.getString("nationality_name");
                countries.add(new Country(id, name, nationName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countries;
    }
}