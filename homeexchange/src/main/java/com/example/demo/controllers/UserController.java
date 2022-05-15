package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {return userService.getUsers();}

    @GetMapping("/getUsers/{user}")
    public List<User> getUserById(@PathVariable("user") Integer userId) {
        return userService.getUsers().stream()
                .filter(user -> userId.equals(user.getUserId()))
                .collect(Collectors.toList());
    }
}
