package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.MatchDTO;
import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.MatchService;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matches")
public class MatchRestController {

    @Autowired
    private MatchService matchService;
    @GetMapping
    @Operation(summary = "Get all matches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found matches", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Matches.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<List<MatchDTO>>getAllMatches(){
        List<MatchDTO> matchDTOS = matchService.findAllMatches().stream()
                .map(matchService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(matchDTOS);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a match by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the match", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Matches.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Match not found", content = @Content)
    })
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        Matches match = matchService.findMatchById(id);
        if (match == null) {
            return ResponseEntity.notFound().build();
        }
        MatchDTO matchDTO = matchService.convertToDTO(match);
        return ResponseEntity.ok(matchDTO);
    }

    @PostMapping
    @Operation(summary = "Create a Match")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Match", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Matches.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO matchDTO) {
        Matches match = matchService.convertToEntity(matchDTO);
        matchService.saveMatch(match);
        URI location = URI.create("/api/matches/" + match.getId());
        return ResponseEntity.created(location).body(matchDTO);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a match by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the match", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Matches.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Match not found", content = @Content)
    })
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable Long id, @RequestBody MatchDTO matchDTO) {
        Matches updatedMatch = matchService.updateMatch(id, matchDTO);
        if (updatedMatch == null) {
            return ResponseEntity.notFound().build();
        }
        MatchDTO updatedMatchDTO = matchService.convertToDTO(updatedMatch);
        return ResponseEntity.ok(updatedMatchDTO);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a match by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the match", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Matches.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "403", description = "Operation not permitted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Match not found", content = @Content)
    })
    public ResponseEntity<?> deleteMatch(@PathVariable Long id) {
        int result = matchService.deleteMatchRest(id);
        if (result==0){
            String msg = "Match with id " + id + " deleted .";
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }else if (result==1){
            return ResponseEntity.notFound().build();
        }else if (result==2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The match cannot be deleted because it is associated with a tournament.");
        }//else if (result==3) Not necessary nowadays
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

