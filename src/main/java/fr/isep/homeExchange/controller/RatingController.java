package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Rating;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getRatings")
    public List<Rating> getRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/getUsersRatings/{userId}")
    public List<Rating> getUserRatings(@PathVariable("userId") Integer userId) {
        return ratingService.getRatings().stream()
                .filter(rating -> userId.equals(rating.getUser().getUserId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getHabitationsRatings/{habId}")
    public List<Rating> getHabitationsRatings(@PathVariable("habId") Integer habId) {
        return ratingService.getRatings().stream()
                .filter(rating -> habId.equals(rating.getHabitation().getIdHabitation()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getRates")
    public String getRates() {
        List<Integer> rates = ratingService.getRatings().stream()
                .map(Rating::getRate)
                .collect(Collectors.toList());
        List<User> users = ratingService.getRatings().stream()
                .map(Rating::getUser)
                .collect(Collectors.toList());
        List<String> usersFirstNames = users.stream()
                .map(User::getFirst_name)
                .collect(Collectors.toList());
        List<String> usersLastNames = users.stream()
                .map(User::getLast_name)
                .collect(Collectors.toList());
        List<String> usersNames = new ArrayList<>();
        for (int i = 0; i < usersFirstNames.size(); i++) {
            usersNames.add(usersFirstNames.get(i) + " " + usersLastNames.get(i) + " " + rates.get(i));
        }
        return usersNames.toString();
    }
    ////
}

