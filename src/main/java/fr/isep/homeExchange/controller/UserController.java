package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Equipment;
import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.EquipmentRepository;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserRepository userRepository;
    private HabitationRepository habitationRepository;
    private EquipmentRepository equipmentRepository;

    @Autowired
    public UserController(UserRepository userRepository, HabitationRepository habitationRepository, EquipmentRepository equipmentRepository) {
        this.userRepository = userRepository;
        this.habitationRepository = habitationRepository;
        this.equipmentRepository = equipmentRepository;
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

    @GetMapping(value = "/user")
    public String user() {
        return "Welcome User !";
    }


    @RequestMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "index";
        }
        User user = getUserBySession(session);
        model = createUserModel(user, model);
        return "index";
    }

    @GetMapping("register")
    public String register(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "register";
        }
        User user = getUserBySession(session);
        model = createUserModel(user, model);
        return "index";
    }

    @PostMapping("register")
    public String saveRegister(@RequestParam String FName, @RequestParam String LName, @RequestParam String Password, HttpSession httpSession) {
        String pass = encoder(Password);
        System.out.println(pass);
        userRepository.save(new User(FName, LName, pass));
        return "index";
    }

    @GetMapping("login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "login";
        } else {
            User user = getUserBySession(session);
            model = createUserModel(user, model);
            return "index";
        }
    }

    @PostMapping("login")
    public String verifLogin(ModelMap modelMap, @RequestParam String Email, @RequestParam String Password, Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        User user = userRepository.getUserByEmail(Email);
        session.setAttribute("userId", user.getUserId());
        model = createUserModel(user, model);
        if (user.getPassword().equals(encoder(Password))) {
            return "redirect:/";
        }
        modelMap.put("errorMsg", "Please provide the correct username and password");
        return "login";
    }

    @GetMapping("infoscompte")
    public String collectInfosCompte(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/";
        } else {
            User user = getUserBySession(httpSession);
            model = createUserModel(user, model);
            List<Habitation> habits = habitationRepository.getHabitationsByUserId(user.getUserId());
            model.addAttribute("habits", habits);
            List<List<Equipment>> equipmentByHabitation = new ArrayList<>();
            for (Habitation i:habits) {
                List<Equipment> equipment = equipmentRepository.getEquipmentByHabitation(i);
                equipmentByHabitation.add(equipment);
            }
            model.addAttribute("equipmentsByHabitation", equipmentByHabitation);
            model.addAttribute("UsersHabitationsList", habits);
            System.out.println(equipmentByHabitation.toString());
            return "infoscompte";
        }
    }

    @RequestMapping("infoscompte")
    public String infos_compte(Model model, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        return ("infoscompte");
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "logoutsuccessful";
    }

    @RequestMapping("user/update")
    private String updateUser(HttpSession session, Model model, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String username) {
        User user = getUserBySession(session);
        model = createUserModel(user, model);
        List<Habitation> habits = habitationRepository.getHabitationsByUserId(user.getUserId());
        model.addAttribute("habits", habits);
        user.setFirst_name(firstname);
        user.setLast_name(lastname);
        user.setUsername(username);
        userRepository.save(user);
        return "redirect:/infoscompte";
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }

    private Model createUserModel(User user, Model model) {
        return model.addAttribute("user", user);
    }

    public String encoder(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());
    }
}

