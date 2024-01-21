package com.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.company.dao.impl.UserRepository;
import com.company.entity.User;

@SpringBootApplication
@EnableCaching
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
            for (int i=0; i<10; i++){
                userRepository.getAll(null, null, null);
            }
        };
    }
}