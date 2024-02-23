package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface playerRepository extends JpaRepository<Player, Long> {
}
