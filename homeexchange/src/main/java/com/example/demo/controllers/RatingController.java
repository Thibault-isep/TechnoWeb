package com.example.demo.controllers;

import com.example.demo.entities.Rating;
import com.example.demo.entities.User;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@RestController
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

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
                .filter(rating -> habId.equals(rating.getHabitation().getHabitationId()))
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
}
