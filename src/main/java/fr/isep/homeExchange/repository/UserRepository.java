package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT h FROM Habitation h WHERE h.user.userId = :x")
    List<Habitation> usersHabitation(@Param("x") int id);

    User findUserByUsername(String username);

    User findUserByUserId(int userId);

    User getUserByEmail(String email);
}
