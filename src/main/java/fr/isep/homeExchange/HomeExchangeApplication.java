package fr.isep.homeExchange;

import fr.isep.homeExchange.controller.HabitationController;
import fr.isep.homeExchange.model.*;
import fr.isep.homeExchange.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
@ComponentScan({"fr.isep.homeExchange", "fr.isep.homeExchange.controller"})
public class HomeExchangeApplication {

    public static void main(String[] args) {
        new File(HabitationController.uploadDirectory).mkdir();
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(HomeExchangeApplication.class, args);
        UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);
        HabitationRepository habitationRepository = configurableApplicationContext.getBean(HabitationRepository.class);
        ReservationRequestRepository reservationRequestRepository = configurableApplicationContext.getBean(ReservationRequestRepository.class);
        ReservationPeriodRepository reservationPeriodRepository = configurableApplicationContext.getBean(ReservationPeriodRepository.class);
        RatingRepository ratingRepository = configurableApplicationContext.getBean(RatingRepository.class);
        ExchangeRepository exchangeRepository = configurableApplicationContext.getBean(ExchangeRepository.class);
        MessageRepository messageRepository = configurableApplicationContext.getBean(MessageRepository.class);
        EquipmentRepository equipmentRepository = configurableApplicationContext.getBean(EquipmentRepository.class);

        User Barth = new User("Barthélémy", "Estignard", "estignard.barthelemy@gmail.com", "BarthEst", encoder("BarthPass12"), LocalDate.of(2000, 11, 10), "Man", "10 rue Jules Ferry", "Levallois", "92300", "0675893241", "I'm Barthelemy Estignard", "ROLE_ADMIN");
        User Thibault = new User("Thibault", "Chanier", "thibchan@gmail.com", "ThibChan", encoder("PassThibChan45"), LocalDate.of(2000, 03, 8), "Man", "24 rue de Vanves", "Issy-les-Moulineaux", "92130", "0778416985", "Too old for that stuff", "ROLE_ADMIN");
        User Krishan = new User("Krishan", "Navas", "krishan.navas@gmail.com", "KrishNav", encoder("KrishPass89"), LocalDate.of(2000,8,12), "Man", "8 Boulevard Poissonière", "Paris", "75009", "0645974032", "Appartement in Paris", "ROLE_USER");
        User Jad = new User("Jad", "Hariri", "jad.hariri", "JadHari", encoder("JadHari"), LocalDate.of(2000, 9, 28), "Man", "129 Avenue du Maine", "Paris", "75014", "0649253781", "Appartement in Paris", "ROLE_USER");
        List<User> users = Arrays.asList(Barth, Thibault, Krishan);
        userRepository.saveAll(users);

        List<String> Photos = new ArrayList<String>(Arrays.asList("../images/house.jpg", "../images/house.jpg", "../images/house.jpg", "../images/house.jpg"));
        Habitation hab1 = new Habitation("Habitation 1", "House", 2, 3, 1, "this is a test", "1 rue de la Paix", "Paris", "France", "75000", "test", "test", Barth, Photos);
        Habitation hab2 = new Habitation("Habitation 2","Flat", 1, 1, 1, "this is a test2", "2 rue Foche", "Marseille", "France", "75000", "test", "test", Thibault, Photos);
        List<Habitation> habitations = Arrays.asList(hab1, hab2);

        habitationRepository.saveAll(habitations);
        ReservationPeriod resP1 = new ReservationPeriod(LocalDate.of(2022, 05, 9), LocalDate.of(2022, 05, 12), false, hab2);
        ReservationPeriod resP2 = new ReservationPeriod(LocalDate.of(2022, 07, 9), LocalDate.of(2022, 07, 12), false, hab2);
        ReservationPeriod resP3 = new ReservationPeriod(LocalDate.of(2022, 07, 9), LocalDate.of(2022, 07, 12), false, hab1);
        List<ReservationPeriod> reservationPeriods = Arrays.asList(resP1, resP2, resP3);
        reservationPeriodRepository.saveAll(reservationPeriods);
        ReservationRequest res1 = new ReservationRequest("09/05 - hab1", LocalDate.of(2022, 05, 9), LocalDate.of(2022, 05, 12), 0, resP3, hab1, hab2, Thibault);
        ReservationRequest res2 = new ReservationRequest("09/05 - hab2", LocalDate.of(2022, 05, 9), LocalDate.of(2022, 05, 12), 0, resP1, hab2, hab1, Barth);
        List<ReservationRequest> reservations = Arrays.asList(res1, res2);
        reservationRequestRepository.saveAll(reservations);
        Rating rate1 = new Rating(5, hab1, Barth,"Belle terrasse");
        Rating rate2 = new Rating(4, hab2, Thibault, "Maison très propre et agréable");
        List<Rating> ratings = Arrays.asList(rate1, rate2);
        ratingRepository.saveAll(ratings);

        Exchange exchange1 = new Exchange(Barth, Thibault);
        exchangeRepository.save(exchange1);
        Message message1 = new Message("Bonjour sale merde", LocalDate.now(), exchange1, Thibault);
        Message message2 = new Message("Oui je suis une merde", LocalDate.now(), exchange1, Barth);
        Message message3 = new Message("C'est bien", LocalDate.now(), exchange1, Thibault);
        List<Message> messages = Arrays.asList(message1, message2, message3);
        messageRepository.saveAll(messages);

        Equipment netflix = new Equipment("Netflix", "Video");
        Equipment garage = new Equipment("Garage", "Garer une voiture");
        Equipment disneyplus = new Equipment("Disney+", "Video de disney");
        Equipment test = new Equipment("test+", "Video de disney");
        List<Equipment> equipment = Arrays.asList(netflix, garage, disneyplus, test);
        equipmentRepository.saveAll(equipment);

        hab1.addEquipment(netflix);
        hab1.addEquipment(garage);
        hab2.addEquipment(disneyplus);
        hab2.addEquipment(netflix);

        habitations = Arrays.asList(hab1, hab2);

        habitationRepository.saveAll(habitations);
    }

    public static String encoder(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());
    }
}
