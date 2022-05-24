package fr.isep.homeExchange.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table
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
    private String gender;
    private String address;
    private String city;
    private String zip_code;
    private String phone_number;
    private String description;
    private String roles;

    public User(String fname, String lname) {
        this.first_name = fname;
        this.last_name = lname;
    }

    public User(String first_name, String last_name, String password, String Email, LocalDate dob, String Username) {
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = Email;
        this.dob = dob;
        this.username = Username;
    }

    public User(String first_name, String last_name, String email, String username, String password, LocalDate dob, String gender, String address, String city, String zip_code, String phone_number, String description, String roles) {
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
        this.roles = roles;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", description='" + description + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    public void updateUser(String username, String firstname, String lastname, String Description, String Address, String City, String Gender, String Phone_Number, String Email, String Zip_Code, String Dob) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(Dob, formatter);

        this.setFirst_name(firstname);
        this.setLast_name(lastname);
        this.setUsername(username);
        this.setDescription(Description);
        this.setAddress(Address);
        this.setCity(City);
        this.setGender(Gender);
        this.setPhone_number(Phone_Number);
        this.setDob(date);
        this.setEmail(Email);
        this.setZip_code(Zip_Code);
    }
}