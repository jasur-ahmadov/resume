package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableCaching
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

//    @Autowired
//    private UserRepository userRepository;
//
//    @Bean
//    public CommandLineRunner run() {
//        return args -> {
//            System.out.println(Arrays.asList(userRepository.getAuthorities(32)));
//        };
//    }
}