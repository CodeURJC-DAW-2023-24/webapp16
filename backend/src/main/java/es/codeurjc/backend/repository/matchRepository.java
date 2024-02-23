package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface matchRepository extends JpaRepository<Matches, Long> {
}
