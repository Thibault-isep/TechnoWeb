package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.ReservationRequest;
import fr.isep.homeExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Integer> {
    List<ReservationRequest> getReservationRequestByUser(User user);

    ReservationRequest getReservationRequestByReservationRequestId(int reservationRequestId);

    List<ReservationRequest> getReservationRequestByHabitation(Habitation habitation);

    List<ReservationRequest> getReservationRequestByHabitationHabitationIdAndValidateNot(int habitationId, int validate);
}
