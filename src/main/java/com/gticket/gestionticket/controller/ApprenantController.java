package com.gticket.gestionticket.controller;

import com.gticket.gestionticket.modele.Apprenant;
import com.gticket.gestionticket.service.serviceApprenant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apprenants")
@AllArgsConstructor
public class ApprenantController {

    private final serviceApprenant serviceApprenant;

    @PostMapping("/createApprenant")
    public Apprenant create(@RequestBody Apprenant apprenant) {
        return serviceApprenant.creer(apprenant);
    }

    @GetMapping("/readApprenant")
    public List<Apprenant> read() {
        return serviceApprenant.lire();
    }

    @GetMapping("/readApprenantByRole/{role}")
    public List<Apprenant> readByRole(@PathVariable String role) {
        return serviceApprenant.lireParRole(role);
    }

    @PutMapping("/updateApprenant/{id}")
    public Apprenant update(@PathVariable Long id, @RequestBody Apprenant apprenant) {
        return serviceApprenant.modifier(id, apprenant);
    }

    @DeleteMapping("/deleteApprenant/{id}")
    public String delete(@PathVariable Long id) {
        return serviceApprenant.supprimer(id);
    }
}
