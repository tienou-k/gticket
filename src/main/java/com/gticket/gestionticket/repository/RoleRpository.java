package com.gticket.gestionticket.repository;

import com.gticket.gestionticket.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRpository extends JpaRepository<Role, Long> {

    static List<Role> findByNom(String nom){
        return null;
    }

    // ............
}
