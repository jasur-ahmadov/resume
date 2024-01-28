package com.company.dao.impl;

import java.util.List;

import com.company.entity.User;

public interface UserRepositoryCustom {

    User findByEmailAndPassword(String email, String password);

    List<User> getAll(String name, String surname, Integer nationalityId);

    User getById(int userId);

    boolean updateUser(User u);

    boolean removeUser(int id);

    boolean addUser(User u);

    User findByEmail(String email);

    String[] getAuthorities(Integer userId);
}