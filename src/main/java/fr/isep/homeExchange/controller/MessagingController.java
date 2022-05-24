package fr.isep.homeExchange.controller;

import fr.isep.homeExchange.model.Exchange;
import fr.isep.homeExchange.model.Message;
import fr.isep.homeExchange.model.User;
import fr.isep.homeExchange.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MessagingController {
    private MessageRepository messageRepository;
    private ExchangeRepository exchangeRepository;
    private HabitationRepository habitationRepository;
    private UserRepository userRepository;

    @Autowired
    public MessagingController(MessageRepository messageRepository, ExchangeRepository exchangeRepository, HabitationRepository habitationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.exchangeRepository = exchangeRepository;
        this.habitationRepository = habitationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/messagingFromHabitation")
    public String checkExchange(@RequestParam() int habitationId, HttpSession httpSession) {
        if(httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            User user = getUserBySession(httpSession);
            User otherUser = habitationRepository.getHabitationByHabitationId(habitationId).getUser();
            Exchange exchange = checkExchange(user, otherUser);
            return "redirect:/messaging/" + String.valueOf(exchange.getExchangeId());
        }
    }

    @GetMapping(value = "/messaging/{exchangeId}")
    public String messaging(@PathVariable() int exchangeId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            User user = getUserBySession(httpSession);
            List<Message> exchangeMessages = messageRepository.getMessageByExchangeExchangeId(exchangeId);
            User conditionUser = exchangeRepository.getExchangeByExchangeId(exchangeId).getUser1();
            User otherUser = conditionUser == user ? exchangeRepository.getExchangeByExchangeId(exchangeId).getUser2() : conditionUser;

            model.addAttribute("exchangeMessages", exchangeMessages);
            model.addAttribute("otherUser", otherUser);
            model.addAttribute("exchangeId", exchangeId);
            return "messaging";
        }
    }

    @PostMapping(value ="/messaging/{exchangeId}/send")
    public String sendMessage(@PathVariable() int exchangeId, @RequestParam() String messageContent, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            return "redirect:/login";
        } else {
            int userId = (int) httpSession.getAttribute("userId");
            String content = messageContent;
            Exchange currentExchange = exchangeRepository.getExchangeByExchangeId(exchangeId);
            User user = userRepository.findUserByUserId(userId);
            Message message = new Message(content, LocalDate.now(), currentExchange, user);

            messageRepository.save(message);

            return "redirect:/messaging/{exchangeId}";
        }
    }

    private Exchange checkExchange(User user, User otherUser) {
        Exchange exchange = exchangeRepository.getExchangeByUser1AndUser2(user, otherUser);
        if (exchange == null) {
            exchange = exchangeRepository.getExchangeByUser1AndUser2(otherUser, user);
            if (exchange == null) {
                exchange = new Exchange(user, otherUser);
            }
            exchangeRepository.save(exchange);
        }
        return exchange;
    }

    private User getUserBySession(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        return userRepository.findUserByUserId(userId);
    }
}