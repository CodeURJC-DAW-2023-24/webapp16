package es.codeurjc.backend.service;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findPlayerTeamById(Long teamId){

        return playerRepository.findPlayerByTeamId(teamId);
    }
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Page<Player> findAllPlayers(PageRequest pageRequest) {
        return playerRepository.findAll(pageRequest);
    }
    public Player findPlayerByNameAndLastName(String playerName, String lastName){return playerRepository.findPlayerByNameAndLastName(playerName, lastName);}
    public Player findPlayerByName(String playerName){return playerRepository.findPlayerByName(playerName);}
    public List<Player> findPlayerByNameSearch(String name){return playerRepository.findPlayerByNameContainingIgnoreCase(name);}
    public List<Player> findPlayerByLastNameSearch(String lastName){return playerRepository.findPlayerByLastNameContainingIgnoreCase(lastName);}
    public List<Player> findPlayerByNationalitySearch(String nationality){return playerRepository.findPlayerByNationalityContainingIgnoreCase(nationality);}
    public List<Player> findPlayerByPositionSearch(String position){return playerRepository.findPlayerByPositionContainingIgnoreCase(position);}
}