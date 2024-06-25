package com.gticket.gestionticket.service;


import com.gticket.gestionticket.models.Notification;


import java.util.List;

public interface NotificationService {

    Notification creer(Notification notification);

    List<Notification> lire();

    Notification modifier(Long id, Notification notification);

    String supprimer(Long id);

}
