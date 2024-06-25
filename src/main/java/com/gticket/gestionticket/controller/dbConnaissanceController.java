package com.gticket.gestionticket.controller;

import com.gticket.gestionticket.models.BaseDeConnaissance;
import com.gticket.gestionticket.service.dbConnaissanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/db")
@AllArgsConstructor
public class dbConnaissanceController {
    private final dbConnaissanceService dbConnaissanceService;

    @PostMapping(value = "/create", consumes = {"*/*"})
    public BaseDeConnaissance creer(BaseDeConnaissance baseDeConnaissance) {
        return dbConnaissanceService.save(baseDeConnaissance);
    }


    @GetMapping("/list-db-connaissance")
    public List<BaseDeConnaissance> lire() {
        return dbConnaissanceService.lire();
    }

    @PutMapping( "/modifier-db-connaissance/{id}")
    public BaseDeConnaissance modifier(@PathVariable Long id, @RequestBody BaseDeConnaissance baseDeConnaissance) {
        return dbConnaissanceService.modifier(id, baseDeConnaissance);
    }

    @DeleteMapping("/supprimer-db-connaissance/{id}")
    public String supprimer(@PathVariable Long id) {
        return dbConnaissanceService.supprimer(id);
    }
}
