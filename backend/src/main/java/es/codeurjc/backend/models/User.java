package es.codeurjc.backend.models;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private String address;
    private String email;
    private String gender;



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
}