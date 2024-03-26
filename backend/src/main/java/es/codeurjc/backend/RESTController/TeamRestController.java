package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamRestController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "4") int pageSize) throws SQLException {
        Page<Team> teamsPage = teamService.findAllTeams(PageRequest.of(page, pageSize));
        for (Team currentTeam : teamsPage.getContent()) {
            currentTeam.setImagePath(currentTeam.blobToString(currentTeam.getImageFile(), currentTeam));
        }
        return ResponseEntity.ok(teamsPage.getContent());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        Optional<Team> optionalTeam = teamService.findTeamById(id);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            TeamDTO teamDTO = teamService.convertToDTO(team);
            return ResponseEntity.ok(teamDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
