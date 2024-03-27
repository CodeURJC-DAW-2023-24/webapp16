package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<TournamentDTO>>getAllTournaments(){
        List<TournamentDTO> tournamentDTOS = tournamentService.findAllTournaments().stream()
                .map(tournamentService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tournamentDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TournamentDTO>getTournamentId(@PathVariable Long id){
        Tournament tournament = tournamentService.findTournamentById(id);
        if (tournament == null) {
            return ResponseEntity.notFound().build();
        }
        TournamentDTO tournamentDTO = tournamentService.convertToDTO(tournament);
        return ResponseEntity.ok(tournamentDTO);
    }

    @PostMapping
    public ResponseEntity<TournamentDTO>getTournamentId(@RequestBody TournamentDTO tournamentDTO){
        Tournament tournament = tournamentService.convertToEntity(tournamentDTO);
        tournament.setTournamentImageFile(tournament.URLtoBlob(tournament.getTournamentImagePath()));
        Tournament savedTournament = tournamentService.saveRest(tournament);
        TournamentDTO savedTournamentDTO = tournamentService.convertToDTO(savedTournament);
        URI location = URI.create("/api/tournaments/" + savedTournament.getId());
        return ResponseEntity.created(location).body(savedTournamentDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TournamentDTO> updateTournament(@PathVariable Long id, @RequestBody TournamentDTO tournamentDTO) {
        Tournament existingTournament = tournamentService.findTournamentById(id);
        if (existingTournament == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Tournament tournament = tournamentService.convertToEntity(tournamentDTO);
        tournament.setId(id);
        Tournament updatedTournament = tournamentService.updateTournament(id, tournament);
        TournamentDTO updatedTournamentDTO = tournamentService.convertToDTO(updatedTournament);
        return ResponseEntity.ok(updatedTournamentDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTournament(@PathVariable Long id) {
        try {
            List<Matches> matches = matchService.findMatchesByTournamentId(id);

            if (!matches.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The tournament cannot be deleted because it has associated matches.");
            }

            tournamentService.deleteTournamentById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
