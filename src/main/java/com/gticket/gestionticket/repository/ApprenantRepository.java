package com.gticket.gestionticket.repository;

import com.gticket.gestionticket.modele.Apprenant;
import org.springframework.context.annotation.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

    List<Apprenant> findByRole(Role role);
}
