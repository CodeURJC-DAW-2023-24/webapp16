package es.codeurjc.backend.service;


import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TournamentService {

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
    public void save(Tournament tournament){
        tournamentRepository.save(tournament);
    }
}
