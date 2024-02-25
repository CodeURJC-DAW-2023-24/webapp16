package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.UserPasswords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPasswordsRepository extends JpaRepository<UserPasswords, Long> {
}
