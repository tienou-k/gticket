package com.gticket.gestionticket.repository;

import com.gticket.gestionticket.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
