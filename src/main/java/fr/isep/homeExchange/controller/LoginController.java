package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login-verif")
    @ResponseBody
    public String login(@RequestParam(name = "Fname") String Fname, @RequestParam(name = "LName") String LName) {
        User user = new User(Fname, LName);
        userRepository.save(user);
        return Fname + LName;
    }

    @GetMapping(value = "login")
    public String homePage(Model model) {
        return "login";
    }
}
