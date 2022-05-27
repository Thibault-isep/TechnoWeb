package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.ReservationPeriod;
import fr.isep.homeExchange.model.ReservationRequest;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.ReservationPeriodRepository;
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
    private final ReservationPeriodRepository reservationPeriodRepository;
    private final HabitationRepository habitationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationController(ReservationRequestRepository reservationRequestRepository, ReservationPeriodRepository reservationPeriodRepository, HabitationRepository habitationRepository, UserRepository userRepository) {
        this.reservationRequestRepository = reservationRequestRepository;
        this.reservationPeriodRepository = reservationPeriodRepository;
        this.habitationRepository = habitationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/reservationRequest/{habitationId}/{reservationPeriodId}")
    public String takeReservation(@PathVariable int habitationId, @PathVariable int reservationPeriodId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            int userId = (int) httpSession.getAttribute("userId");
            User user = getUserBySession(httpSession);
            model.addAttribute("user",user);
            Habitation habitationToRequest = habitationRepository.getHabitationByHabitationId(habitationId);
            List<Habitation> habitationsToExchange = habitationRepository.getHabitationByUserId(userId);
            ReservationPeriod reservationPeriod = reservationPeriodRepository.getReservationPeriodByReservationPeriodId(reservationPeriodId);
            model.addAttribute("habitationToRequest", habitationToRequest);
            model.addAttribute("habitationsToExchange", habitationsToExchange);
            model.addAttribute("reservationPeriod", reservationPeriod);
            return "takeReservation";
        }
    }

    @PostMapping(value = "/reservationRequest/{habitationId}/{reservationPeriodId}/send")
    public String sendReservationRequest(@PathVariable int habitationId, @PathVariable int reservationPeriodId, @RequestParam String userDateOfStart, @RequestParam String userDateOfEnd, @RequestParam int habitationToExchangeId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            User user = getUserBySession(httpSession);
            Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
            Habitation habitationToExchange = habitationRepository.getHabitationByHabitationId(habitationToExchangeId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfStart = LocalDate.parse(userDateOfStart, formatter);
            LocalDate dateOfEnd = LocalDate.parse(userDateOfEnd, formatter);
            ReservationPeriod reservationPeriod = reservationPeriodRepository.getReservationPeriodByReservationPeriodId(reservationPeriodId);
            ReservationRequest reservationRequest = new ReservationRequest(LocalDate.now().toString() + " - " + habitation.getName(), dateOfStart, dateOfEnd, 0, reservationPeriod, habitation, habitationToExchange, user);
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
            model.addAttribute("user",user);
            List<ReservationRequest> reservationRequests = reservationRequestRepository.getReservationRequestByUser(user);
            model.addAttribute("listOfReservationRequests", reservationRequests);
            return "myReservationsRequests";
        }
    }

    @GetMapping(value = "/reservationRequest/{reservationRequestId}/modify")
    public String modifyReservationRequest(@PathVariable int reservationRequestId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            int userId = (int) httpSession.getAttribute("userId");
            User user = getUserBySession(httpSession);
            model.addAttribute("user",user);
            ReservationRequest reservationRequest = reservationRequestRepository.getReservationRequestByReservationRequestId(reservationRequestId);
            List<Habitation> habitations = habitationRepository.getHabitationsByUserId(userId);
            model.addAttribute("reservationRequest", reservationRequest);
            model.addAttribute("habitations", habitations);
            return "modifyReservationRequest";
        }
    }

    @PostMapping(value = "/reservationRequest/{reservationRequestId}/modify/send")
    public String sendModificationReservation(@PathVariable int reservationRequestId, @RequestParam String userDateOfStart, @RequestParam String userDateOfEnd, @RequestParam int habitationToExchangeId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            ReservationRequest reservationRequest = reservationRequestRepository.getReservationRequestByReservationRequestId(reservationRequestId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfStart = LocalDate.parse(userDateOfStart, formatter);
            LocalDate dateOfEnd = LocalDate.parse(userDateOfEnd, formatter);
            Habitation habitationToExchange = habitationRepository.getHabitationByHabitationId(habitationToExchangeId);
            reservationRequest.setStart(dateOfStart);
            reservationRequest.setEnd(dateOfEnd);
            reservationRequest.setHabitationToExchange(habitationToExchange);
            reservationRequestRepository.save(reservationRequest);
            return "redirect:/myReservationsRequests";
        }
    }

    @GetMapping(value = "/reservationRequest/{reservationRequestId}/delete")
    public String deleteReservationRequest(@PathVariable int reservationRequestId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            ReservationRequest reservationRequest = reservationRequestRepository.getReservationRequestByReservationRequestId(reservationRequestId);
            reservationRequestRepository.delete(reservationRequest);
            return "redirect:/myReservationsRequests";
        }
    }

    @GetMapping(value = "/reservationRequest/{habitationId}/fromUsers")
    public String getReservationRequestsFromUsers(@PathVariable int habitationId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            User user = getUserBySession(httpSession);
            model.addAttribute("user", user);
            List<ReservationRequest> reservationRequests = reservationRequestRepository.getReservationRequestByHabitationHabitationIdAndValidateNot(habitationId, -1);
            model.addAttribute("listOfReservationRequests", reservationRequests);
            return "reservationRequestsFromUsers";
        }
    }

    @GetMapping(value = "/reservationRequest/{reservationRequestId}/accept")
    public String acceptReservationRequest(@PathVariable int reservationRequestId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            ReservationRequest reservationRequest = reservationRequestRepository.getReservationRequestByReservationRequestId(reservationRequestId);
            int reservationId = reservationRequest.getReservationRequestId();
            reservationRequest.setValidate(1);
            ReservationPeriod reservationPeriod = reservationRequest.getReservationPeriod();
            reservationPeriod.setValidate(true);
            reservationPeriodRepository.save(reservationPeriod);
            reservationRequestRepository.save(reservationRequest);
            return "redirect:/reservationRequest/" + reservationId + "/fromUsers";
        }
    }

    @GetMapping(value = "/reservationRequest/{reservationRequestId}/refuse")
    public String refuseReservationRequest(@PathVariable int reservationRequestId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            ReservationRequest reservationRequest = reservationRequestRepository.getReservationRequestByReservationRequestId(reservationRequestId);
            reservationRequest.setValidate(-1);
            int reservationId = reservationRequest.getReservationRequestId();
            reservationRequestRepository.save(reservationRequest);
            return "redirect:/reservationRequest/" + reservationId + "/fromUsers";
        }
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }
}