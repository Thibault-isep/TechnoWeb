package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserRepository userRepository;
    private HabitationRepository habitationRepository;

    @Autowired
    public UserController(UserRepository userRepository, HabitationRepository habitationRepository) {
        this.userRepository = userRepository;
        this.habitationRepository = habitationRepository;
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getUsers")
    public List<User> getUsers() {return userRepository.findAll();}

    @GetMapping("/getUsers/{user}")
    public List<User> getUserById(@PathVariable("user") Integer userId) {
        return userRepository.findAll().stream()
                .filter(user -> userId.equals(user.getUserId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getUsersHab/{userId}")
    public List<Habitation> getUsersHab(@PathVariable("userId") Integer userId) {
        return habitationRepository.getHabitationsByUserId(1);
    }

    @PostMapping("/login-verif")
    @ResponseBody
    public String login(@RequestParam(name = "Fname") String Fname, @RequestParam(name = "LName") String LName) {
        User user = new User(Fname, LName);
        userRepository.save(user);
        return Fname + LName;
    }

    @GetMapping(value = "/user")
    public String user() {
        return "Welcome User !";
    }
}

