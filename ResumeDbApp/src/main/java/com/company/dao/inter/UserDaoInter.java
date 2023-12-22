package com.company.dao.inter;

import com.company.bean.User;
import java.util.List;

public interface UserDaoInter {

    public List<User> getAll();

    public User getById(int userId);

    public boolean updateUser(User u);

    public boolean removeUser(int id);
    
    public boolean addUser(User u);
}