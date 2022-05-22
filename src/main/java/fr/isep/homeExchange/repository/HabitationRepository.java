package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitationRepository extends JpaRepository<Habitation, Long> {
    List<Habitation> getHabitationByCity(String city);

    Habitation getHabitationByHabitationId(int habitationId);

    @Query("SELECT h FROM Habitation h WHERE h.user.userId = :x")
    List<Habitation> getHabitationByUserId(@Param("x")int id);

    public List<Habitation> getHabitationsByUserUserId(int id);

    @Query("SELECT h FROM Habitation h")
    public List<Habitation> findAll();
}
