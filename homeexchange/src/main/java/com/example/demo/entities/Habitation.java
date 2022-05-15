package com.example.demo.entities;


import javax.persistence.*;

@Entity
public class Habitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int habitationId;
    private String type;
    private int bed;
    private int rooms;
    private int bathrooms;
    private boolean isGarden;
    private boolean isGarage;
    private boolean isWifi;
    private String description;
    private boolean available;
    private String address;
    private String city;
    private String country;
    private String zip_code;
    private String services;
    private String constraints;





    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Habitation(int size, String type, User user) {
        this.type = type;
        this.user = user;
    }

    public Habitation() {
    }

    public int getHabitationId() {
        return habitationId;
    }

    public void setHabitationId(int id) {
        this.habitationId = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Habitation{" +
                "habitationId=" + habitationId +
                ", type='" + type + '\'' +
                '}';
    }
}
