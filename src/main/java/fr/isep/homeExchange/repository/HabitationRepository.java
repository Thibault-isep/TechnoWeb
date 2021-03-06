package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitationRepository extends JpaRepository<Habitation, Long> {
    Habitation getHabitationByHabitationId(int habitationId);

    @Query("SELECT h FROM Habitation h WHERE h.user.userId = :x")
    List<Habitation> getHabitationByUserId(@Param("x")int id);

    @Query("SELECT h FROM Habitation h")
    List<Habitation> findAll();

    @Query("SELECT h FROM Habitation h WHERE h.user.userId = :x")
    List<Habitation> getHabitationsByUserId(@Param("x") int id);

    @Query("SELECT h FROM Habitation h WHERE h.user.userId <> :x")
    List<Habitation> searchHabitation(@Param("x") int id);

    @Query("SELECT MAX(h.bed) FROM Habitation h")
    Integer getMaxHabitation();

    Habitation getHabitationByHabitationIdAndUserUserIdNot(int habitationId, int userId);
}
