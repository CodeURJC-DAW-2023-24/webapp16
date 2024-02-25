package es.codeurjc.backend.repository;

import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT t.pass FROM UserPasswords t WHERE t.user = :user")
    String getUserCredential(@Param("user") String user);
}
