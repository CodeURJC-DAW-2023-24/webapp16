package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.PlayerDTO;
import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/players")
public class PlayerRestController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "4") int pageSize) {
        Page<Player> playersPage = playerService.findAllPlayers(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(playersPage.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        Optional<Player> optionalPlayer = playerService.findPlayerById(id);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            PlayerDTO playerDTO = playerService.convertToDTO(player);
            return ResponseEntity.ok(playerDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        Player player = playerService.convertToEntity(playerDTO);
        Player savedPlayer = playerService.saveRest(player);
        PlayerDTO savedPlayerDTO = playerService.convertToDTO(savedPlayer);
        URI location = URI.create("/api/players/" + savedPlayer.getId());
        return ResponseEntity.created(location).body(savedPlayerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Player player = playerService.convertToEntity(playerDTO);
        player.setId(id);
        Player updatedPlayer = playerService.updatePlayer(id, player);
        PlayerDTO updatedPlayerDTO = playerService.convertToDTO(updatedPlayer);
        return ResponseEntity.ok(updatedPlayerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }
}
