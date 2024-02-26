package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t")
    List<Team> findAllTeams();


    Team findTeamByName (String name);

    List<Team> findTeamByNameContainingIgnoreCase(String name);


}
