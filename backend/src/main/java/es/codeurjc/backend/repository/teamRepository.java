package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface teamRepository extends JpaRepository<Team, Long> {
}
