package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.ReservationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationPeriodRepository extends JpaRepository<ReservationPeriod, Long> {
    ReservationPeriod getReservationPeriodByReservationPeriodId(int reservationPeriodId);

    List<ReservationPeriod> getReservationPeriodByHabitation(Habitation habitation);
}
