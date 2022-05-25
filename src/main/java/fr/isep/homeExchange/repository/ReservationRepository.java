package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Exchange;
import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.Reservation;
import fr.isep.homeExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> getReservationByUser(User user);

    Reservation getReservationByReservationId(int reservationId);

    List<Reservation> getReservationByHabitation(Habitation habitation);
}
