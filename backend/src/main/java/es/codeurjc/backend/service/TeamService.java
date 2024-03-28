package es.codeurjc.backend.service;

import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import es.codeurjc.backend.repository.TeamRepository;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAllTeams(int page, int pageSize) throws SQLException {
        Page<Team> teamsPage = teamRepository.findAll(PageRequest.of(page, pageSize));
        for (Team team : teamsPage) {
            team.setImagePath(team.blobToString(team.getImageFile(), team));
        }
        return teamsPage.getContent();
    }

    public Team saveRest(Team team){
        return teamRepository.save(team);
    }
    public void save(Team team){
        teamRepository.save(team);
    }
    public Team findTeamById(Long id){return teamRepository.findTeamById(id);}
    public List<Team> findAll() {
        return teamRepository.findAllTeams();
    }
    public Team findTeamByName(String name){return teamRepository.findTeamByName(name);}
    public List<Team> findTournamentsByTourName(Long tourId){return teamRepository.findTournamentsByTourName(tourId);}

    public List<Team> findTeamByNameSearch(String name){return teamRepository.findTeamByNameContainingIgnoreCase(name);}

    public Page<Team> findAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }
    public List<Team> getTopTeamsByWins(int limit) {
        List<Team> teams = findAll();
        teams.sort(Comparator.comparingInt(Team::getWins).reversed());
        return teams.subList(0, Math.min(limit, teams.size()));
    }
    public Team updateTeam(Long id, Team updatedTeam) {
        Team existingTeam = teamRepository.findTeamById(id);
        if (existingTeam != null) {
            updateTeamFields(existingTeam, updatedTeam);
        } else {
            throw new NoSuchElementException("Team with id " + id + " not found");
        }
        return teamRepository.save(existingTeam);
    }
    private void updateTeamFields(Team existingTeam, Team updatedTeam) {
        if (updatedTeam.getName() != null) {
            existingTeam.setName(updatedTeam.getName());
        }
        if (updatedTeam.getCoach() != null) {
            existingTeam.setCoach(updatedTeam.getCoach());
        }
        if (updatedTeam.getStadium() != null) {
            existingTeam.setStadium(updatedTeam.getStadium());
        }
        //
        if (updatedTeam.getGamesPlayed() != 0) {
            existingTeam.setGamesPlayed(updatedTeam.getGamesPlayed());
        }
        if (updatedTeam.getWins() != 0) {
            existingTeam.setWins(updatedTeam.getWins());
        }
        if (updatedTeam.getLoses() != 0) {
            existingTeam.setLoses(updatedTeam.getLoses());
        }
        if (updatedTeam.getImageFile() != null) {
            existingTeam.setImageFile(updatedTeam.getImageFile());
        }
        if (updatedTeam.getImagePath() != null) {
            existingTeam.setImagePath(updatedTeam.getImagePath());
            existingTeam.setImageFile(existingTeam.URLtoBlob(existingTeam.getImagePath()));
        }
    }

    public void deleteTeam(Team team){teamRepository.delete(team);}
    public void deleteTeamById(Long id){teamRepository.deleteById(id);}

    public TeamDTO convertToDTO(Team team) {
        return conversionService.convertToDTO(team, TeamDTO.class);
    }

    public Team convertToEntity(TeamDTO teamDTO) {
        return conversionService.convertToEntity(teamDTO, Team.class);
    }
}

