package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.ReservationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationPeriodRepository extends JpaRepository<ReservationPeriod, Long> {
    List<ReservationPeriod> getReservationPeriodByValidateIs(boolean validate);

    ReservationPeriod getReservationPeriodByReservationPeriodId(int reservationPeriodId);

    List<ReservationPeriod> getReservationPeriodsByHabitation(Habitation habitation);

    List<ReservationPeriod> getReservationPeriodByStartIsLessThanEqualAndValidateIs(LocalDate dateOfStart, boolean validate);

    List<ReservationPeriod> getReservationPeriodByEndIsGreaterThanEqualAndValidateIs(LocalDate dateOfEnd, boolean validate);

    List<ReservationPeriod> getReservationPeriodByStartIsLessThanEqualAndEndIsGreaterThanEqualAndValidateIs(LocalDate dateOfstart, LocalDate dateOfEnd, boolean validate);
}