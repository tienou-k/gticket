package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.BaseDeConnaissance;
import com.gticket.gestionticket.repository.baseDeconnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;





@Service
@AllArgsConstructor
@Transactional
public class dbConnaissanceServiceImpl implements dbConnaissanceService {
    private final baseDeconnRepository baseDeconnRepository;

    @Override
    public BaseDeConnaissance creer(BaseDeConnaissance baseDeConnaissance) {
        return baseDeconnRepository.save(baseDeConnaissance);
    }

    @Override
    public List<BaseDeConnaissance> lire() {
        return baseDeconnRepository.findAll();
    }

    @Override
    public BaseDeConnaissance modifier(Long id, BaseDeConnaissance baseDeConnaissance) {
        return baseDeconnRepository.findById(id)
                .map(existingBaseDeConnaissance -> {
                    existingBaseDeConnaissance.setDescription(baseDeConnaissance.getDescription());
                    existingBaseDeConnaissance.setFileUrl(baseDeConnaissance.getFileUrl());
                    existingBaseDeConnaissance.setFormateur(baseDeConnaissance.getFormateur());
                    return baseDeconnRepository.save(existingBaseDeConnaissance);
                })
                .orElseThrow(() -> new RuntimeException("BaseDeConnaissance pas existant !"));
    }

    @Override
    public String supprimer(Long id) {
        baseDeconnRepository.deleteById(id);
        return "BaseDeConnaissance supprimé !";
    }

    @Override
    public BaseDeConnaissance save(BaseDeConnaissance baseDeConnaissance) {
            return baseDeconnRepository.save(baseDeConnaissance);
    }
}
