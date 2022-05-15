package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Reservation;
import fr.isep.homeExchange.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //NON RELIE AU VIEW//
    @GetMapping("/getReservations")
    public List<Reservation> getReservations() {return reservationService.getReservations();}

    @GetMapping("/getReservations/{user}")
    public List<Reservation> getUsersReservations(@PathVariable ("user") Integer userId) {
        return reservationService.getReservations().stream()
                .filter(reservation -> userId.equals(reservation.getUser().getUserId()))
                .collect(Collectors.toList());
    }
    ////
}

