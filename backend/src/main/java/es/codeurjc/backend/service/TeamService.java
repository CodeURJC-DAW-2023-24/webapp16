package es.codeurjc.backend.service;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.codeurjc.backend.repository.TeamRepository;

import java.security.SecureRandom;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public void save(Team team){
        teamRepository.save(team);
    }
    public List<Team> findAll() {
        return teamRepository.findAllTeams();
    }
    public Team findTeamByName(String name){return teamRepository.findTeamByName(name);}

    public List<Team> findTeamByNameSearch(String name){return teamRepository.findTeamByNameContainingIgnoreCase(name);}
}
