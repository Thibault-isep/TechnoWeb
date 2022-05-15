package com.example.demo.entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String password;
    private LocalDate dob;
    private int gender;
    private String address;
    private String city;
    private String zip_code;
    private String phone_number;
    private String description;

    public User(String first_name, String last_name, String email, String username, String password, LocalDate dob, int gender, String address, String city, String zip_code, String phone_number, String description) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.zip_code = zip_code;
        this.phone_number = phone_number;
        this.description = description;
    }


    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String name) {
        this.first_name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + first_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
