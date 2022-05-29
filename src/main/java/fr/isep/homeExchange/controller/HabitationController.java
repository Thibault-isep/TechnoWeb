package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.*;
import fr.isep.homeExchange.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HabitationController {
    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images";
    private HabitationRepository habitationRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private EquipmentRepository equipmentRepository;
    private ReservationRequestRepository reservationRequestRepository;
    private ReservationPeriodRepository reservationPeriodRepository;

    @Autowired
    public HabitationController(HabitationRepository habitationRepository, RatingRepository ratingRepository, EquipmentRepository equipmentRepository, UserRepository userRepository, ReservationRequestRepository reservationRequestRepository, ReservationPeriodRepository reservationPeriodRepository) {
        this.habitationRepository = habitationRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.equipmentRepository = equipmentRepository;
        this.reservationRequestRepository = reservationRequestRepository;
        this.reservationPeriodRepository = reservationPeriodRepository;
    }

    @RequestMapping(value = "habitation/search")
    public String habitationSearch(Model model, @RequestParam(name = "habitationSearch", defaultValue = "") String userSearch, @RequestParam(defaultValue = "1") int Beds, @RequestParam(defaultValue = "") String dateOfStartString, @RequestParam(defaultValue = "") String dateOfEndString, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            User user = getUserBySession(session);
            model.addAttribute("user", user);
        }
        List<Habitation> habitations = new ArrayList<>();
        List<ReservationPeriod> reservationPeriods;
        if(!dateOfStartString.equals("") || !dateOfEndString.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (!dateOfStartString.equals("") && dateOfEndString.equals("")) {
                LocalDate dateOfStart = LocalDate.parse(dateOfStartString, formatter);
                reservationPeriods = reservationPeriodRepository.getReservationPeriodByStartIsLessThanEqualAndValidateIs(dateOfStart, false);
            } else if (dateOfStartString.equals("")) {
                LocalDate dateOfEnd = LocalDate.parse(dateOfEndString, formatter);
                reservationPeriods = reservationPeriodRepository.getReservationPeriodByEndIsGreaterThanEqualAndValidateIs(dateOfEnd, false);
            } else {
                LocalDate dateOfStart = LocalDate.parse(dateOfStartString, formatter);
                LocalDate dateOfEnd = LocalDate.parse(dateOfEndString, formatter);
                reservationPeriods = reservationPeriodRepository.getReservationPeriodByStartIsLessThanEqualAndEndIsGreaterThanEqualAndValidateIs(dateOfStart, dateOfEnd, false);
            }
        } else {
            reservationPeriods = reservationPeriodRepository.getReservationPeriodByValidateIs(false);
        }
        for (ReservationPeriod reservationPeriod : reservationPeriods) {
            Habitation habitation;
            if(session.getAttribute("userId") != null) {
                habitation = habitationRepository.getHabitationByHabitationIdAndUserUserIdNot(reservationPeriod.getHabitation().getHabitationId(), (int) session.getAttribute("userId"));
            } else {
                habitation = habitationRepository.getHabitationByHabitationId(reservationPeriod.getHabitation().getHabitationId());
            }
            if(habitation != null) {
                habitations.add(habitation);
            }
        }
        model.addAttribute("reservationPeriods", reservationPeriods);
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
        List<List<Equipment>> Equipments = new ArrayList<>();
        for (Habitation h: habitations){
            Equipments.add(equipmentRepository.getEquipmentByHabitation(h));
        }
        model.addAttribute("Beds", Beds);
        model.addAttribute("Equipments",Equipments);
        model.addAttribute("MaxBeds", habitationRepository.getMaxHabitation());
        return "searchResults";
    }

    @RequestMapping(value = "habitation/{habitationId}/{reservationPeriodId}")
    public String habitationInfo(Model model, @PathVariable("habitationId") int habitationId, @PathVariable("reservationPeriodId") int reservationPeriodId, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            User user = getUserBySession(session);
            Rating ratingFromUser = ratingRepository.getRatingByUserUserIdAndHabitationHabitationId(user.getUserId(), habitationId);
            model.addAttribute("ratingFromUser", ratingFromUser);
            model.addAttribute("user", user);
        }
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        List<Rating> ratings = ratingRepository.getRatingsByHabitation(habitation);
        List<Equipment> equipmentList = equipmentRepository.getEquipmentByHabitation(habitation);
        ReservationPeriod reservationPeriod = reservationPeriodRepository.getReservationPeriodByReservationPeriodId(reservationPeriodId);
        System.out.println("test" + reservationPeriodId);
        model.addAttribute("photos", habitation.getPhotos());
        model.addAttribute("equipments", equipmentList);
        model.addAttribute("ratings", ratings);
        model.addAttribute("habitation", habitation);
        model.addAttribute("reservationPeriod", reservationPeriod);
        return "habitationInfo";
    }

    @RequestMapping(value = "habitation/{habitationId}/exchange")
    public String habitationInfoExchange(Model model, @PathVariable("habitationId") int habitationId, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        User user = getUserBySession(session);
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        List<Rating> ratings = ratingRepository.getRatingsByHabitation(habitation);
        model.addAttribute("ratings", ratings);
        model.addAttribute("habitation", habitation);
        model.addAttribute("user", user);
        return "habitationInfosExchange";
    }

    @RequestMapping(value = "my-habitations/{habitationId}")
    public String myHabitationInfo(Model model, @PathVariable("habitationId") int habitationId, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        User user = getUserBySession(session);
        model = createUserModel(user, model);
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        if (habitation.getUser().getUserId() != user.getUserId()) {
            return "redirect:/infos-account";
        }
        List<Equipment> equipments = equipmentRepository.findAll();
        List<Equipment> habitationsEquipment = equipmentRepository.getEquipmentByHabitation(habitation);
        List<ReservationPeriod> reservationPeriods = reservationPeriodRepository.getReservationPeriodsByHabitation(habitation);
        model.addAttribute("photos", habitation.getPhotos());
        model.addAttribute("equipment", equipments);
        model.addAttribute("habitationsequipment", habitationsEquipment);
        model.addAttribute("reservationPeriods", reservationPeriods);
        model.addAttribute("habitation", habitation);
        return "myHabitationInfo";
    }

    @GetMapping(value = "/my-habitations/{habitationId}/delete")
    public String deleteHabitation(@PathVariable() int habitationId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
            List<ReservationRequest> reservationRequests = reservationRequestRepository.getReservationRequestsByHabitation(habitation);
            List<ReservationRequest> reservationRequests1 = reservationRequestRepository.getReservationRequestByHabitationToExchange(habitation);
            List<ReservationPeriod> reservationPeriods = reservationPeriodRepository.getReservationPeriodsByHabitation(habitation);
            List<Rating> ratings = ratingRepository.getRatingsByHabitation(habitation);
            reservationRequestRepository.deleteAll(reservationRequests);
            reservationRequestRepository.deleteAll(reservationRequests1);
            reservationPeriodRepository.deleteAll(reservationPeriods);
            ratingRepository.deleteAll(ratings);
            habitationRepository.delete(habitation);
            return "redirect:/infos-account";
        }
    }

    @RequestMapping("my-habitation/{habitationId}/delete-image/{image}")
    public String deleteImageHabitation(@PathVariable int habitationId, @PathVariable int image, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        habitation.deletePhoto(image);
        habitationRepository.save(habitation);
        return "redirect:/infos-account";
    }

    @GetMapping(value = "add-habitation")
    public String addHabitation(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        User user = getUserBySession(session);
        List<Equipment> equipment = equipmentRepository.findAll();
        model.addAttribute("equipment", equipment);
        model = createUserModel(user, model);
        return "addHabitation";
    }

    @PostMapping(value = "add-habitation")
    public String saveHabitation(Model model, HttpSession session, @RequestParam String Type, @RequestParam String Address, HttpServletRequest request, HttpServletResponse response, @RequestParam String Country, @RequestParam String Zip_Code, @RequestParam String City, @RequestParam int Rooms, @RequestParam int Bed, @RequestParam int Bathrooms, @RequestParam String Description, @RequestParam String Services, @RequestParam String Constraints, @RequestParam String Name, @RequestParam MultipartFile[] Photos) throws IOException {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
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
        for(MultipartFile photo:Photos) {
            String path = photo.getOriginalFilename().replace(" ", "-");
            Path fileNameAndPath = Paths.get(uploadDirectory, path);
            try {
                Files.write(fileNameAndPath, photo.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            newHabitation.addPhoto("../images/" + path);
        }
        habitationRepository.save(newHabitation);

        String [] datesOfStart = request.getParameterValues("dateOfStart");
        String [] datesOfEnd = request.getParameterValues("dateOfEnd");
        if(datesOfStart != null) {
            for (int i = 0; i < datesOfStart.length; i++) {
                String dateOfStartString = datesOfStart[i];
                String dateOfEndString = datesOfEnd[i];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateOfStart = LocalDate.parse(dateOfStartString, formatter);
                LocalDate dateOfEnd = LocalDate.parse(dateOfEndString, formatter);
                ReservationPeriod reservationPeriod = new ReservationPeriod(dateOfStart, dateOfEnd, false, newHabitation);
                reservationPeriodRepository.save(reservationPeriod);
            }
        }
        return "redirect:/infos-account";
    }

    @RequestMapping("update-habitation")
    public String updateHabitation(Model model, HttpServletRequest request, HttpSession session, @RequestParam String Type, @RequestParam String Name, @RequestParam String Address, @RequestParam String Country, @RequestParam String Zip_Code, @RequestParam String City, @RequestParam int Rooms, @RequestParam int Bed, @RequestParam int Bathrooms, @RequestParam String Description, @RequestParam String Services, @RequestParam String Constraints, @RequestParam int habitationId, @RequestParam MultipartFile[] Photos) throws IOException {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
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
        for(MultipartFile photo:Photos) {
            String path = photo.getOriginalFilename().replace(" ", "-");
            Path fileNameAndPath = Paths.get(uploadDirectory, path);
            try {
                Files.write(fileNameAndPath, photo.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            habitation.addPhoto("../images/" + path);
        }

        habitationRepository.save(habitation);

        String [] reservationPeriodIds = request.getParameterValues("reservationPeriodId");
        String [] datesOfStart = request.getParameterValues("dateOfStart");
        String [] datesOfEnd = request.getParameterValues("dateOfEnd");

        if(reservationPeriodIds != null) {
            for (int i = 0; i < reservationPeriodIds.length; i++) {
                int reservationPeriodId = Integer.parseInt(reservationPeriodIds[i]);
                String dateOfStartString = datesOfStart[i];
                String dateOfEndString = datesOfEnd[i];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateOfStart = LocalDate.parse(dateOfStartString, formatter);
                LocalDate dateOfEnd = LocalDate.parse(dateOfEndString, formatter);
                ReservationPeriod reservationPeriod = reservationPeriodRepository.getReservationPeriodByReservationPeriodId(reservationPeriodId);
                reservationPeriod.setStart(dateOfStart);
                reservationPeriod.setEnd(dateOfEnd);
                reservationPeriodRepository.save(reservationPeriod);
            }
        }

        String [] newDatesOfStart = request.getParameterValues("newDateOfStart");
        String [] newDatesOfEnd = request.getParameterValues("newDateOfEnd");

        if(newDatesOfStart != null) {
            for (int i = 0; i < newDatesOfStart.length; i++) {
                String newDateOfStartString = newDatesOfStart[i];
                String newDateOfEndString = newDatesOfEnd[i];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate newDateOfStart = LocalDate.parse(newDateOfStartString, formatter);
                LocalDate newDateOfEnd = LocalDate.parse(newDateOfEndString, formatter);
                ReservationPeriod newReservationPeriod = new ReservationPeriod(newDateOfStart, newDateOfEnd, false, habitation);
                reservationPeriodRepository.save(newReservationPeriod);
            }
        }
        return "redirect:/infos-account";
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }

    private Model createUserModel(User user, Model model) {
        return model.addAttribute("user", user);
    }
}