package com.gticket.gestionticket.repository;



import com.gticket.gestionticket.models.Priorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrioriteRepository extends JpaRepository<Priorite, Long> {
    Optional<Priorite> findByNom(String Nom);
}
