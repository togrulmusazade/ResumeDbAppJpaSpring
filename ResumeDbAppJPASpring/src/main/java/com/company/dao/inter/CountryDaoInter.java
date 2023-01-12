package com.company.dao.inter;

import com.company.entity.Country;

import java.util.List;





public interface CountryDaoInter {

    public List<Country> getAllCountry();

    public Country getCountryById(int id);

    public boolean updateCountry(Country cc);

    public boolean removeCountry(int id);

    public boolean addCountry(Country cc);
}
