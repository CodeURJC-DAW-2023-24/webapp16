package es.codeurjc.backend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TournamentService {
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> findAllTournaments(){
        return tournamentRepository.findAllTournaments();

    }

    public Tournament findTournamentByCup(String cup){
        return tournamentRepository.findTournamentByCup(cup);
    }

    public Tournament findTournamentById(Long id){
        return tournamentRepository.findTournamentById(id);
    }
    public Tournament findTournamentByName(String name){
        return tournamentRepository.findTournamentByName(name);
    }


    public List<Tournament> findTournamentByCupSearch(String cup){
        return tournamentRepository.findTournamentByCupContainingIgnoreCase(cup);

    }

    public List<Matches> findRound(int round, Long id){
        return tournamentRepository.findRound(round,id);
    }
    public void delete(Tournament tournament){tournamentRepository.delete(tournament);}

    public long countTournaments(){
        return tournamentRepository.countTournaments();
    }
    public void newTournament(String name, String category, String cup){
        Tournament tournament = tournamentRepository.findTournamentByName(name);
        if (tournament!=null){
            tournament.setCategory(category);
            tournament.setCup(cup);
            tournamentRepository.save(tournament);
        }else {
            tournamentRepository.save(new Tournament(name, category, cup, null));
        }
    }
    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        Tournament existingTournament = tournamentRepository.findTournamentById(id);
        if (existingTournament != null) {
            if (updatedTournament.getName() != null) {
                existingTournament.setName(updatedTournament.getName());
            }
            if (updatedTournament.getCategory() != null) {
                existingTournament.setCategory(updatedTournament.getCategory());
            }
            if (updatedTournament.getCup() != null) {
                existingTournament.setCup(updatedTournament.getCup());
            }
            if (updatedTournament.getTournamentImagePath() != null) {
                existingTournament.setTournamentImagePath(updatedTournament.getTournamentImagePath());
                existingTournament.setTournamentImageFile(existingTournament.URLtoBlob(existingTournament.getTournamentImagePath()));
            }
        } else {
            throw new NoSuchElementException("Tournament with id " + id + " not found");
        }
        return tournamentRepository.save(existingTournament);
    }
    public void save(Tournament tournament){
        tournamentRepository.save(tournament);
    }
    public Tournament saveRest(Tournament tournament){
        return tournamentRepository.save(tournament);
    }
    public TournamentDTO convertToDTO(Tournament tournament) {
        return conversionService.convertToDTO(tournament, TournamentDTO.class);
    }
    public void deleteTournamentById(Long id){tournamentRepository.deleteById(id);}
    public Tournament convertToEntity(TournamentDTO tournamentDTO) {
        return conversionService.convertToEntity(tournamentDTO, Tournament.class);
    }
}
