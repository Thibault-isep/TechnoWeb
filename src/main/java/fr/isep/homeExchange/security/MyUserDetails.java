package fr.isep.homeExchange.security;

import fr.isep.homeExchange.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

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
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.userId = user.getUserId();
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.dob = user.getDob();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.zip_code = user.getZip_code();
        this.phone_number = user.getPhone_number();
        this.description = user.getDescription();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
