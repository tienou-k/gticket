package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.BaseDeConnaissance;
import com.gticket.gestionticket.repository.BaseDeConnaissanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;





@Service
@AllArgsConstructor
@Transactional
public class dbConnaissanceServiceImpl implements dbConnaissanceService {
    private final BaseDeConnaissanceRepository baseDeConnaissanceRepository;


    @Override
    public BaseDeConnaissance creer(BaseDeConnaissance baseDeConnaissance) {
        return baseDeConnaissanceRepository.save(baseDeConnaissance);
    }

    public List<BaseDeConnaissance> lire() {
        return baseDeConnaissanceRepository.findAll();
    }

    public BaseDeConnaissance modifier(Long id, BaseDeConnaissance baseDeConnaissance) {
        return baseDeConnaissanceRepository.save(baseDeConnaissance);
    }

    @Override
    public String supprimer(Long id){
        baseDeConnaissanceRepository.deleteById((long) Math.toIntExact(id));
        return "baseDeConnaissance supprimé !";
    }

}
