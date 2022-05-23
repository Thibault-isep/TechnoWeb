package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.Reservation;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.ReservationRepository;
import fr.isep.homeExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final HabitationRepository habitationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, HabitationRepository habitationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.habitationRepository = habitationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/myReservationsRequests")
    public String getMyReservationRequests(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            User user = getUserBySession(httpSession);
            List<Reservation> reservations = reservationRepository.getReservationByUser(user);
            model.addAttribute("listOfReservations", reservations);
            return "myReservationsRequests";
        }
    }

    @GetMapping(value = "/reservation/{reservationId}/modify")
    public String modifyReservation(@PathVariable int reservationId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            Reservation reservation = reservationRepository.getReservationByReservationId(reservationId);
            model.addAttribute("reservation", reservation);
            return "modifyReservationRequest";
        }
    }

    @PostMapping(value = "/reservation/{reservationId}/modify/send")
    public String sendModificationReservation(@PathVariable() int reservationId, @RequestParam() String userDateOfStart, @RequestParam() String userDateOfEnd, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            Reservation reservation = reservationRepository.getReservationByReservationId(reservationId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfStart = LocalDate.parse(userDateOfStart, formatter);
            LocalDate dateOfEnd = LocalDate.parse(userDateOfEnd, formatter);
            reservation.setStart(dateOfStart);
            reservation.setEnd(dateOfEnd);
            reservationRepository.save(reservation);
            return "redirect:/myReservationsRequests";
        }
    }

    @GetMapping(value = "/reservation/{reservationId}/delete")
    public String deleteReservationRequest(@PathVariable() int reservationId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            Reservation reservation = reservationRepository.getReservationByReservationId(reservationId);
            reservationRepository.delete(reservation);
            return "redirect:/myReservationsRequests";
        }
    }

    @PostMapping(value = "/reservation/{habitationId}")
    public String takeReservation(@PathVariable int habitationId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
            model.addAttribute("habitation", habitation);
            return "takeReservation";
        }
    }

    @PostMapping(value = "/reservation/{habitationId}/send")
    public String sendRegistration(@PathVariable int habitationId, @RequestParam() String userDateOfStart, @RequestParam() String userDateOfEnd, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            User user = getUserBySession(httpSession);
            Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfStart = LocalDate.parse(userDateOfStart, formatter);
            LocalDate dateOfEnd = LocalDate.parse(userDateOfEnd, formatter);
            Reservation reservation = new Reservation(LocalDate.now().toString() + " - " + habitation.getName(), dateOfStart, dateOfEnd, false, habitation, user);
            reservationRepository.save(reservation);
            return "redirect:/";
        }
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }
}