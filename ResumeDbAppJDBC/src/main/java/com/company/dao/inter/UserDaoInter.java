package com.company.dao.inter;

import com.company.entity.User;
import java.util.List;

public interface UserDaoInter {

    public List<User> getAll();

    public User findByEmailAndPassword(String email, String password);

    public List<User> getAll(String name, String surname, Integer nationalityId);

    public User getById(int userId);

    public boolean updateUser(User u);

    public boolean removeUser(int id);

    public boolean addUser(User u);

    public User findByEmail(String email);
}