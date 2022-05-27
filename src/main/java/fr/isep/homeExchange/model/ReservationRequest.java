package fr.isep.homeExchange.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
public class ReservationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationRequestId;
    private String name;
    private LocalDate start;
    private LocalDate end;
    private int validate;

    @ManyToOne
    @JoinColumn(name = "habitation_id")
    private Habitation habitation;

    @ManyToOne
    @JoinColumn(name="habitation_to_exchange_id")
    private Habitation habitationToExchange;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ReservationRequest(String name, LocalDate start, LocalDate end, int validate, Habitation habitation, Habitation habitationToExchange, User user) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.validate = validate;
        this.habitation = habitation;
        this.habitationToExchange = habitationToExchange;
        this.user = user;
    }

    public ReservationRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getValidate() {
        return validate;
    }

    public void setValidate(int validate) {
        this.validate = validate;
    }

    public Habitation getHabitation() {
        return habitation;
    }

    public void setHabitation(Habitation habitation) {
        this.habitation = habitation;
    }

    public int getReservationRequestId() {
        return reservationRequestId;
    }

    public void setReservationRequestId(int reservationId) {
        this.reservationRequestId = reservationId;
    }

    public Habitation getHabitationToExchange() {
        return habitationToExchange;
    }

    public void setHabitationToExchange(Habitation habitationToExchange) {
        this.habitationToExchange = habitationToExchange;
    }
}
