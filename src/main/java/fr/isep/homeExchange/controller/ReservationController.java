package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.ReservationRequest;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.ReservationRequestRepository;
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
    private final ReservationRequestRepository reservationRequestRepository;
    private final HabitationRepository habitationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationController(ReservationRequestRepository reservationRequestRepository, HabitationRepository habitationRepository, UserRepository userRepository) {
        this.reservationRequestRepository = reservationRequestRepository;
        this.habitationRepository = habitationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/reservationRequest/{habitationId}")
    public String takeReservation(@PathVariable int habitationId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
            model.addAttribute("habitation", habitation);
            return "takeReservation";
        }
    }

    @PostMapping(value = "/reservationRequest/{habitationId}/send")
    public String sendRegistration(@PathVariable int habitationId, @RequestParam() String userDateOfStart, @RequestParam() String userDateOfEnd, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            User user = getUserBySession(httpSession);
            Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfStart = LocalDate.parse(userDateOfStart, formatter);
            LocalDate dateOfEnd = LocalDate.parse(userDateOfEnd, formatter);
            ReservationRequest reservationRequest = new ReservationRequest(LocalDate.now().toString() + " - " + habitation.getName(), dateOfStart, dateOfEnd, habitation, user);
            reservationRequestRepository.save(reservationRequest);
            return "redirect:/";
        }
    }

    @GetMapping(value = "/myReservationsRequests")
    public String getMyReservationRequests(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            User user = getUserBySession(httpSession);
            List<ReservationRequest> reservationRequests = reservationRequestRepository.getReservationRequestByUser(user);
            model.addAttribute("listOfReservations", reservationRequests);
            return "myReservationsRequests";
        }
    }

    @GetMapping(value = "/reservationRequest/{reservationRequestId}/modify")
    public String modifyReservationRequest(@PathVariable int reservationRequestId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            ReservationRequest reservationRequest = reservationRequestRepository.getReservationRequestByReservationRequestId(reservationRequestId);
            model.addAttribute("reservation", reservationRequest);
            return "modifyReservationRequest";
        }
    }

    @PostMapping(value = "/reservationRequest/{reservationRequestId}/modify/send")
    public String sendModificationReservation(@PathVariable() int reservationId, @RequestParam() String userDateOfStart, @RequestParam() String userDateOfEnd, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            ReservationRequest reservationRequest = reservationRequestRepository.getReservationRequestByReservationRequestId(reservationId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfStart = LocalDate.parse(userDateOfStart, formatter);
            LocalDate dateOfEnd = LocalDate.parse(userDateOfEnd, formatter);
            reservationRequest.setStart(dateOfStart);
            reservationRequest.setEnd(dateOfEnd);
            reservationRequestRepository.save(reservationRequest);
            return "redirect:/myReservationsRequests";
        }
    }

    @GetMapping(value = "/reservationRequest/{reservationRequestId}/delete")
    public String deleteReservationRequest(@PathVariable() int reservationRequestId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            ReservationRequest reservationRequest = reservationRequestRepository.getReservationRequestByReservationRequestId(reservationRequestId);
            reservationRequestRepository.delete(reservationRequest);
            return "redirect:/myReservationsRequests";
        }
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }
}