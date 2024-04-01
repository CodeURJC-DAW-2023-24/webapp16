package es.codeurjc.backend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import es.codeurjc.backend.DTOs.MatchDTO;
import es.codeurjc.backend.model.*;
import es.codeurjc.backend.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;
    private ObjectMapper objectMapper;
    public MatchService(ObjectMapper objectMapper){this.objectMapper = objectMapper;}
    public List<Matches> findAllMatches(){
        return matchRepository.findAll();
    }
    public Matches findMatchById(Long id){
        return matchRepository.findMatchById(id);
    }
    public List<Matches> findMatchesByTournamentId(Long id){
        return matchRepository.findMatchesByTournamentId(id);
    }
    public List<Matches> findMatchByRound(int round){
        return matchRepository.findMatchesByRound(round);
    }
    public Matches saveMatch(Matches match){
        return matchRepository.save(match);
    }

    public List<Matches> findMatchByRoundAndCup(int i, Tournament tournament) { return matchRepository.findMatchesByRoundAndTournament(i, tournament);
    }
    public List<Matches> findMatchesByTeamId(Long id) { List<Matches> matches = matchRepository.findByLocalTeamId(id);
        matches.addAll(matchRepository.findByVisitingTeamId(id));
        return matches;
    }
    public List<Matches> generateMatches(List<Team> teams, Tournament tournament) {
        List<Matches> matches = new ArrayList<>();

        for (int i = 0; i < teams.size() - 1; i += 2) {
            Team homeTeam = teams.get(i);
            Team awayTeam = teams.get(i + 1);

            Matches match = new Matches(homeTeam, awayTeam, tournament, 0, 0, 1);
            matches.add(match);

        }

        return matches;
    }
    public MatchDTO convertToDTO(Matches matches) {
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        return objectMapper.convertValue(matches, MatchDTO.class);
    }

    public Matches convertToEntity(MatchDTO matchDTO) {
        return objectMapper.convertValue(matchDTO, Matches.class);
    }

    public Matches updateMatch(Long id, MatchDTO updatedMatchDTO) {
        Matches existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Match with id " + id + " not found"));

        if (updatedMatchDTO.getLocalTeam() != null) {
            existingMatch.setLocalTeam(updatedMatchDTO.getLocalTeam());
        }
        if (updatedMatchDTO.getVisitingTeam() != null) {
            existingMatch.setVisitingTeam(updatedMatchDTO.getVisitingTeam());
        }
        if (updatedMatchDTO.getLocalGoals() != 0) {
            existingMatch.setLocalGoals(updatedMatchDTO.getLocalGoals());
        }
        if (updatedMatchDTO.getVisitingGoals() != 0) {
            existingMatch.setVisitingGoals(updatedMatchDTO.getVisitingGoals());
        }
        if (updatedMatchDTO.getRound() != 0) {
            existingMatch.setRound(updatedMatchDTO.getRound());
        }
        if (updatedMatchDTO.getTournament() != null) {
            existingMatch.setTournament(updatedMatchDTO.getTournament());
        }

        return matchRepository.save(existingMatch);
    }

 public void deleteMatch(Matches matches) {
        matchRepository.delete(matches);
 }
    public int deleteMatchRest(Long id) {
        try {
            Matches match = this.findMatchById(id);

            if (match == null) {
                return 1;
            }
            Tournament tournament = match.getTournament();
            if (tournament != null) {
                return 2;
            }
            this.deleteMatch(match);
            return 0;
        } catch (Exception e) {
            return 3;
        }
    }


    public List<Matches> saveAll(List<Matches> matches){
        return matchRepository.saveAll(matches);
    }
}
