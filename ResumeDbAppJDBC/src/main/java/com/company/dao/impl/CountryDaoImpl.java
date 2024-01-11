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
        try (Connection c = getConnection()) {
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

    @Override
    public Country getById(int userId) {
        Country country = null;
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM country WHERE id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                country = getCountry(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean updateCountry(Country u) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE country SET name=?, nationality=? WHERE id= ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationalityName());
            stmt.setInt(3, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    @Override
    public boolean insertCountry(Country u) {
        boolean b = true;
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO country (name ,nationality) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationalityName());
            b = stmt.execute();
            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                u.setId(genKeys.getInt(1));
            }
        } catch (Exception ex) {
            System.out.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeCountry(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM country WHERE id=?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    public Country getCountry(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality_name");
        Country country = new Country(id, name, nationality);
        System.out.println(country);
        return country;
    }
}