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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
        return "redirect:/";
    }

    @PostMapping("register")
    public String saveRegister(ModelMap modelMap, @RequestParam String FName, @RequestParam String LName, @RequestParam String Password, @RequestParam String Email, @RequestParam String Dob, @RequestParam String Username, HttpSession httpSession, @RequestParam String Gender, @RequestParam String Address, @RequestParam String City, @RequestParam String Zip_Code, @RequestParam String Phone_Number, @RequestParam String Description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(Dob, formatter);
        String pass = encoder(Password);
        if (userRepository.findUserByUsername(Username) != null) {
            System.out.println(Username);
            modelMap.put("errorMsg", "Username taken");
            return "register";
        }

        if (userRepository.getUserByEmail(Email) != null) {
            modelMap.put("errorMsg", "Email taken");
            return "register";
        }

        userRepository.save(new User(FName, LName, Email, Username, pass, date, Gender, Address, City, Zip_Code, Phone_Number, Description, "USER"));
        return "redirect:/";
    }

    @GetMapping("login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "login";
        } else {
            User user = getUserBySession(session);
            model = createUserModel(user, model);
            return "redirect:/";
        }
    }

    @PostMapping("login")
    public String verifLogin(ModelMap modelMap, @RequestParam String Email, @RequestParam String Password, Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        User user = userRepository.getUserByEmail(Email);
        if (user.getPassword().equals(encoder(Password))) {
            session.setAttribute("userId", user.getUserId());
            model = createUserModel(user, model);
            return "redirect:/";
        }
        modelMap.put("errorMsg", "Please provide the correct username and password");
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "redirect:/";
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

    @GetMapping("/infoscompte")
    public String collectInfosCompte(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/";
        } else {
            User user = getUserBySession(httpSession);
            model = createUserModel(user, model);
            List<Habitation> habits = habitationRepository.getHabitationsByUserId(user.getUserId());
            model.addAttribute("habits", habits);
            List<List<Equipment>> equipmentByHabitation = new ArrayList<>();
            List<Equipment> equipment = equipmentRepository.findAll();
            for (Habitation i:habits) {
                List<Equipment> equipmentInHabitation = equipmentRepository.getEquipmentByHabitation(i);
                equipmentByHabitation.add(equipmentInHabitation);
            }
            model.addAttribute("equipments", equipment);
            model.addAttribute("equipmentsByHabitation", equipmentByHabitation);
            model.addAttribute("UsersHabitationsList", habits);
            return "infosCompte";
        }
    }

    @RequestMapping("user/update")
    private String updateUser(HttpSession session, Model model, @RequestParam String username, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String Dob, @RequestParam String Email, @RequestParam String Gender, @RequestParam String Address, @RequestParam String City, @RequestParam String Description, @RequestParam String Phone_Number, @RequestParam String Zip_Code) {
        User user = getUserBySession(session);
        model = createUserModel(user, model);
        List<Habitation> habits = habitationRepository.getHabitationsByUserId(user.getUserId());
        model.addAttribute("habits", habits);

        user.updateUser(username, firstname, lastname, Description, Address, City, Gender, Phone_Number, Email, Zip_Code, Dob);

        userRepository.save(user);
        return "redirect:/infoscompte";
    }
}