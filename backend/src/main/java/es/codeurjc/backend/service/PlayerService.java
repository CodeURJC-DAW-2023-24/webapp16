package es.codeurjc.backend.service;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public List<Player> findPlayerTeamById(Long teamId){

        return playerRepository.findPlayerByTeamId(teamId);
    }
}
