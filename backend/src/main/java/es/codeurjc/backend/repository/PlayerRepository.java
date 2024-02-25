package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.team = :teamId")
    List<Player> findPlayerByTeamId(@Param("teamId") Long teamId);
}
