package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Exchange;
import fr.isep.homeExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Exchange getExchangeByExchangeId(int exchangeId);

    Exchange getExchangeByUser1AndUser2(User user1, User user2);
}
