package es.codeurjc.backend.model;


import jakarta.persistence.*;

import java.util.List;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.model.Team;


@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String cup;

    @OneToMany(mappedBy = "tournament")
    private List<Team> listTeam;

    @OneToMany(mappedBy = "tournament")
    private List<Matches> listMatches;

    public Tournament(){}
    public Tournament(String name, String category, String cup) {
        this.name = name;
        this.category = category;
        this.cup = cup;

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