package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.Rating;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.RatingRepository;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HabitationController {
    private HabitationRepository habitationRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public HabitationController(HabitationRepository habitationRepository, RatingRepository ratingRepository) {
        this.habitationRepository = habitationRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/getHabitationsRating/{habId}")
    public List<Rating> getHabitationsRating(@PathVariable ("habId") Integer habId){
        return ratingRepository.findAll().stream()
                .filter(rating -> habId.equals(rating.getHabitation().getHabitationId()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "habitation/search")
    public String habitationSearch(Model model, @RequestParam(name = "habitationSearch", defaultValue = "") String userSearch) {
        List<Habitation> habitationsList = habitationRepository.getHabitationByCity(userSearch);
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
}