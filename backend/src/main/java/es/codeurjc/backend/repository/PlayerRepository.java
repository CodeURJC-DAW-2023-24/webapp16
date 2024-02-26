package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.team.id = :teamId")
    List<Player> findPlayerByTeamId(@Param("teamId") Long teamId);

    Player findPlayerByName (String playerName);
    @Query("SELECT p FROM Player p")
    List<Player> findAllPlayers();
}
