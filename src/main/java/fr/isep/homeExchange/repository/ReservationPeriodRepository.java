package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.ReservationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationPeriodRepository extends JpaRepository<ReservationPeriod, Long> {
}
