package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {
}
