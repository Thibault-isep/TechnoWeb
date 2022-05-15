package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getUsers")
    public List<User> getUsers() {return userService.getUsers();}

    @GetMapping("/getUsers/{user}")
    public List<User> getUserById(@PathVariable("user") Integer userId) {
        return userService.getUsers().stream()
                .filter(user -> userId.equals(user.getUserId()))
                .collect(Collectors.toList());
    }
    ////
}

