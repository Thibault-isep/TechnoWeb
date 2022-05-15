package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;
    private int rate;

    @OneToOne
    @JoinColumn(name = "User_Id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Habitation_Id")
    private Habitation habitation;

    public Rating(){}

    public Rating(int rate, Habitation habitation, User user) {
        this.rate = rate;
        this.habitation = habitation;
        this.user = user;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Habitation getHabitation() {
        return habitation;
    }

    public void setHabitation(Habitation habitation) {
        this.habitation = habitation;
    }
}
