package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
    private Country getCountry(ResultSet rs) throws Exception{
        int id=rs.getInt("id");
        String name=rs.getString("name");
        String nationality=rs.getString("nationality");
        return new Country(id,name,nationality);
    }
    @Override
    public List<Country> getAllCountry() {
        List<Country> result=new ArrayList<>();
        try(Connection c=connect();){

            Statement stmt=c.createStatement();
            stmt.execute(" select * from country ");
            ResultSet rs=stmt.getResultSet();

            while(rs.next()){
                Country u=getCountry(rs);

                result.add(u);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Country getCountryById(int id) {
        Country result=null;
        try(Connection c=connect();){

            Statement stmt=c.createStatement();
            stmt.execute(" select * from country where id="+id);
            ResultSet rs=stmt.getResultSet();

            while(rs.next()){
                result=getCountry(rs);

            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateCountry(Country cc) {
        try(Connection c=connect();){

            PreparedStatement stmt=c.prepareStatement("update country set name =?,nationality=? where id=? ");
            stmt.setString(1, cc.getName());
            stmt.setString(2, cc.getNationality());
            stmt.setInt(3, cc.getId());
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeCountry(int id) {
        try(Connection c=connect();){

            Statement stmt=c.createStatement();
            return stmt.execute("delete from country where id="+id);

        }   catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }      }

    @Override
    public boolean addCountry(Country cc) {
        try(Connection c=connect();){

            PreparedStatement stmt=c.prepareStatement("insert into country(name,nationality)values(?,?)");
            stmt.setString(1, cc.getName());
            stmt.setString(2, cc.getNationality());
            return stmt.execute();

        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }      }

}