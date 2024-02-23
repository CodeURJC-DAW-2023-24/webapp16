package es.codeurjc.backend.model;


import jakarta.persistence.*;

import java.util.List;


@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String cup;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Team> teamList;

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

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String value) {
        this.category = value;
    }

    public String getCup() {
        return this.cup;
    }
    public void setCup(String value) {
        this.cup = value;
    }
}