package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<List<TournamentDTO>>getAllTournaments(){
        List<TournamentDTO> tournamentDTOS = tournamentService.findAllTournaments().stream()
                .map(tournamentService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tournamentDTOS);
    }
}
