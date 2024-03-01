package es.codeurjc.backend.service;


import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Report;
import es.codeurjc.backend.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public Matches findMatchById(Long id){
        return matchRepository.findMatchById(id);

    }

    public Matches saveMatch(Matches match){
        return matchRepository.save(match);
    }
}
