package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.DTOs.TournamentWithTeamsDTO;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.service.TournamentService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tournaments")
@CrossOrigin(origins = "https://localhost:4200")
public class TournamentRestController {
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private BlobConverter blobConverter;
    @Autowired
    private TeamService teamService;


    @GetMapping
    @Operation(summary = "Get all tournaments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found tournaments", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tournament.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<List<TournamentDTO>>getAllTournaments(){
        List<TournamentDTO> tournamentDTOS = tournamentService.findAllTournaments().stream()
                .map(tournamentService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tournamentDTOS);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a tournament by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the tournament", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tournament.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tournament not found", content = @Content)
    })
    public ResponseEntity<TournamentDTO>getTournamentId(@PathVariable Long id){
        Tournament tournament = tournamentService.findTournamentById(id);
        if (tournament == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tournamentService.convertToDTO(tournament));
    }
    @GetMapping("/{id}/image")
    @Operation(summary = "Get a tournament image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the image", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tournament.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tournament not found", content = @Content)
    })
    public ResponseEntity<String>getTournamentImage(@PathVariable Long id){
        try {
            return ResponseEntity.ok(tournamentService.findTournamentById(id).getTournamentImagePath());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting the image link: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a tournament")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created tournament", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tournament.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<TournamentDTO>getTournamentId(@RequestBody TournamentWithTeamsDTO tournamentWithTeamsDTO){
        Tournament tournament = tournamentService.convertToEntity(tournamentWithTeamsDTO.getTournament());
        tournament.setTournamentImageFile(blobConverter.URLtoBlob(tournament.getTournamentImagePath()));
        Tournament savedTournament = tournamentService.saveRest(tournament);
        List<Team> teams = new ArrayList<>();
        for (TeamDTO teamDTO : tournamentWithTeamsDTO.getTeams()) {
            Team team = teamService.convertToEntity(teamDTO);
            team.setImageFile(blobConverter.URLtoBlob(team.getImagePath()));
            team.setTournament(savedTournament);
            teams.add(team);
        }
        teamService.saveAllRest(teams);
        matchService.saveAll(matchService.generateMatches(teams, savedTournament));
        URI location = URI.create("/api/tournaments/" + savedTournament.getId());
        return ResponseEntity.created(location).body(tournamentService.convertToDTO(savedTournament));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a tournament by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the tournament", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tournament.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tournament not found", content = @Content)
    })
    public ResponseEntity<TournamentDTO> updateTournament(@PathVariable Long id, @RequestBody TournamentDTO tournamentDTO) {
        if (tournamentService.findTournamentById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Tournament tournament = tournamentService.convertToEntity(tournamentDTO);
        tournament.setId(id);
        return ResponseEntity.ok(tournamentService.convertToDTO(tournamentService.updateTournament(id, tournament)));
    }
    @PutMapping("/{id}/image")
    @Operation(summary = "Update a tournament image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the image", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tournament.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tournament not found", content = @Content)
    })
    public ResponseEntity<String> updateTournamentImage(@PathVariable Long id, @RequestBody String imageUrl) {
        Tournament existingTournament = tournamentService.findTournamentById(id);
        if (existingTournament == null) {
            return new ResponseEntity<>("Tournament with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }

        try {
            existingTournament.setTournamentImagePath(imageUrl);
            existingTournament.setTournamentImageFile(blobConverter.URLtoBlob(imageUrl));
            tournamentService.save(existingTournament);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update image for tournament with ID " + id + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("URL of successfully updated image for the tournament with ID: " + id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tournament by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the tournament", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tournament.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "403", description = "Operation not permitted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tournament not found", content = @Content)
    })
    public ResponseEntity<?> deleteTournament(@PathVariable Long id) {
        try {
            if (!matchService.findMatchesByTournamentId(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The tournament cannot be deleted because it has associated matches.");
            }

            tournamentService.deleteTournamentById(id);
            String msg = "Tournament with id " + id + " deleted .";

            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
