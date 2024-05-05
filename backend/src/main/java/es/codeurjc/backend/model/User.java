package es.codeurjc.backend.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private String address;
    private String email;
    private String gender;
    private String dni;

    @JsonIgnore
    private String encodedPassword;
    private String nickname;

    public User(String username, String password, String... roles) {
        this.name = username;
        this.encodedPassword = password;
        this.roles = Arrays.asList(roles);
    }
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User() {
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public String getEncodedPassword() {
        return encodedPassword;
    }
    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long value) {
        this.id = value;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String value) {
        this.lastName = value;
    }
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String value) {
        this.address = value;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String value) {
        this.email = value;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String value) {
        this.gender = value;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
}