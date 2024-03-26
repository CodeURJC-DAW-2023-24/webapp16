package es.codeurjc.backend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.DTOs.MatchDTO;
import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Report;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    private ObjectMapper objectMapper;
    public MatchService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public Matches findMatchById(Long id){
        return matchRepository.findMatchById(id);

    }
    public List<Matches> findMatchByRound(int round){
        return matchRepository.findMatchesByRound(round);
    }
    public Matches saveMatch(Matches match){
        return matchRepository.save(match);
    }

    public List<Matches> findMatchByRoundAndCup(int i, Tournament tournament) { return matchRepository.findMatchesByRoundAndTournament(i, tournament);
    }

    public MatchDTO convertToDTO(Matches matches) {
        return objectMapper.convertValue(matches, MatchDTO.class);
    }

    public Matches convertToEntity(MatchDTO matchDTO) {
        return objectMapper.convertValue(matchDTO, Matches.class);
    }
}
