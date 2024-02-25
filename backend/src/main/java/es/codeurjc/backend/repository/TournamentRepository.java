package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query("SELECT t FROM Tournament t")
    List<Tournament> findAllTournaments();


    @Query("SELECT t FROM Tournament t WHERE t.cup = :cup")
    Tournament findTournamentByCup(@Param("cup") String cup);
}
