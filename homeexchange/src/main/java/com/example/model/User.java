package com.example.model;


import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, unique = true, length = 45)
    private String adress;

    @Column(nullable = false, unique = false, length = 45)
    private String city;

    @Column(nullable = false, unique = true, length = 45)
    private String dob;

    @Column(nullable = false, unique = true, length = 45)
    private String gender;

    @Column(nullable = false, unique = true, length = 45)
    private int phone_number;

    @Column(nullable = false, unique = true, length = 45)
    private int zip_code;

    @Column(nullable = false, length = 64)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
