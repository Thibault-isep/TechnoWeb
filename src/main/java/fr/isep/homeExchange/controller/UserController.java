package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> getUsers() {
        return userRepository.findAll();
    }

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


    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String saveRegister(@RequestParam String FName, @RequestParam String LName, HttpSession httpSession) {
        userRepository.save(new User(FName, LName));
        System.out.println(httpSession.getAttribute("userId"));
        return "index";
    }

    @GetMapping("login")
    public String login(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "login";
        } else {
            return "index";
        }
    }

    @PostMapping("login")
    public String verifLogin(ModelMap modelMap, @RequestParam String Username, @RequestParam String Password, Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        User user = userRepository.findAll().stream()
                .filter(userstream -> Username.equals(userstream.getUsername()))
                .findAny().get();
        model.addAttribute("user", user);
        session.setAttribute("userId", user.getUserId());
        if (user.getPassword().equals(Password)) {
            return "index";
        }
        modelMap.put("errorMsg", "Please provide the correct username and password");
        return "login";
    }

    @GetMapping("collectInfoCompte")
    public String collectInfosCompte(Model model, HttpSession httpSession) {
        System.out.println(httpSession.getAttribute("userId"));
        if (httpSession.getAttribute("userId") == null) {
            return "index";
        } else {
            User user = getUserBySession(httpSession);
            System.out.println(user.getUsername());
            List<Habitation> habits = habitationRepository.getHabitationsByUserId(user.getUserId());
            model.addAttribute("user", user);
            model.addAttribute("habits", habits);
            return "infoscompte";
        }
    }

    @RequestMapping("infoscompte")
    public String infos_compte(Model model, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        return ("infoscompte");
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "logoutsuccessful";
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }
}

