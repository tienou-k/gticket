package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.BaseDeConnaissance;


import java.util.List;

public interface dbConnaissanceService {

    BaseDeConnaissance creer(BaseDeConnaissance baseDeConnaissance);
    List<BaseDeConnaissance> lire();
    BaseDeConnaissance modifier(Long id, BaseDeConnaissance baseDeConnaissance);
    String supprimer(Long id);


}
