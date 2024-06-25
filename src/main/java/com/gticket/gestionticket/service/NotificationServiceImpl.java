package com.gticket.gestionticket.service;


import com.gticket.gestionticket.models.Notification;
import com.gticket.gestionticket.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;


    @Override
    public Notification creer(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> lire() {
        return notificationRepository.findAll();
    }

    public Notification modifier(Long id, Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public String supprimer(Long id){
        notificationRepository.deleteById(Math.toIntExact(id));
        return "Notification supprimé !";
    }
}
