package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Rating;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class RatingController {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getRatings")
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @GetMapping("/getUsersRatings/{userId}")
    public List<Rating> getUserRatings(@PathVariable("userId") Integer userId) {
        return ratingRepository.findAll().stream()
                .filter(rating -> userId.equals(rating.getUser().getUserId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getHabitationsRatings/{habId}")
    public List<Rating> getHabitationsRatings(@PathVariable("habId") Integer habId) {
        return ratingRepository.findAll().stream()
                .filter(rating -> habId.equals(rating.getHabitation().getIdHabitation()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getRates")
    public String getRates() {
        List<Integer> rates = ratingRepository.findAll().stream()
                .map(Rating::getRate)
                .collect(Collectors.toList());
        List<User> users = ratingRepository.findAll().stream()
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
