
// UserRepository.java

package com.gticket.gestionticket.repository;
import com.gticket.gestionticket.models.Role;
import com.gticket.gestionticket.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);

    List<Utilisateur> findByRole(Role role);
}