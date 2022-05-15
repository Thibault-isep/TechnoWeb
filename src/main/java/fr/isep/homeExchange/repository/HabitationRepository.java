package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitationRepository extends JpaRepository<Habitation, Long> {
    @Query("SELECT h FROM Habitation h WHERE h.city LIKE :x")
    public List<Habitation> getHabitationByCity(@Param("x")String name);

    @Query("SELECT h FROM Habitation h")
    public List<Habitation> findAll();
}
