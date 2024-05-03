package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.service.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Operation(summary = "Get a search by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the search", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MergedAnnotations.Search.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Search not found", content = @Content)
    })
    public ResponseEntity<List<?>> search(
            @RequestParam(value = "tournament", required = false) String tournament,
            @RequestParam(value = "team", required = false) String team,
            @RequestParam(value = "player", required = false) String player,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "nationality", required = false) String nationality) {
        //Map<String, Object> response = new HashMap<>();


        if (tournament != null) {
            List<Tournament> tournaments = tournamentService.findTournamentByCupSearch(tournament);
            tournaments.forEach(t -> {
                try {
                    t.setTournamentImagePath(t.getTournamentImageAsString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            List<TournamentDTO> tournamentDTOs = tournaments.stream()
                    .map(tournamentService::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok((tournamentDTOs));
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

            List<TeamDTO> teamDTOs = teams.stream()
                    .map(teamService::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(teamDTOs);
        }
        if (player != null) {
            List<Player> players = new ArrayList<>();
            players.addAll(playerService.findPlayerByNameSearch(player));
            players.addAll(playerService.findPlayerByLastNameSearch(player));
            return ResponseEntity.ok(players);
        }
        if (position != null) {
            List<Player> playersByPosition = playerService.findPlayerByPositionSearch(position);
            return ResponseEntity.ok(playersByPosition);
        }
        if (nationality != null) {
            List<Player> playersByNationality = playerService.findPlayerByNationalitySearch(nationality);
            return ResponseEntity.ok(playersByNationality);
        }
        return ResponseEntity.badRequest().build();
    }

}
