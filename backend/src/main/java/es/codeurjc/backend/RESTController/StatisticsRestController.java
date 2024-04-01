package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.PlayerDTO;
import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.stat.Statistics;
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
    @Operation(summary = "Get a team statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the statistic", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Statistics.class))}),
            @ApiResponse(responseCode = "404", description = "Statistic not found", content = @Content)
    })
    public ResponseEntity<List<TeamDTO>> getTeamsStatistics() {
        List<TeamDTO> topTeamsDTO = new ArrayList<>();
        for (Team team : teamService.getTopTeamsByWins(10)){
            topTeamsDTO.add(teamService.convertToDTO(team));
        }
        return ResponseEntity.ok(topTeamsDTO);
    }

    @GetMapping("/teams/{position}")
    @Operation(summary = "Get a team by its position")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the team", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Statistics.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content)
    })
    public ResponseEntity<TeamDTO> getTeamByPosition(@PathVariable int position) {
        List<Team> allTeams = teamService.findAll();
        allTeams.sort(Comparator.comparingInt(Team::getWins).reversed());
        position--; //Deleted index, used position instead
        if (position < 0 || position >= allTeams.size()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamService.convertToDTO(allTeams.get(position)));
    }

    @GetMapping("/players")
    @Operation(summary = "Get best players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found players", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Statistics.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<List<PlayerDTO>> getTopPlayersStatistics() {
        List<PlayerDTO> topPlayersDTO = new ArrayList<>();
        for (Player player : playerService.findTopPlayersByGoals(10)){
            topPlayersDTO.add(playerService.convertToDTO(player));
        }
        return ResponseEntity.ok(topPlayersDTO);
    }

    @GetMapping("/players/{position}")
    @Operation(summary = "Get a player by its position")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the player", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Statistics.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)
    })
    public ResponseEntity<PlayerDTO> getPlayerByPosition(@PathVariable int position) {
        List<Player> allPlayers = playerService.findAll();
        allPlayers.sort(Comparator.comparingInt(Player::getGoals).reversed());
        position--; //Deleted index, used position instead
        if (position < 0 || position >= allPlayers.size()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playerService.convertToDTO(allPlayers.get(position)));
    }
}
