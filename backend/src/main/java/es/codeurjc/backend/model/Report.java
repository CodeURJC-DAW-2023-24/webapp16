package es.codeurjc.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String time;
    private String matchOfficials;
    private int localTeamGoals;
    private int visitingTeamGoals;
    //private String localTeamScorers;
    //private String visitingTeamScorers;
    private String observations;

    @JsonManagedReference
    @OneToOne
    private Matches match;
    public Report(){}

    public Report(String date, String time, String matchOfficials, int localTeamGoals, int visitingTeamGoals, String observations, Matches match) {
        this.date = date;
        this.time = time;
        this.matchOfficials = matchOfficials;
        this.localTeamGoals = localTeamGoals;
        this.visitingTeamGoals = visitingTeamGoals;
        this.observations = observations;
        this.match = match;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatchOfficials() {
        return matchOfficials;
    }

    public void setMatchOfficials(String matchOfficials) {
        this.matchOfficials = matchOfficials;
    }

    public int getLocalTeamGoals() {
        return localTeamGoals;
    }

    public void setLocalTeamGoals(int localTeamGoals) {
        this.localTeamGoals = localTeamGoals;
    }

    public int getVisitingTeamGoals() {
        return visitingTeamGoals;
    }

    public void setVisitingTeamGoals(int visitingTeamGoals) {
        this.visitingTeamGoals = visitingTeamGoals;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
    public Matches getMatch() {
        return match;
    }

    public void setMatch(Matches match) {
        this.match = match;
    }

    //public String getLocalTeamScorers() {
    //return localTeamScorers;
    //}

    //public void setLocalTeamScorers(String localTeamScorers) {
    //this.localTeamScorers = localTeamScorers;
    //}

    //public String getVisitingTeamScorers() {
    // return visitingTeamScorers;
    //}

    //public void setVisitingTeamScorers(String visitingTeamScorers) {

    //  this.visitingTeamScorers = visitingTeamScorers;
    //}
}
