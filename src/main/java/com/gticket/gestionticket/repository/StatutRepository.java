package com.gticket.gestionticket.repository;


import com.gticket.gestionticket.models.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatutRepository extends JpaRepository<Statut, Long> {

    Optional<Statut> findByNom(String Nom);
}
