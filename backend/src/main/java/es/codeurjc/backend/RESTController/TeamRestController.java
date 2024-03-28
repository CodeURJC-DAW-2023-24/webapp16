package es.codeurjc.backend.RESTController;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamRestController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "4") int pageSize) throws SQLException {
        return ResponseEntity.ok(teamService.findAllTeams(page, pageSize));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        Team existingTeam = teamService.findTeamById(id);
        if (existingTeam != null) {
            TeamDTO teamDTO = teamService.convertToDTO(existingTeam);
            return ResponseEntity.ok(teamDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<String>getTeamImage(@PathVariable Long id){
        try {
            Team team = teamService.findTeamById(id);
            String imageUrl = team.getImagePath();
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting the image link: " + e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        Team team = teamService.convertToEntity(teamDTO);
        team.setImageFile(team.URLtoBlob(team.getImagePath()));
        Team savedTeam = teamService.saveRest(team);
        TeamDTO savedTeamDTO = teamService.convertToDTO(savedTeam);
        URI location = URI.create("/api/teams/" + savedTeam.getId());
        return ResponseEntity.created(location).body(savedTeamDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        Team existingTeam = teamService.findTeamById(id);
        if (existingTeam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Team team = teamService.convertToEntity(teamDTO);
        team.setId(id);
        Team updatedTeam = teamService.updateTeam(id, team);
        TeamDTO updatedTeamDTO = teamService.convertToDTO(updatedTeam);
        return ResponseEntity.ok(updatedTeamDTO);
    }
    @PutMapping("/{id}/image")
    public ResponseEntity<String> updateTeamImage(@PathVariable Long id, @RequestBody String imageUrl) {
        Team existingTeam = teamService.findTeamById(id);
        if (existingTeam == null) {
            return new ResponseEntity<>("Team with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }

        try {
            existingTeam.setImagePath(imageUrl);
            Blob imageBlob = existingTeam.URLtoBlob(imageUrl);
            existingTeam.setImageFile(imageBlob);
            teamService.save(existingTeam);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update image for team with ID " + id + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("URL of successfully updated image for the team with ID: " + id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        try {
            List<Player> players = playerService.findPlayersTeamById(id);

            for (Player player : players) {
                player.setTeam(null);
                playerService.save(player);
            }

            List<Matches> matches = matchService.findMatchesByTeamId(id);
            if (!matches.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The team cannot be eliminated because it has associated matches.");
            }

            teamService.deleteTeamById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
