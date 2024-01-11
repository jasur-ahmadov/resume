package com.company.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection c = getConnection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "	u.*, "
                    + "	n.nationality_name AS nationality, "
                    + "	c.NAME AS birthplace "
                    + "FROM "
                    + "	USER u "
                    + "	LEFT JOIN country n ON u.nationality_id = n.id "
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id ");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
//                users.add(getUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        String jpql = "select u from User u where 1=1 ";
        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name = :n ";
        }
        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname = :s ";
        }
        if (nationalityId != null) {
            jpql += " and u.nationality.id = :nId ";
        }
        EntityManager em = emInstance();
        Query q = em.createQuery(jpql, User.class);
        if (name != null && !name.trim().isEmpty()) {
            q.setParameter("n", name);
        }
        if (surname != null && !surname.trim().isEmpty()) {
            q.setParameter("s", surname);
        }
        if (nationalityId != null) {
            q.setParameter("nId", nationalityId);
        }
        em.close();
        return q.getResultList();
    }

    @Override // JPQL
    public User findByEmailAndPassword(String email, String password) {
        EntityManager em = emInstance();
        Query q = em.createQuery("select u from User u where u.email = :e and u.password = :p", User.class);
        q.setParameter("e", email);
        q.setParameter("p", password);
        List<User> list = q.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        em.close();
        return null;
    }

//    @Override // CriteriaBuilder
//    public User findByEmail(String email) {
//        EntityManager em = emInstance();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> cq = cb.createQuery(User.class);
//        Root<User> postRoot = cq.from(User.class);
//        cq.select(postRoot).where(cb.equal(postRoot.get("email"), email));
//        List<User> list = em.createQuery(cq).getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        em.close();
//        return null;
//    }

//    @Override // NamedQuery
//    public User findByEmail(String email) {
//        EntityManager em = emInstance();
//        Query query = em.createNamedQuery("User.findByEmail", User.class);
//        query.setParameter("email", email);
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        em.close();
//        return null;
//    }

    @Override // Native SQL
    public User findByEmail(String email) {
        EntityManager em = emInstance();
        Query query = em.createNativeQuery("select * from user where email = ?", User.class);
        query.setParameter(1, email);
        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        em.close();
        return null;
    }

    @Override
    public boolean updateUser(User u) {
        EntityManager em = emInstance();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager em = emInstance();
        User u = em.find(User.class, id);
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public User getById(int userId) {
        EntityManager em = emInstance();
        User u = em.find(User.class, userId);
        em.close();
        return u;
    }

    static BCrypt.Hasher myVerifyer = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(myVerifyer.hashToString(4, u.getPassword().toCharArray()));
        EntityManager em = emInstance();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}