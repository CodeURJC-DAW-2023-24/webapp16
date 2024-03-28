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

    //Constructor vacio
    public ReportDTO(){
    }

    //Constructor con parametros
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
}
