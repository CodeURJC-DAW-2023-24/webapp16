package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.PlayerDTO;
import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsRestController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/teams")
    public ResponseEntity<List<TeamDTO>> getTeamsStatistics() {
        List<Team> topTeams = teamService.getTopTeamsByWins(10);
        List<TeamDTO> topTeamsDTO = new ArrayList<>();
        for (Team team : topTeams){
            topTeamsDTO.add(teamService.convertToDTO(team));
        }
        return ResponseEntity.ok(topTeamsDTO);
    }
    @GetMapping("/teams/{position}")
    public ResponseEntity<TeamDTO> getTeamByPosition(@PathVariable int position) {
        List<Team> allTeams = teamService.findAll();
        allTeams.sort(Comparator.comparingInt(Team::getWins).reversed());

        int index = position - 1;

        if (index < 0 || index >= allTeams.size()) {
            return ResponseEntity.notFound().build();
        }

        Team team = allTeams.get(index);
        TeamDTO teamDTO = teamService.convertToDTO(team);
        return ResponseEntity.ok(teamDTO);
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerDTO>> getTopPlayersStatistics() {
        List<Player> topPlayers = playerService.findTopPlayersByGoals(10);
        List<PlayerDTO> topPlayersDTO = new ArrayList<>();
        for (Player player : topPlayers){
            topPlayersDTO.add(playerService.convertToDTO(player));
        }
        return ResponseEntity.ok(topPlayersDTO);
    }
    @GetMapping("/players/{position}")
    public ResponseEntity<PlayerDTO> getPlayerByPosition(@PathVariable int position) {
        List<Player> allPlayers = playerService.findAll();
        allPlayers.sort(Comparator.comparingInt(Player::getGoals).reversed());

        int index = position - 1;

        if (index < 0 || index >= allPlayers.size()) {
            return ResponseEntity.notFound().build();
        }

        Player player = allPlayers.get(index);
        PlayerDTO playerDTO = playerService.convertToDTO(player);
        return ResponseEntity.ok(playerDTO);
    }
}
