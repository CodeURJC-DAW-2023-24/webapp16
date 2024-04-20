package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.PlayerDTO;
import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.DTOs.TeamWithPlayersDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.utils.BlobConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "https://localhost:4200")
public class TeamRestController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private BlobConverter blobConverter;


    @GetMapping
    @Operation(summary = "Get all teams")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found teams", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Team.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<List<Team>> getAllTeams(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "4") int pageSize) throws SQLException {
        return ResponseEntity.ok(teamService.findAllTeams(page, pageSize));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a team by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the team", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Team.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content)
    })
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        Team existingTeam = teamService.findTeamById(id);
        if (existingTeam != null) {
            return ResponseEntity.ok(teamService.convertToDTO(existingTeam));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/image")
    @Operation(summary = "Get a team image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the image", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Team.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    public ResponseEntity<String>getTeamImage(@PathVariable Long id){
        try {
            return ResponseEntity.ok(teamService.findTeamById(id).getImagePath());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting the image link: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a Team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Team", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Team.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamWithPlayersDTO teamWithPlayersDTO) {
        Team team = teamService.convertToEntity(teamWithPlayersDTO.getTeam());
        team.setImageFile(blobConverter.URLtoBlob(team.getImagePath()));
        Team savedTeam = teamService.saveRest(team);
        List<Player> players = new ArrayList<>();
        for (PlayerDTO playerDTO : teamWithPlayersDTO.getPlayers()) {
            Player player = playerService.convertToEntity(playerDTO);
            player.setTeam(savedTeam);
            players.add(player);
        }
        playerService.saveAll(players);
        URI location = URI.create("/api/teams/" + savedTeam.getId());
        return ResponseEntity.created(location).body(teamService.convertToDTO(savedTeam));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a team by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the team", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Team.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content)
    })
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        if (teamService.findTeamById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Team team = teamService.convertToEntity(teamDTO);
        team.setId(id);
        return ResponseEntity.ok(teamService.convertToDTO(teamService.updateTeam(id, team)));
    }
    @PutMapping("/{id}/image")
    @Operation(summary = "Update a team image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the image", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Team.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content)
    })
    public ResponseEntity<String> updateTeamImage(@PathVariable Long id, @RequestBody String imageUrl) {
        Team existingTeam = teamService.findTeamById(id);
        if (existingTeam == null) {
            return new ResponseEntity<>("Team with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }

        try {
            existingTeam.setImagePath(imageUrl);
            existingTeam.setImageFile(blobConverter.URLtoBlob(imageUrl));
            teamService.save(existingTeam);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update image for team with ID " + id + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("URL of successfully updated image for the team with ID: " + id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a team by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the team", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Team.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "403", description = "Operation not permitted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content)
    })
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        try {
            for (Player player : playerService.findPlayersTeamById(id)) {
                player.setTeam(null);
                playerService.save(player);
            }

            if (!matchService.findMatchesByTeamId(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The team cannot be eliminated because it has associated matches.");
            }
            teamService.deleteTeamById(id);
            String msg = "Team with id " + id + " deleted .";
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
