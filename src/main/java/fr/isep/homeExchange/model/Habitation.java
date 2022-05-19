package fr.isep.homeExchange.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Habitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHabitation;
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

    public Habitation(String type, int bed, int rooms, int bathrooms, boolean isGarden, boolean isGarage, boolean isWifi, String description, boolean available, String address, String city, String country, String zip_code, String services, String constraints) {
        this.type = type;
        this.bed = bed;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.isGarden = isGarden;
        this.isGarage = isGarage;
        this.isWifi = isWifi;
        this.description = description;
        this.available = available;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zip_code = zip_code;
        this.services = services;
        this.constraints = constraints;
    }

    public Habitation() {

    }

    public int getIdHabitation() {
        return idHabitation;
    }

    public void setIdHabitation(int id) {
        this.idHabitation = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
                "habitationId=" + idHabitation +
                ", type='" + type + '\'' +
                '}';
    }
}