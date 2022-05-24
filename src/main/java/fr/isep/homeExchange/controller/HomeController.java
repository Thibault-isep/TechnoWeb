package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private UserRepository userRepository;

    @Autowired
    private HomeController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(value="/contact")
    public String contact(){
        return "contact";
    }
}
