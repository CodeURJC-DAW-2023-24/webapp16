package es.codeurjc.backend.DTOs;

import java.util.List;

public class TeamWithPlayersDTO {

    private TeamDTO team;
    private List<PlayerDTO> players;

    public TeamWithPlayersDTO(){

    }
    public TeamWithPlayersDTO(TeamDTO team, List<PlayerDTO> players){
        this.team = team;
        this.players = players;

    }
    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

}
