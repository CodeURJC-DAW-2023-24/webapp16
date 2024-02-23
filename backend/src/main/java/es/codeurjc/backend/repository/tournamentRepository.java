package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tournamentRepository extends JpaRepository<Tournament, Long> {
}
