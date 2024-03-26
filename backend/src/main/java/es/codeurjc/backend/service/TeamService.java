package es.codeurjc.backend.service;

import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import es.codeurjc.backend.repository.TeamRepository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private TeamRepository teamRepository;

    public void save(Team team){
        teamRepository.save(team);
    }
    public Optional<Team> findTeamById(Long id){return teamRepository.findById(id);}
    public List<Team> findAll() {
        return teamRepository.findAllTeams();
    }
    public Team findTeamByName(String name){return teamRepository.findTeamByName(name);}
    public List<Team> findTournamentsByTourName(Long tourId){return teamRepository.findTournamentsByTourName(tourId);}

    public List<Team> findTeamByNameSearch(String name){return teamRepository.findTeamByNameContainingIgnoreCase(name);}

    public Page<Team> findAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }
    public void deleteTeam(Team team){teamRepository.delete(team);}

    public TeamDTO convertToDTO(Team team) {
        return conversionService.convertToDTO(team, TeamDTO.class);
    }

    public Team convertToEntity(TeamDTO teamDTO) {
        return conversionService.convertToEntity(teamDTO, Team.class);
    }
}

