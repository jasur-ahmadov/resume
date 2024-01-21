package com.company.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

    User findByName(String name);

    User findByNameAndSurname(String name, String surname);

    @Query(value = "select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);
}