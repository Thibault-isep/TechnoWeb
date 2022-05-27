package fr.isep.homeExchange.repository;

import fr.isep.homeExchange.model.Message;
import fr.isep.homeExchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getMessageByExchangeExchangeId(int exchangeId);

    List<Message> getMessagesByUser(User user);
}