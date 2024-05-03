package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.PlayerDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/players")
public class PlayerRestController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ReportService reportService;

    @GetMapping
    @Operation(summary = "Get all players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found players", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<List<Player>> getAllPlayers(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "4") int pageSize) {
        return ResponseEntity.ok(playerService.findAllPlayers(PageRequest.of(page, pageSize)).getContent());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a player by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the player", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)
    })
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        Player optionalPlayer = playerService.findPlayerById(id);
        if (optionalPlayer != null) {
            return ResponseEntity.ok(playerService.convertToDTO(optionalPlayer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a Player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Player", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        Player player = playerService.convertToEntity(playerDTO);
        playerService.save(player);
        URI location = URI.create("/api/players/" + player.getId());
        return ResponseEntity.created(location).body(playerDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a player by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the player", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)
    })
    public ResponseEntity<?> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Player existingPlayer = playerService.findPlayerById(id);
        if (existingPlayer == null) {
            return new ResponseEntity<>("Player with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }else
            return ResponseEntity.ok(playerService.convertToDTO(
                playerService.updatePlayer(id, playerService.convertToEntity(playerDTO))    ));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a player by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the player", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "403", description = "Operation not permitted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)
    })
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        Player player = playerService.findPlayerById(id);
        if (player!=null) {
            playerService.deletePlayerById(id);
            String msg = "Player with id " + id + " deleted .";
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }else {
            String msg = "Player with id " + id + " does not exist .";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PlayerDTO>>  getTPlayersByTeamId(@PathVariable Long teamId){
        List<PlayerDTO> playerDTOS = playerService.findPlayersTeamById(teamId).stream()
                .map(playerService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(playerDTOS);
    }
}
