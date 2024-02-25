package es.codeurjc.backend.service;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findPlayerTeamById(Long teamId){

        return playerRepository.findPlayerByTeamId(teamId);
    }
    public Player findPlayerByName(String playerName){return playerRepository.findPlayerByName(playerName);}
}