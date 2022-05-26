package fr.isep.homeExchange.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
public class ReservationPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationPeriodId;
    private LocalDate start;
    private LocalDate end;
    private boolean validate;

    @ManyToOne
    @JoinColumn(name = "habitation_id")
    private Habitation habitation;

    public ReservationPeriod() {
    }

    public ReservationPeriod(LocalDate start, LocalDate end, boolean validate, Habitation habitation) {
        this.start = start;
        this.end = end;
        this.validate = validate;
        this.habitation = habitation;
    }

    public int getReservationPeriodId() {
        return reservationPeriodId;
    }

    public void setReservationPeriodId(int reservationPeriodId) {
        this.reservationPeriodId = reservationPeriodId;
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

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Habitation getHabitation() {
        return habitation;
    }

    public void setHabitation(Habitation habitation) {
        this.habitation = habitation;
    }
}
