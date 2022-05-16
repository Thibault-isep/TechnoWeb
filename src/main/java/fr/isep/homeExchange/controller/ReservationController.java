package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Reservation;
import fr.isep.homeExchange.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getReservations")
    public List<Reservation> getReservations() {return reservationRepository.findAll();}

    @GetMapping("/getReservations/{user}")
    public List<Reservation> getUsersReservations(@PathVariable ("user") Integer userId) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> userId.equals(reservation.getUser().getUserId()))
                .collect(Collectors.toList());
    }
    ////
}

