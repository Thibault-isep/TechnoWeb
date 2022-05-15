package com.example.demo.controllers;

import com.example.demo.entities.Reservation;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/getReservations")
    public List<Reservation> getReservations() {return reservationService.getReservations();}

    @GetMapping("/getReservations/{user}")
    public List<Reservation> getUsersReservations(@PathVariable("user") Integer userId) {
        return reservationService.getReservations().stream()
                .filter(reservation -> userId.equals(reservation.getUser().getUserId()))
                .collect(Collectors.toList());
    }
}
