package fr.isep.homeExchange.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
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

    @ManyToMany
    @JoinTable(
            name = "equipments",
            joinColumns = @JoinColumn(name = "habitationId"),
            inverseJoinColumns = @JoinColumn(name = "equipmentId")
    )
    private List<Equipment> equipments = new ArrayList<>();


    public void addEquipment(Equipment equipment) {
        this.equipments.add(equipment);
    }

    public Habitation(String type, String address, User user) {
        this.type = type;
        this.address = address;
        this.user = user;
    }

    public Habitation(String type, int bed, int rooms, int bathrooms, boolean isGarden, boolean isGarage, boolean isWifi, String description, boolean available, String address, String city, String country, String zip_code, String services, String constraints, User user) {
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

    public boolean isGarden() {
        return isGarden;
    }

    public void setGarden(boolean garden) {
        isGarden = garden;
    }

    public boolean isGarage() {
        return isGarage;
    }

    public void setGarage(boolean garage) {
        isGarage = garage;
    }

    public boolean isWifi() {
        return isWifi;
    }

    public void setWifi(boolean wifi) {
        isWifi = wifi;
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

    @Override
    public String toString() {
        return "Habitation{" +
                "habitationId=" + habitationId +
                ", type='" + type + '\'' +
                ", bed=" + bed +
                ", rooms=" + rooms +
                ", bathrooms=" + bathrooms +
                ", isGarden=" + isGarden +
                ", isGarage=" + isGarage +
                ", isWifi=" + isWifi +
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