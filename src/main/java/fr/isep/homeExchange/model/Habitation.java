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
    private boolean hasGarden;
    private boolean hasGarage;
    private boolean hasWifi;
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

    public Habitation(String type, int bed, int rooms, int bathrooms, boolean isGarden, boolean hasGarage, boolean hasWifi, String description, boolean available, String address, String city, String country, String zip_code, String services, String constraints) {
        this.type = type;
        this.bed = bed;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.hasGarden = isGarden;
        this.hasGarage = hasGarage;
        this.hasWifi = hasWifi;
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

    public void setIdHabitation(int idHabitation) {
        this.idHabitation = idHabitation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
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
                "idHabitation=" + idHabitation +
                ", type='" + type + '\'' +
                ", bed=" + bed +
                ", rooms=" + rooms +
                ", bathrooms=" + bathrooms +
                ", hasGarden=" + hasGarden +
                ", hasGarage=" + hasGarage +
                ", hasWifi=" + hasWifi +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", services='" + services + '\'' +
                ", constraints='" + constraints + '\'' +
                ", user=" + user +
                '}';
    }
}