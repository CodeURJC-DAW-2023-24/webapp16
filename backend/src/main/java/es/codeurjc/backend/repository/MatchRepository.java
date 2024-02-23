package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Matches, Long> {
}
