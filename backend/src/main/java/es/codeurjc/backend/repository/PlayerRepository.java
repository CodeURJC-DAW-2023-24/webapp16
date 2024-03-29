package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.team.id = :teamId")
    List<Player> findPlayerByTeamId(@Param("teamId") Long teamId);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Player p WHERE p.team.id = :teamId")
    void deletePlayerByTeamId(@Param("teamId") Long teamId);

    Player findPlayerByNameAndLastName (String name, String lastName);
    Player findPlayerByName(String playerName);
    List<Player> findPlayerByNameContainingIgnoreCase(String name);
    List<Player> findPlayerByLastNameContainingIgnoreCase(String lastName);

    List<Player> findPlayerByNationalityContainingIgnoreCase(String nationality);

    List<Player> findPlayerByPositionContainingIgnoreCase(String Position);


    Player findPlayerById(Long id);
}
