package fr.isep.homeExchange;

import fr.isep.homeExchange.model.*;
import fr.isep.homeExchange.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
public class HomeExchangeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(HomeExchangeApplication.class, args);
        UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);
        HabitationRepository habitationRepository = configurableApplicationContext.getBean(HabitationRepository.class);
        ReservationRepository reservationRepository = configurableApplicationContext.getBean(ReservationRepository.class);
        RatingRepository ratingRepository = configurableApplicationContext.getBean(RatingRepository.class);
        ExchangeRepository exchangeRepository = configurableApplicationContext.getBean(ExchangeRepository.class);
        MessageRepository messageRepository = configurableApplicationContext.getBean(MessageRepository.class);
        EquipmentRepository equipmentRepository = configurableApplicationContext.getBean(EquipmentRepository.class);

        User Barth = new User("Barth", "Est", "estignard.barthelemy@gmail.com", "BarthEst", encoder("Password123"), LocalDate.of(2000, 11, 10), 0, "10 rue Jules Ferry", "Levallois", "92300", "0606060606", "I'm Barthelemy Estignard", "ROLE_USER");
        User Thibault = new User("Thibault", "Chanier", "thibchan@gmail.fr", "ThibChan", encoder("Pass12"), LocalDate.of(1911, 01, 02), 0, "24 rue de Vanves", "Issy-les-Moulineaux", "92130", "0707070707", "Too old for that stuff", "ROLE_ADMIN");
        List<User> users = Arrays.asList(Barth, Thibault);
        userRepository.saveAll(users);
        Habitation hab1 = new Habitation("House in Paris", "House", 2, 3, 1, true, true, true, "this is a test", true, "1 rue de la Paix", "Paris", "France", "75000", "test", "test", Barth);
        Habitation hab2 = new Habitation("Flat in Marseilles", "Flat", 1, 1, 1, false, true, false, "this is a test2", true, "2 rue Foche", "Marseille", "France", "75000", "test", "test", Thibault);
        List<Habitation> habitations = Arrays.asList(hab1, hab2);
        habitationRepository.saveAll(habitations);
        Reservation res1 = new Reservation("09/05 - hab1", LocalDate.of(2022, 05, 9), LocalDate.of(2022, 05, 12), true, hab1, Thibault);
        Reservation res2 = new Reservation("09/05 - hab2", LocalDate.of(2022, 05, 9), LocalDate.of(2022, 05, 12), true, hab2, Barth);
        List<Reservation> reservations = Arrays.asList(res1, res2);
        reservationRepository.saveAll(reservations);
        Rating rate1 = new Rating(5, hab1, Barth);
        Rating rate2 = new Rating(4, hab2, Thibault);
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
