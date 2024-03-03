package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t")
    List<Team> findAllTeams();
    @Query("SELECT t FROM Team t WHERE t.tournament.id = :tour")
    List<Team> findTournamentsByTourName(@Param("tour") Long tour);

    Team findTeamByName (String name);

    List<Team> findTeamByNameContainingIgnoreCase(String name);

    Page<Team> findAll(Pageable pageable);

}


