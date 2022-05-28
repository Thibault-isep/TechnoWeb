package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.*;
import fr.isep.homeExchange.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images";
    private UserRepository userRepository;
    private HabitationRepository habitationRepository;
    private EquipmentRepository equipmentRepository;
    private ReservationPeriodRepository reservationPeriodRepository;
    private ReservationRequestRepository reservationRequestRepository;
    private RatingRepository ratingRepository;
    private ExchangeRepository exchangeRepository;
    private MessageRepository messageRepository;

    @Autowired
    public AdminController(MessageRepository messageRepository ,ExchangeRepository exchangeRepository ,RatingRepository ratingRepository ,UserRepository userRepository, HabitationRepository habitationRepository, EquipmentRepository equipmentRepository, ReservationRequestRepository reservationRequestRepository, ReservationPeriodRepository reservationPeriodRepository) {
        this.userRepository = userRepository;
        this.habitationRepository = habitationRepository;
        this.equipmentRepository = equipmentRepository;
        this.reservationPeriodRepository = reservationPeriodRepository;
        this.reservationRequestRepository = reservationRequestRepository;
        this.ratingRepository = ratingRepository;
        this.exchangeRepository = exchangeRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/user-list")
    public String getUsers(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        if (!user.getRoles().equals("ROLE_ADMIN")) {
            return "redirect:/";
        }
        model = createUserModel(user, model);
        model.addAttribute("users", userRepository.findAll());

        return "admin/user-list";
    }

    @GetMapping("/user/{userId}/edit")
    public String editUser(HttpSession session, Model model, @PathVariable int userId) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        if (!user.getRoles().equals("ROLE_ADMIN")) {
            return "redirect:/";
        }
        User userEdit = userRepository.findUserByUserId(userId);
        List<Habitation> habits = habitationRepository.getHabitationsByUserId(userEdit.getUserId());
        model.addAttribute("habits", habits);
        List<List<Equipment>> equipmentByHabitation = new ArrayList<>();
        List<Equipment> equipment = equipmentRepository.findAll();
        for (Habitation i:habits) {
            List<Equipment> equipmentInHabitation = equipmentRepository.getEquipmentByHabitation(i);
            equipmentByHabitation.add(equipmentInHabitation);
        }
        model.addAttribute("equipments", equipment);
        model.addAttribute("equipmentsByHabitation", equipmentByHabitation);
        model.addAttribute("UsersHabitationsList", habits);
        model.addAttribute("user", userRepository.findUserByUserId(userId));
        return "admin/edit-user";
    }

    @GetMapping("/user/{userId}/delete")
    public String deleteUser(HttpSession session, Model model, @PathVariable int userId) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        if (!user.getRoles().equals("ROLE_ADMIN")) {
            return "redirect:/";
        }
        List<Habitation> usersHabitation = habitationRepository.getHabitationsByUserId(userId);

        for (Habitation habitation:usersHabitation) {
            List<ReservationRequest> reservationRequests = reservationRequestRepository.getReservationRequestsByHabitation(habitation);
            List<ReservationRequest> reservationRequests1 = reservationRequestRepository.getReservationRequestByHabitationToExchange(habitation);
            List<ReservationPeriod> reservationPeriods = reservationPeriodRepository.getReservationPeriodsByHabitation(habitation);
            List<Rating> ratings = ratingRepository.getRatingsByHabitation(habitation);
            reservationRequestRepository.deleteAll(reservationRequests);
            reservationRequestRepository.deleteAll(reservationRequests1);
            reservationPeriodRepository.deleteAll(reservationPeriods);
            ratingRepository.deleteAll(ratings);
            habitationRepository.delete(habitation);
        }
        List<Exchange> exchanges = exchangeRepository.getExchangeByUser1OrUser2(userRepository.findUserByUserId(userId), userRepository.findUserByUserId(userId));
        for (Exchange exchange:exchanges) {
            List<Message> messages = messageRepository.getMessageByExchangeExchangeId(exchange.getExchangeId());
            messageRepository.deleteAll(messages);
        }
        exchangeRepository.deleteAll(exchanges);
        userRepository.delete(userRepository.findUserByUserId(userId));
        return "redirect:/admin/user-list";
    }

    @GetMapping("")
    public String homePageAdmin(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        if (!user.getRoles().equals("ROLE_ADMIN")) {
            return "redirect:/";
        }
        model = createUserModel(user, model);
        return "admin/admin-homepage";
    }

    @RequestMapping("user/{userId}/update")
    public String editUser(HttpSession session, @PathVariable int userId, @RequestParam String username, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String Dob, @RequestParam String Email, @RequestParam String Gender, @RequestParam String Address, @RequestParam String City, @RequestParam String Description, @RequestParam String Phone_Number, @RequestParam String Zip_Code) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        if (!user.getRoles().equals("ROLE_ADMIN")) {
            return "redirect:/";
        }
        User userEdit = userRepository.findUserByUserId(userId);
        userEdit.updateUser(username, firstname, lastname, Description, Address, City, Gender, Phone_Number, Email, Zip_Code, Dob);

        userRepository.save(userEdit);
        return "redirect:/admin/user-list";
    }

    @PostMapping("user/{userId}/habitations/{habitationId}")
    public String editHabitation(HttpSession session, @PathVariable int userId, @PathVariable int habitationId, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        User user = getUserBySession(session);
        if (!user.getRoles().equals("ROLE_ADMIN")) {
            return "redirect:/";
        }
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        List<Equipment> equipments = equipmentRepository.findAll();
        List<Equipment> habitationsEquipment = equipmentRepository.getEquipmentByHabitation(habitation);
        List<ReservationPeriod> reservationPeriods = reservationPeriodRepository.getReservationPeriodsByHabitation(habitation);
        model.addAttribute("photos", habitation.getPhotos());
        model.addAttribute("equipment", equipments);
        model.addAttribute("habitationsequipment", habitationsEquipment);
        model.addAttribute("reservationPeriods", reservationPeriods);
        model.addAttribute("habitation", habitation);
        return "admin/edit-user-habitation";
    }

    @RequestMapping("user/{userId}/habitations/update")
    public String updateUserHabitation(Model model, HttpServletRequest request, HttpSession session, @RequestParam String Type, @RequestParam String Name, @RequestParam String Address, @RequestParam String Country, @RequestParam String Zip_Code, @RequestParam String City, @RequestParam int Rooms, @RequestParam int Bed, @RequestParam int Bathrooms, @RequestParam String Description, @RequestParam String Services, @RequestParam String Constraints, @RequestParam int habitationId, @RequestParam MultipartFile[] Photos) throws IOException {
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
        for (MultipartFile photo : Photos) {
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

        String[] reservationPeriodIds = request.getParameterValues("reservationPeriodId");
        String[] datesOfStart = request.getParameterValues("dateOfStart");
        String[] datesOfEnd = request.getParameterValues("dateOfEnd");

        if (reservationPeriodIds != null) {
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

        String[] newDatesOfStart = request.getParameterValues("newDateOfStart");
        String[] newDatesOfEnd = request.getParameterValues("newDateOfEnd");

        if (newDatesOfStart != null) {
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
        return "redirect:/admin/user-list";
    }

    @RequestMapping("user/{habitationId}/habitations/delete-image/{image}")
    public String deleteImageHabitation(@PathVariable int habitationId, @PathVariable int image, HttpSession session) {
        Habitation habitation = habitationRepository.getHabitationByHabitationId(habitationId);
        habitation.deletePhoto(image);
        habitationRepository.save(habitation);
        return "redirect:/admin/user-list";
    }

    @GetMapping(value = "/{habitationId}/habitations/delete")
    public String deleteHabitation(@PathVariable() int habitationId, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        User user = getUserBySession(httpSession);
        if (!user.getRoles().equals("ROLE_ADMIN")) {
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
            return "redirect:/admin/user-list";
        }
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }

    private Model createUserModel(User user, Model model) {
        return model.addAttribute("user", user);
    }

}
