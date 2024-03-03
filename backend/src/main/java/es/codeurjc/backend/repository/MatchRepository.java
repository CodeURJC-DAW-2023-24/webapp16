package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Matches, Long> {

    @Query("SELECT m FROM Matches m WHERE m.id = :id")
    Matches findMatchById(@Param("id") Long id);

    @Query("SELECT m FROM Matches m WHERE m.id = :id AND m.tournament.id = :tournament_id")
    Matches findMatchById2(@Param("tournament_id") Long tournament_id, @Param("id") Long id);

    List<Matches> findMatchesByRound(int round);

    List<Matches> findMatchesByRoundAndTournament(int round, Tournament tournament);
}
