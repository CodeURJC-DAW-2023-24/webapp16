package es.codeurjc.backend.model;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Matches {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "localTeam_id")
    private Team localTeam;

    @ManyToOne
    @JoinColumn(name = "visitingTeam_id")
    private Team visitingTeam;

    private int localGoals;
    private int visitingGoals;

    private int round;


    @ManyToOne
    private Tournament tournament;



    @OneToOne(mappedBy = "match")
    private Report report;
    public Matches() {
    }


    public Matches(Team localTeam, Team visitingTeam, Tournament tournament, int localGoals, int visitingGoals, int round) {
        this.localTeam = localTeam;
        this.visitingTeam = visitingTeam;
        this.tournament = tournament;
        this.localGoals = localGoals;
        this.visitingGoals = visitingGoals;
        this.round = round;
    }

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


    public void setReport(Report report) {
    }
    public Report getReport() {
        return report;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}