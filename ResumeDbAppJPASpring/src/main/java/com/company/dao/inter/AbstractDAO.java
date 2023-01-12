package com.company.dao.inter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public abstract class AbstractDAO {

    public static   Connection connect() throws SQLException {
        String url="jdbc:mysql://localhost:3306/resume";
        String username="root";
        String password="elnurdeyanet1071";
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url,username,password);

    }

    private static EntityManagerFactory emf = null;
    
    public EntityManager em(){
        if(emf==null){
            emf= Persistence.createEntityManagerFactory("resumeappPU");
        }
        EntityManager entitymanager = emf.createEntityManager();
        return entitymanager;
    }
    
    
    public void close(EntityManager em){
        emf.close();
    }



}