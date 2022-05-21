package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT AVG(r.rate) AS average FROM Rating r WHERE r.habitation.idHabitation = :x")
    public Float getAverageRate(@Param("x")int id);
}
