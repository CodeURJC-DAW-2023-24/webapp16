package es.codeurjc.backend.service;


import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;


    public List<Tournament> findAllTournaments(){
        return tournamentRepository.findAllTournaments();

    }
}
