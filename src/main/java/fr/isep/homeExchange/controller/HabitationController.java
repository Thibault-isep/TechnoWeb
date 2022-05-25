package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.*;
import fr.isep.homeExchange.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HabitationController {
    private HabitationRepository habitationRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private EquipmentRepository equipmentRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public HabitationController(HabitationRepository habitationRepository, RatingRepository ratingRepository, EquipmentRepository equipmentRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.habitationRepository = habitationRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.equipmentRepository = equipmentRepository;
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping(value = "habitation/search")
    public String habitationSearch(Model model, @RequestParam(name = "habitationSearch", defaultValue = "") String userSearch, HttpSession session) {
        List<Habitation> habitations;
        if (session.getAttribute("userId") == null) {
            habitations = habitationRepository.findAll();
        } else {
            habitations = habitationRepository.searchHabitation((int) session.getAttribute("userId"));
            User user = getUserBySession(session);
            model.addAttribute("user", user);
        }
        model.addAttribute("habitations", habitations);
        model.addAttribute("userSearch", userSearch);
        List<Double> Means = new ArrayList<>();
        for (Habitation h: habitations) {
            List<Rating> hRates = ratingRepository.getRatingsByHabitation(h);
            Means.add(hRates.stream()
                    .mapToDouble(hrate -> hrate.getRate())
                    .average()
                    .orElse(0.0));
        }
        model.addAttribute("Means", Means);
        return "searchResults";
    }

    @GetMapping(value = "profile")
    public String profile(Model model, @RequestParam(name = "userId") int userId) {
        List<Habitation> usersHabitationsList = habitationRepository.getHabitationByUserId(userId);
        model.addAttribute("UsersHabitationsList", usersHabitationsList);
        return "profile";
    }

    @RequestMapping(value = "habitation/{habitationId}")
    public String habitationInfo(Model model, @PathVariable("habitationId") int habitationId) {
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        List<Rating> ratings = ratingRepository.getRatingsByHabitation(habitation);
        model.addAttribute("ratings", ratings);
        model.addAttribute("habitation", habitation);
        return "habitationInfo";
    }

    @RequestMapping(value = "myhabitations/{habitationId}")
    public String myHabitationInfo(Model model, @PathVariable("habitationId") int habitationId, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        model = createUserModel(user, model);
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        if (habitation.getUser().getUserId() != user.getUserId()) {
            return "redirect:/infoscompte";
        }
        List<Equipment> equipments = equipmentRepository.findAll();
        List<Equipment> habitationsEquipment = equipmentRepository.getEquipmentByHabitation(habitation);
        model.addAttribute("equipment", equipments);
        model.addAttribute("habitationsequipment", habitationsEquipment);
        model.addAttribute("habitation", habitation);
        return "myHabitationInfo";
    }

    @GetMapping(value = "/myhabitations/{habitationId}/delete")
    public String deleteReservationRequest(@PathVariable() int habitationId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
            List<Reservation> reservations = reservationRepository.getReservationByHabitation(habitation);
            List<Rating> ratings = ratingRepository.getRatingsByHabitation(habitation);
            ratingRepository.deleteAll(ratings);
            reservationRepository.deleteAll(reservations);
            habitationRepository.delete(habitation);
            return "redirect:/infoscompte";
        }
    }

    @GetMapping(value = "addhabitation")
    public String addHabitation(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        List<Equipment> equipment = equipmentRepository.findAll();
        model.addAttribute("equipment", equipment);
        model = createUserModel(user, model);
        return "addHabitation";
    }

    @PostMapping(value = "addhabitation")
    public String saveHabitation(Model model, HttpSession session, @RequestParam String Type, @RequestParam String Address, HttpServletRequest request, HttpServletResponse response, @RequestParam String Country, @RequestParam String Zip_Code, @RequestParam String City, @RequestParam int Rooms, @RequestParam int Bed, @RequestParam int Bathrooms, @RequestParam String Description, @RequestParam String Services, @RequestParam String Constraints, @RequestParam String Name) {
        String[] equipments;
        equipments = request.getParameterValues("equipments");
        User user = getUserBySession(session);
        model = createUserModel(user, model);

        List<Equipment> equipmentList = equipmentRepository.findAll();

        Habitation newHabitation = new Habitation(Name,Type, Bed, Rooms, Bathrooms, Description, Address, City, Country, Zip_Code, Services, Constraints, user);
        for (int i = 0; i < equipments.length; i++) {
            if (equipments[i].equals("OUI")) {
                newHabitation.addEquipment(equipmentList.get(i));
            }
        }
        habitationRepository.save(newHabitation);
        return "redirect:/infoscompte";
    }

    @RequestMapping("updatehabitation")
    public String updateHabitation(Model model, HttpServletRequest request, HttpSession session, @RequestParam String Type, @RequestParam String Name, @RequestParam String Address, @RequestParam String Country, @RequestParam String Zip_Code, @RequestParam String City, @RequestParam int Rooms, @RequestParam int Bed, @RequestParam int Bathrooms, @RequestParam String Description, @RequestParam String Services, @RequestParam String Constraints, @RequestParam int habitationId){
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        List<Equipment> equipmentList = equipmentRepository.findAll();
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        habitation.clearEquipments();
        habitation.updateHabitation(Type, Name, Address, Country, Zip_Code, City, Rooms, Bed, Bathrooms, Description, Services, Constraints);
        String[] equipments;
        equipments = request.getParameterValues("equipments");
        for (int i = 0; i < equipments.length; i++) {
            if (equipments[i].equals("OUI")) {
                habitation.addEquipment(equipmentList.get(i));
            }
        }
        habitationRepository.save(habitation);
        return "redirect:/infoscompte";
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }

    private Model createUserModel(User user, Model model) {
        return model.addAttribute("user", user);
    }
}