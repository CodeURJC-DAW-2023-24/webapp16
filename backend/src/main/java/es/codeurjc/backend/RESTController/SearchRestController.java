package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchRestController {
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam(value = "tournament", required = false) String tournament,
            @RequestParam(value = "team", required = false) String team,
            @RequestParam(value = "player", required = false) String player,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "nationality", required = false) String nationality) {
        Map<String, Object> response = new HashMap<>();

        if (tournament != null) {
            List<Tournament> tournaments = tournamentService.findTournamentByCupSearch(tournament);
            tournaments.forEach(t -> {
                try {
                    t.setTournamentImagePath(t.getTournamentImageAsString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            response.put("tournaments", tournaments);
        }
        if (team != null) {
            List<Team> teams = teamService.findTeamByNameSearch(team);
            teams.forEach(t -> {
                try {
                    t.setImagePath(t.getImageAsString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            response.put("teams", teams);
        }
        if (player != null) {
            List<Player> players = new ArrayList<>();
            players.addAll(playerService.findPlayerByNameSearch(player));
            players.addAll(playerService.findPlayerByLastNameSearch(player));
            response.put("players", players);
        }
        if (position != null) {
            List<Player> playersByPosition = playerService.findPlayerByPositionSearch(position);
            response.put("players", playersByPosition);
        }
        if (nationality != null) {
            List<Player> playersByNationality = playerService.findPlayerByNationalitySearch(nationality);
            response.put("players", playersByNationality);
        }

        return ResponseEntity.ok(response);
    }
}
