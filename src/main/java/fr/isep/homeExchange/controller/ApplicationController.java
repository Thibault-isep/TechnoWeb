package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ApplicationController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String saveRegister(@RequestParam String FName, @RequestParam String LName) {
        userRepository.save(new User(FName, LName));
        return "index";
    }

    @GetMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @PostMapping("login")
    public String verifLogin(ModelMap modelMap, @RequestParam String Username, @RequestParam String Password, Model model, HttpServletRequest request, HttpServletResponse response) {
        User user = userRepository.findAll().stream()
                .filter(userstream -> Username.equals(userstream.getUsername()))
                .findAny().get();
        model.addAttribute("user", user);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (user.getPassword().equals(Password)) {
            return "index";
        }
        modelMap.put("errorMsg", "Please provide the correct username and password");
        return "login";
    }

    @GetMapping("infos_compte")
    public String infos_compte(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        } else {
            System.out.println("hello" + httpSession.getAttribute("user"));
            model.addAttribute("user",httpSession.getAttribute("user"));
            return("infos_compte");
        }
    }
}
