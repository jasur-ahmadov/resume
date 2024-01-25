package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class AbstractDAO {

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "2000";
        return DriverManager.getConnection(url, username, password);
    }

    private static EntityManagerFactory emfactory = null;

    public EntityManager emInstance() {
        if (emfactory == null) {
            emfactory = Persistence.createEntityManagerFactory("resumeappPU");
        }
        return emfactory.createEntityManager();
    }

    public void closeEmf() {
        emfactory.close();
    }
}