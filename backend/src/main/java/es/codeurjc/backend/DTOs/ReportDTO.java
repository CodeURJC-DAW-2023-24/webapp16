package es.codeurjc.backend.DTOs;

import es.codeurjc.backend.model.Matches;

public class ReportDTO {
    private Long id;
    private String date;
    private String time;
    private String matchOfficials;
    private int localTeamGoals;
    private int visitingTeamGoals;
    private String observations;
    private Matches match;

    //Empty Constructor
    public ReportDTO(){
    }

    //Constructor with parameters
    public ReportDTO(Long id, String date, String time, String matchOfficials, int localTeamGoals, int visitingTeamGoals, String observations, Matches match){
        this.id = id;
        this.date = date;
        this.time = time;
        this.matchOfficials = matchOfficials;
        this.localTeamGoals = localTeamGoals;
        this.visitingTeamGoals = visitingTeamGoals;
        this.observations = observations;
        this.match = match;
    }

    //Getters and Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}
    public String getMatchOfficials() {return matchOfficials;}
    public void setMatchOfficials(String matchOfficials) {this.matchOfficials = matchOfficials;}
    public int getLocalTeamGoals() {return localTeamGoals;}
    public void setLocalTeamGoals(int localTeamGoals) {this.localTeamGoals = localTeamGoals;}
    public int getVisitingTeamGoals() {return visitingTeamGoals;}
    public void setVisitingTeamGoals(int visitingTeamGoals) {this.visitingTeamGoals = visitingTeamGoals;}
    public String getObservations() {return observations;}
    public void setObservations(String observations) {this.observations = observations;}
    public Matches getMatch() {return match;}
    public void setMatch(Matches match) {this.match = match;}
}
