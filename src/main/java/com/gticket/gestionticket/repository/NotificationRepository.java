package com.gticket.gestionticket.repository;

import com.gticket.gestionticket.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
