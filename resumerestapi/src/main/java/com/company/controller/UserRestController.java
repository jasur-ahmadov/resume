package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceInter userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(
            @RequestParam(name = "nId", required = false) Integer nId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname
    ) {
        List<User> users = userService.getAll(name, surname, nId);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user));
        }
//        return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(new UserDTO(user));
    }
}