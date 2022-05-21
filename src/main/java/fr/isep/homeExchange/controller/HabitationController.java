package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.Rating;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HabitationController {

    private HabitationRepository habitationRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public HabitationController(HabitationRepository habitationRepository, RatingRepository ratingRepository) {
        this.habitationRepository = habitationRepository;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/getHabitationsRating/{habId}")
    public List<Rating> getHabitationsRating(@PathVariable ("habId") Integer habId){
        return ratingRepository.findAll().stream()
                .filter(rating -> habId.equals(rating.getHabitation().getIdHabitation()))
                .collect(Collectors.toList());
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getHabitationsByUser/{user}")
    public List<Habitation> getHabitationsByUserUserId(@PathVariable ("user") Integer userId){
        return habitationRepository.findAll().stream()
                .filter(habitation -> userId.equals(habitation.getUser().getUserId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getUsersHabitations")
    public List<Habitation> getHabitations() {return habitationRepository.findAll();}
    ////

    @RequestMapping(value = "habitation/{id}")
    public String getHabitationById(Model model, @PathVariable ("id") int id){
        Habitation habitation = habitationRepository.getHabitationByIdHabitation(id);
        Float average = ratingRepository.getAverageRate(id);
        model.addAttribute("rating",average);
        model.addAttribute("habitation",habitation);
        return "habitation";
    }

    @RequestMapping(value = "habitation/search")
    public String habitationSearch(Model model, @RequestParam(name = "habitationSearch", defaultValue = "") String userSearch) {
        List<Habitation> habitationsList = habitationRepository.getHabitationByCity(userSearch);
        List<Integer> habitationsIdList = habitationsList.stream().map(Habitation::getIdHabitation).collect(Collectors.toList());
        List<Float> ratingListByHabitation = new ArrayList<Float>();
        for (int id: habitationsIdList) {
            Float average = ratingRepository.getAverageRate(id);
            if(average == null){

            }else {
                ratingListByHabitation.add(average);
            }
        }
        model.addAttribute("city", userSearch);
        model.addAttribute("result", habitationsList);
        model.addAttribute("rating", ratingListByHabitation);
        return "searchResults";
    }


    @GetMapping(value = "profile")
    public String profile(Model model, @RequestParam(name = "userId") int userId){
        List<Habitation> usersHabitationsList = habitationRepository.getHabitationsByUserId(userId);
        model.addAttribute("UsersHabitationsList", usersHabitationsList);
        return "profile";
    }
}


