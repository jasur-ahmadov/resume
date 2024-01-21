package com.company.dao.inter;

import java.util.List;

import com.company.entity.Country;

public interface CountryDaoInter {

    List<Country> getAll();

    Country getById(int id);

    boolean updateCountry(Country u);

    boolean insertCountry(Country u);

    boolean removeCountry(int id);
}