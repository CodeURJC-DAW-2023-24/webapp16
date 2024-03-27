package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.MatchDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class MatchRestController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        Matches match = matchService.findMatchById(id);
        if (match == null) {
            return ResponseEntity.notFound().build();
        }
        MatchDTO matchDTO = matchService.convertToDTO(match);
        return ResponseEntity.ok(matchDTO);
    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO matchDTO) {
        Matches match = matchService.convertToEntity(matchDTO);
        Matches savedMatch = matchService.saveMatch(match);
        MatchDTO savedMatchDTO = matchService.convertToDTO(savedMatch);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMatchDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable Long id, @RequestBody MatchDTO matchDTO) {
        Matches updatedMatch = matchService.updateMatch(id, matchDTO);

        if (updatedMatch == null) {
            return ResponseEntity.notFound().build();
        }

        MatchDTO updatedMatchDTO = matchService.convertToDTO(updatedMatch);

        return ResponseEntity.ok(updatedMatchDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable Long id) {
        try {
            Matches match = matchService.findMatchById(id);

            if (match == null) {
                return ResponseEntity.notFound().build();
            }

            Tournament tournament = match.getTournament();
            if (tournament != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The match cannot be deleted because it is associated with a tournament.");
            }

            matchService.deleteMatch(match);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

