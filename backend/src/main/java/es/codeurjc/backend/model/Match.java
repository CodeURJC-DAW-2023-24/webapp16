package es.codeurjc.backend.model;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "localTeam_id")
    private Team localTeam;

    @ManyToOne
    @JoinColumn(name = "visitingTeam_id")
    private Team visitingTeam;

    // private String localTeam;
    // private String visitingTeam;
    private int localGoals;
    private int visitingGoals;
    private LocalDate matchDate;
    private String location;

    public Team getLocalTeam() {
        return this.localTeam;
    }
    public void setLocalTeam(Team value) {
        this.localTeam = value;
    }

    public Team getVisitingTeam() {
        return this.visitingTeam;
    }
    public void setVisitingTeam(Team value) {
        this.visitingTeam = value;
    }

    public int getLocalGoals() {
        return this.localGoals;
    }
    public void setLocalGoals(int value) {
        this.localGoals = value;
    }

    public int getVisitingGoals() {
        return this.visitingGoals;
    }
    public void setVisitingGoals(int value) {
        this.visitingGoals = value;
    }

    public LocalDate getMatchDate() {
        return this.matchDate;
    }
    public void setMatchDate(LocalDate value) {
        this.matchDate = value;
    }

    public String getLocation() {
        return this.location;
    }
    public void setLocation(String value) {
        this.location = value;
    }
}