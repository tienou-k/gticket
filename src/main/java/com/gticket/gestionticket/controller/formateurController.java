package com.gticket.gestionticket.controller;

import com.gticket.gestionticket.models.Formateur;
import com.gticket.gestionticket.service.formateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formateurs")
@AllArgsConstructor
public class formateurController {
    private final formateurService formateurService;


}
