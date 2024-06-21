package com.gticket.gestionticket.repository;

import com.gticket.gestionticket.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Optional<Categorie> findByNom(String Nom);
}
