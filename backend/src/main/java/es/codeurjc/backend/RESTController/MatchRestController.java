package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.MatchDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
