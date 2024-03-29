package es.codeurjc.backend.DTOs;

import java.util.List;

public class TournamentWithTeamsDTO {
    private TournamentDTO tournament;
    private List<TeamDTO> teams;

    public TournamentWithTeamsDTO() {
    }

    public TournamentWithTeamsDTO(TournamentDTO tournament, List<TeamDTO> teams) {
        this.tournament = tournament;
        this.teams = teams;
    }

    public TournamentDTO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentDTO tournament) {
        this.tournament = tournament;
    }

    public List<TeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDTO> teams) {
        this.teams = teams;
    }
}
