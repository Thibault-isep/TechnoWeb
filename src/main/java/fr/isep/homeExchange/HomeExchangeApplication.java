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
        List<User> users = Arrays.asList(Barth, Thibault, Krishan, Jad);
        userRepository.saveAll(users);


        List<String> Photos1 = new ArrayList<>(Arrays.asList("../images/salon.jpeg", "../images/cuisine.jpg", "../images/chambre.jpg"));
        List<String> Photos2 = new ArrayList<>(Arrays.asList("../images/salon.jpg", "../images/cuisine2.jpg", "../images/chambre1.jpg"));
        List<String> Photos3 = new ArrayList<>(Arrays.asList("../images/house2.jpg", "../images/salon2.jpg", "../images/chambre4.jpeg"));
        List<String> Photos4 = new ArrayList<>(Arrays.asList("../images/maison.jpg", "../images/salon3.jpg", "../images/chambre2.jpg"));

        Habitation hab1 = new Habitation("Apartment in Paris 1st borough", "Apartment", 2, 3, 1, "40m² apartment in Paris", "1 rue de la Paix", "Paris", "France", "75001", "Clean the apartment before leaving.", "Smokers authorized but smoke at the window.", Barth, Photos1);
        Habitation hab2 = new Habitation("Apartment in Paris 16th borough","Apartment", 5, 6, 2, "120m² apartment in Paris", "2 rue du Maréchal Foche", "Paris", "France", "75016", "None", "No smokers allowed, no pets allowed", Thibault, Photos2);
        Habitation hab3 = new Habitation("House next to Marseille", "House", 3, 5, 1, "140m² house next to Marseille with a garden", "72 Boulevard François Arlaud", "Marseille", "France", "13009", "None", "No pets allowed", Krishan, Photos3);
        Habitation hab4 = new Habitation("House in Carnac", "House", 5, 7, 3,"150m² in the center of Carnac", "5 rue er Voten", "Carnac", "France", "56340", "Mow the lawn", "No constraints", Jad, Photos4);

        List<Habitation> habitations = Arrays.asList(hab1, hab2, hab3, hab4);

        habitationRepository.saveAll(habitations);
        ReservationPeriod resP1 = new ReservationPeriod(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 6, 18), false, hab1);
        ReservationPeriod resP2 = new ReservationPeriod(LocalDate.of(2022, 7, 23), LocalDate.of(2022, 7, 30), false, hab1);
        ReservationPeriod resP3 = new ReservationPeriod(LocalDate.of(2022, 8, 13), LocalDate.of(2022, 7, 20), false, hab2);
        ReservationPeriod resP4 = new ReservationPeriod(LocalDate.of(2022,8,13), LocalDate.of(2022,8,15), false, hab4);
        List<ReservationPeriod> reservationPeriods = Arrays.asList(resP1, resP2, resP3, resP4);
        reservationPeriodRepository.saveAll(reservationPeriods);

        ReservationRequest res1 = new ReservationRequest("30/05 - hab1", LocalDate.of(2022, 6, 4), LocalDate.of(2022, 6, 18), 0, resP1, hab1, hab2, Thibault);
        ReservationRequest res2 = new ReservationRequest("09/05 - hab2", LocalDate.of(2022, 8, 13), LocalDate.of(2022, 8, 20), 0, resP3, hab2, hab1, Barth);
        List<ReservationRequest> reservations = Arrays.asList(res1, res2);
        reservationRequestRepository.saveAll(reservations);


        Rating rate1 = new Rating(5, hab1, Barth,"Belle terrasse");
        Rating rate2 = new Rating(4, hab2, Thibault, "Maison très propre et agréable");
        Rating rate3 = new Rating(4, hab2, Thibault, "Maison très propre et agréable :)");
        List<Rating> ratings = Arrays.asList(rate1, rate2,rate3);
        ratingRepository.saveAll(ratings);

        Exchange exchange1 = new Exchange(Barth, Thibault);
        exchangeRepository.save(exchange1);
        Message message1 = new Message("Bonjour, votre maison m'intéresse pour les vacances, je ne vois aucune informations concernant les animaux, sont-ils autorisés ?", LocalDate.now(), exchange1, Thibault);
        Message message2 = new Message("Oui tout à fait, en revanche il n'y a rien sur place pour leur confort", LocalDate.now(), exchange1, Barth);
        Message message3 = new Message("Je prendrai mes affaires, merci", LocalDate.now(), exchange1, Thibault);
        List<Message> messages = Arrays.asList(message1, message2, message3);
        messageRepository.saveAll(messages);

        Equipment netflix = new Equipment("Netflix", "Video");
        Equipment garage = new Equipment("Garage", "Garer une voiture");
        Equipment disneyPlus = new Equipment("Disney+", "Video de disney");
        Equipment garden = new Equipment("Garden", "Presence of a garden");
        Equipment wifi = new Equipment("WIFI", "Presence of WIFI");
        Equipment pool = new Equipment("Pool", "Presence of a Pool");

        List<Equipment> equipment = Arrays.asList(netflix, garage, disneyPlus, garden, wifi, pool);
        equipmentRepository.saveAll(equipment);

        hab1.addEquipment(netflix);
        hab1.addEquipment(garage);
        hab1.addEquipment(wifi);
        hab2.addEquipment(wifi);
        hab2.addEquipment(disneyPlus);
        hab2.addEquipment(netflix);
        hab4.addEquipment(garage);
        hab4.addEquipment(garden);
        hab4.addEquipment(wifi);
        hab3.addEquipment(wifi);

        habitations = Arrays.asList(hab1, hab2);

        habitationRepository.saveAll(habitations);
    }

    public static String encoder(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());
    }
}
