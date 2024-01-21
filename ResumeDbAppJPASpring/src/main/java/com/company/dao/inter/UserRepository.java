package com.company.dao.inter;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}