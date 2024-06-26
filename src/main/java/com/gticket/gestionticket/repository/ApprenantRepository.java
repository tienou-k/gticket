package com.gticket.gestionticket.repository;

import com.gticket.gestionticket.models.Apprenant;
import org.springframework.context.annotation.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

    List<Apprenant> findByRoles(Role role);
}
