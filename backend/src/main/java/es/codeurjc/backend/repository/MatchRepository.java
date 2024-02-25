package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Matches, Long> {


    @Query("SELECT m FROM Matches m WHERE m.id = :id")
    Matches findMatchById(@Param("id") Long id);
}
