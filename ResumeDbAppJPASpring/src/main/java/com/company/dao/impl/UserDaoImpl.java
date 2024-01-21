package com.company.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository(value = "repo1")
@Scope(value = "prototype") // default singleton
public class UserDaoImpl implements UserDaoInter {

    @PersistenceContext
    EntityManager em;

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
        return q.getResultList();
    }

    @Override // JPQL
    public User findByEmailAndPassword(String email, String password) {
        Query q = em.createQuery("select u from User u where u.email = :e and u.password = :p", User.class);
        q.setParameter("e", email);
        q.setParameter("p", password);
        List<User> list = q.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

//    @Override // CriteriaBuilder
//    public User findByEmail(String email) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> cq = cb.createQuery(User.class);
//        Root<User> postRoot = cq.from(User.class);
//        cq.select(postRoot).where(cb.equal(postRoot.get("email"), email));
//        List<User> list = em.createQuery(cq).getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        return null;
//    }

//    @Override // NamedQuery
//    public User findByEmail(String email) {
//        Query query = em.createNamedQuery("User.findByEmail", User.class);
//        query.setParameter("email", email);
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        return null;
//    }

    @Override // Native SQL
    public User findByEmail(String email) {
        Query query = em.createNativeQuery("select * from user where email = ?", User.class);
        query.setParameter(1, email);
        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean updateUser(User u) {
        em.merge(u);
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        User u = em.find(User.class, id);
        em.remove(u);
        return true;
    }

    @Override
    public User getById(int userId) {
        User u = em.find(User.class, userId);
        return u;
    }

    static BCrypt.Hasher myVerifyer = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(myVerifyer.hashToString(4, u.getPassword().toCharArray()));
        em.persist(u);
        return true;
    }
}