package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.Rating;
import fr.isep.homeExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> getRatingsByHabitation(Habitation habitation);

    Rating getRatingByRatingId(int ratingId);

    Rating getRatingByUserUserIdAndHabitationHabitationId(int userId, int habitationId);
}
