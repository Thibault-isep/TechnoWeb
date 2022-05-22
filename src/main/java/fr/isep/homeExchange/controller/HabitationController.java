package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Equipment;
import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.Rating;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.EquipmentRepository;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.RatingRepository;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HabitationController {

    private HabitationRepository habitationRepository;
    private RatingRepository ratingRepository;
    private UserRepository userRepository;
    private EquipmentRepository equipmentRepository;

    @Autowired
    public HabitationController(HabitationRepository habitationRepository, RatingRepository ratingRepository, UserRepository userRepository, EquipmentRepository equipmentRepository) {
        this.habitationRepository = habitationRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/getHabitationsRating/{habId}")
    public List<Rating> getHabitationsRating(@PathVariable("habId") Integer habId) {
        return ratingRepository.findAll().stream()
                .filter(rating -> habId.equals(rating.getHabitation().getIdHabitation()))
                .collect(Collectors.toList());
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getHabitationsByUser/{user}")
    public List<Habitation> getHabitationsByUserUserId(@PathVariable("user") Integer userId) {
        return habitationRepository.findAll().stream()
                .filter(habitation -> userId.equals(habitation.getUser().getUserId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getUsersHabitations")
    public List<Habitation> getHabitations() {
        return habitationRepository.findAll();
    }

    @RequestMapping(value = "habitation/search")
    public String habitationSearch(Model model, @RequestParam(name = "habitationSearch", defaultValue = "") String userSearch, @RequestParam int rooms) {
        List<Habitation> habitationsList = habitationRepository.getHabitationsByCityLikeOrCityContainsAndRoomsBetween(userSearch, "", 0, rooms);
        model.addAttribute("result", habitationsList);
        return "searchResults";
    }

    @GetMapping(value = {"", "homepage"})
    public String homePage(Model model) {
        List<Habitation> habitationList = habitationRepository.findAll();
        model.addAttribute("habitations", habitationList);
        return "homepage";
    }

    @GetMapping(value = "profile")
    public String profile(Model model, @RequestParam(name = "userId") int userId) {
        List<Habitation> usersHabitationsList = habitationRepository.getHabitationsByUserId(userId);
        return "profile";
    }

    @GetMapping(value = "addHabitation")
    public String addHabitation(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        List<Equipment> equipment = equipmentRepository.findAll();
        model.addAttribute("equipment", equipment);
        model = createUserModel(user, model);
        return "addHabitation";
    }

    @PostMapping(value = "addHabitation")
    public String saveHabitation(Model model, HttpSession session, @RequestParam String Type, @RequestParam String Address, @RequestParam(value = "equipments") List<String> equipments, HttpServletRequest request, HttpServletResponse response) {
        User user = getUserBySession(session);
        model = createUserModel(user, model);
        Habitation newHabitation = new Habitation(Type, Address, user);
        List<Equipment> equipmentList = equipmentRepository.findAll();
        for (int i = 0; i < equipments.size(); i++) {
            System.out.println(i + equipments.get(i));
        }
        habitationRepository.save(newHabitation);
        return "redirect:/infoscompte";
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }

    private Model createUserModel(User user, Model model) {
        return model.addAttribute("user", user);
    }
}


