package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query("SELECT t FROM Tournament t")
    List<Tournament> findAllTournaments();
}
