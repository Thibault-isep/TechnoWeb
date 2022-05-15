package fr.isep.homeExchange.config;

import fr.isep.homeExchange.model.Habitation;
import fr.isep.homeExchange.repository.HabitationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HabitationConfig {

    @Bean
    CommandLineRunner commandLineRunner(HabitationRepository repository) {
        return args -> {
            Habitation habitation1 = new Habitation("House", 2, 3, 1, true, true, true, "this is a test", true, "1 rue de la Paix", "Paris", "France", "75000", "test", "test");
            Habitation habitation2 = new Habitation("Flat", 1, 1, 1, false, true, false, "this is a test2", true, "2 rue Foche", "Marseille", "France", "75000", "test", "test");

            repository.saveAll(List.of(habitation1, habitation2));
        };
    }
}
