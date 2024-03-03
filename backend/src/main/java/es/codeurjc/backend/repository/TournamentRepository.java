package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Matches;
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
    @Query("SELECT t FROM Tournament t WHERE t.name = :name")
    Tournament findTournamentByName(@Param("name") String name);
    @Query("SELECT t FROM Tournament t WHERE t.id = :id")
    Tournament findTournamentById(@Param("id") Long id);
    @Query("SELECT m FROM Matches m WHERE m.tournament.id = :id") //todos los partidos de un torneo
    List<Matches> findBracketById(@Param("id") Long id);

    @Query("SELECT m FROM Matches m WHERE m.tournament.id = :id AND m.round = :round") //devolver los partidos de una ronda de un torneo
    List<Matches> findRound( @Param("round") int round,@Param("id") Long id);
    @Query("SELECT COUNT(t) FROM Tournament t ")
    long countTournaments();
}