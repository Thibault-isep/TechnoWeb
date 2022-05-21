package fr.isep.homeExchange;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.model.Rating;
import fr.isep.homeExchange.model.Reservation;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.HabitationRepository;
import fr.isep.homeExchange.repository.RatingRepository;
import fr.isep.homeExchange.repository.ReservationRepository;
import fr.isep.homeExchange.repository.UserRepository;
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

		User Barth = new User("Barth", "Est", "estignard.barthelemy@gmail.com", "BarthEst", encoder("Pass"), LocalDate.of(2000, 11,10), 0, "10 rue Jules Ferry", "Levallois", "92300", "0606060606", "I'm Barthelemy Estignard","ROLE_USER");
		User Thibault = new User("Thibault", "Chanier", "Thibault.Chanier@gmail.com", "ThibChan", encoder("Pass2"), LocalDate.of(1911, 01, 02), 0, "24 rue de Vanves", "Issy-les-Moulineaux", "92130", "0707070707", "Too old for that stuff", "ROLE_ADMIN");
		List<User> users = Arrays.asList(Barth, Thibault);
		userRepository.saveAll(users);
		Habitation habitation1 = new Habitation("House", 2, 3, 1, true, true, true, "this is a test", true, "1 rue de la Paix", "Paris", "France", "75000", "test", "test", userRepository.findUserByUserId(1));
		Habitation habitation2 = new Habitation("Flat", 1, 1, 1, false, true, false, "this is a test2", true, "2 rue Foche", "Marseille", "France", "75000", "test", "test", userRepository.findUserByUserId(1));
		List<Habitation> habitations = Arrays.asList(habitation1, habitation2);
		habitationRepository.saveAll(habitations);
		Reservation res1 = new Reservation("09/05 - hab1", LocalDate.of(2022,05,9), LocalDate.of(2022,05,12), true, habitation1, Thibault);
		Reservation res2 = new Reservation("09/05 - hab2", LocalDate.of(2022,05,9), LocalDate.of(2022,05,12), true, habitation2, Barth);
		List<Reservation> reservations = Arrays.asList(res1, res2);
		reservationRepository.saveAll(reservations);
		Rating rate1 = new Rating(5, habitation1, Barth);
		Rating rate2 = new Rating(4, habitation2, Thibault);
		List<Rating> ratings = Arrays.asList(rate1, rate2);
		ratingRepository.saveAll(ratings);


	}

	public static String encoder(String password) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(password.getBytes());
	}
}
