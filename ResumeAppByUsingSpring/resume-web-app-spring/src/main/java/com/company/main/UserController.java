package com.company.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.company.service.inter.UserServiceInter;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceInter userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "users";
    }
}