package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.ReservationRequest;
import fr.isep.homeExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Integer> {
    List<ReservationRequest> getReservationByUser(User user);

    ReservationRequest getReservationByReservationId(int reservationId);
}
