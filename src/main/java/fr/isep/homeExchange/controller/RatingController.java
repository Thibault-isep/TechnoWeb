package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.Rating;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.RatingRepository;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RatingController {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final HabitationRepository habitationRepository;

    @Autowired
    public RatingController(RatingRepository ratingRepository, UserRepository userRepository, HabitationRepository habitationRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.habitationRepository = habitationRepository;
    }

    @PostMapping(value="/edit-my-rating/{habitationId}/{ratingId}")
    public String editUserRating(@PathVariable int habitationId, @PathVariable int ratingId, @RequestParam int ratingRate, @RequestParam String ratingDescription, @RequestParam int reservationPeriodId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        Rating rating = ratingRepository.getRatingByRatingId(ratingId);
        rating.setRate(ratingRate);
        rating.setDescription(ratingDescription);
        ratingRepository.save(rating);
        return "redirect:/habitation/" + habitationId + "/" + reservationPeriodId;
    }

    @PostMapping(value="/add-my-rating/{habitationId}")
    public String editUserRating(@PathVariable int habitationId, @RequestParam int ratingRate, @RequestParam String ratingDescription, @RequestParam int reservationPeriodId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        User user = getUserBySession(httpSession);
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        Rating rating = new Rating(ratingRate, habitation, user, ratingDescription);
        ratingRepository.save(rating);
        return "redirect:/habitation/" + habitationId + "/" + reservationPeriodId;
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }
}
