package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitationRepository extends JpaRepository<Habitation, Integer> {
    List<Habitation> getHabitationByCity(String city);

    @Query("SELECT h FROM Habitation h WHERE h.user.userId = :x")
    public List<Habitation> getHabitationsByUserId(@Param("x") int id);

    public List<Habitation> getHabitationsByCityLikeOrCityContainsAndRoomsBetween(String city, String cityEmpty, int roomMin, int roomMax);
}
