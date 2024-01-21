package com.company.dao.inter;

import java.util.List;

import com.company.entity.User;

public interface UserDaoInter {

//    List<User> getAll();

    User findByEmailAndPassword(String email, String password);

    List<User> getAll(String name, String surname, Integer nationalityId);

    User getById(int userId);

    boolean updateUser(User u);

    boolean removeUser(int id);

    boolean addUser(User u);

    User findByEmail(String email);
}