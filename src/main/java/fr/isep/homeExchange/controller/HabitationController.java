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
    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private EquipmentRepository equipmentRepository;

    @Autowired
    public HabitationController(HabitationRepository habitationRepository, RatingRepository ratingRepository, EquipmentRepository equipmentRepository, UserRepository userRepository) {
        this.habitationRepository = habitationRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/getHabitationsRating/{habId}")
    public List<Rating> getHabitationsRating(@PathVariable ("habId") Integer habId){
        return ratingRepository.findAll().stream()
                .filter(rating -> habId.equals(rating.getHabitation().getHabitationId()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "habitation/search")
    public String habitationSearch(Model model, @RequestParam(name = "habitationSearch", defaultValue = "") String userSearch, @RequestParam int rooms, HttpSession session) {
        List<Habitation> habitationsList = new ArrayList<>();
        if (session.getAttribute("userId") == null) {
            habitationsList = habitationRepository.searchHabitation(userSearch);
        } else {
            User user = getUserBySession(session);
            model = createUserModel(user, model);
            habitationsList = habitationRepository.getHabitationsByCityLikeOrCityContainsAndRoomsBetweenAndUserIsNot(userSearch, "", 0, rooms, user);
        }
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
        List<Habitation> usersHabitationsList = habitationRepository.getHabitationByUserId(userId);
        model.addAttribute("UsersHabitationsList", usersHabitationsList);
        return "profile";
    }

    @RequestMapping(value = "habitation/{habitationId}")
    public String habitationInfo(Model model, @PathVariable("habitationId") int habitationId) {
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        model.addAttribute("habitation", habitation);
        return "habitationInfo";
    }

    @GetMapping("/getHabitationsByUser/{user}")
    public List<Habitation> getHabitationsByUserUserId(@PathVariable("user") Integer userId) {
        return habitationRepository.findAll().stream()
                .filter(habitation -> userId.equals(habitation.getUser().getUserId()))
                .collect(Collectors.toList());
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
    public String saveHabitation(Model model, HttpSession session, @RequestParam String Type, @RequestParam String Address, HttpServletRequest request, HttpServletResponse response) {
        String[] equipments;
        equipments = request.getParameterValues("equipments");
        User user = getUserBySession(session);
        model = createUserModel(user, model);

        List<Equipment> equipmentList = equipmentRepository.findAll();

        Habitation newHabitation = new Habitation(Type, Address, user);
        for (int i = 0; i < equipments.length; i++) {
            if (equipments[i].equals("OUI")) {
                newHabitation.addEquipment(equipmentList.get(i));
            }
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