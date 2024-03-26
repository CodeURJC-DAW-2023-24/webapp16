package es.codeurjc.backend.DTOs;

import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;

public class MatchDTO {

    private Long id;
    private Team localTeam;
    private Team visitingTeam;
    private int localGoals;
    private int visitingGoals;
    private int round;
    private Tournament tournament;

    // Constructor vacío
    public MatchDTO() {
    }

    // Constructor con parámetros
    public MatchDTO(Long id, Team localTeam, Team visitingTeam, int localGoals, int visitingGoals, int round, Tournament tournament) {
        this.id = id;
        this.localTeam = localTeam;
        this.visitingTeam = visitingTeam;
        this.localGoals = localGoals;
        this.visitingGoals = visitingGoals;
        this.round = round;
        this.tournament = tournament;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
    }

    public Team getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(Team visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public int getLocalGoals() {
        return localGoals;
    }

    public void setLocalGoals(int localGoals) {
        this.localGoals = localGoals;
    }

    public int getVisitingGoals() {
        return visitingGoals;
    }

    public void setVisitingGoals(int visitingGoals) {
        this.visitingGoals = visitingGoals;
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
