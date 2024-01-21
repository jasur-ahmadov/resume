package com.company;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserRepository;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;

@SpringBootApplication
public class ResumeDbAppJpaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);
    }

//    @Autowired
//    private UserServiceInter userServiceInter;
//
//    @Bean
//    public CommandLineRunner run() {
//        return args -> {
//            List<User> users = userServiceInter.getAll(null, null, null);
//            User user = users.get(0);
//            user.setName(user.getName().toLowerCase());
//            userServiceInter.updateUser(user);
//        };
//    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            List<User> users = userRepository.findAll();
            System.out.println(users);
            users = userRepository.findAll(Sort.by(Order.desc("id")));
            System.out.println(users);
        };
    }
}