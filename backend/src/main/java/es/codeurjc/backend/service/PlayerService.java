package es.codeurjc.backend.service;

import es.codeurjc.backend.DTOs.PlayerDTO;
import es.codeurjc.backend.DTOs.TeamDTO;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private ConversionService conversionService;

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findPlayersTeamById(Long teamId){
        return playerRepository.findPlayerByTeamId(teamId);
    }
    public void save(Player player){
        playerRepository.save(player);
    }
    public Player saveRest(Player player){
        return playerRepository.save(player);
    }
    public List<Player> findAll() {
        return playerRepository.findAll();
    }
    public Optional<Player> findPlayerById(Long id){return playerRepository.findById(id);}
    public Page<Player> findAllPlayers(PageRequest pageRequest) {
        return playerRepository.findAll(pageRequest);
    }
    public Player findPlayerByNameAndLastName(String playerName, String lastName){return playerRepository.findPlayerByNameAndLastName(playerName, lastName);}
    public Player findPlayerByName(String playerName){return playerRepository.findPlayerByName(playerName);}
    public List<Player> findPlayerByNameSearch(String name){return playerRepository.findPlayerByNameContainingIgnoreCase(name);}
    public List<Player> findPlayerByLastNameSearch(String lastName){return playerRepository.findPlayerByLastNameContainingIgnoreCase(lastName);}
    public List<Player> findPlayerByNationalitySearch(String nationality){return playerRepository.findPlayerByNationalityContainingIgnoreCase(nationality);}
    public List<Player> findPlayerByPositionSearch(String position){return playerRepository.findPlayerByPositionContainingIgnoreCase(position);}
    public List<Player> findTopPlayersByGoals(int numberOfPlayers) {
        List<Player> players = findAll();
        players.sort(Comparator.comparingInt(Player::getGoals).reversed());
        return players.subList(0, Math.min(numberOfPlayers, players.size()));
    }
    public Player updatePlayer(Long id, Player updatedPlayer) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Player with id " + id + " not found"));

        updatePlayerFields(existingPlayer, updatedPlayer);

        return playerRepository.save(existingPlayer);
    }
    private void updatePlayerFields(Player existingPlayer, Player updatedPlayer) {
        if (updatedPlayer.getName() != null) {
            existingPlayer.setName(updatedPlayer.getName());
        }
        if (updatedPlayer.getLastName() != null) {
            existingPlayer.setLastName(updatedPlayer.getLastName());
        }
        if (updatedPlayer.getAge() != null) {
            existingPlayer.setAge(updatedPlayer.getAge());
        }
        if (updatedPlayer.getJerseyNumber() != 0) {
            existingPlayer.setJerseyNumber(updatedPlayer.getJerseyNumber());
        }
        if (updatedPlayer.getNationality() != null) {
            existingPlayer.setNationality(updatedPlayer.getNationality());
        }
        if (updatedPlayer.getGoals() != 0) {
            existingPlayer.setGoals(updatedPlayer.getGoals());
        }
        if (updatedPlayer.getPosition() != null) {
            existingPlayer.setPosition(updatedPlayer.getPosition());
        }
        if (updatedPlayer.getWeight() != null) {
            existingPlayer.setWeight(updatedPlayer.getWeight());
        }
        if (updatedPlayer.getHeight() != null) {
            existingPlayer.setHeight(updatedPlayer.getHeight());
        }
    }
    public void deletePlayerByTeamId(Long teamId){playerRepository.deletePlayerByTeamId(teamId);}
    public void deletePlayerById(Long id){playerRepository.deleteById(id);}
    public PlayerDTO convertToDTO(Player player) {
        return conversionService.convertToDTO(player, PlayerDTO.class);
    }
    public Player convertToEntity(PlayerDTO playerDTO) {
        return conversionService.convertToEntity(playerDTO, Player.class);
    }
}