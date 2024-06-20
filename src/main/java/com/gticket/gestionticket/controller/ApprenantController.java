package com.gticket.gestionticket.controller;

import com.gticket.gestionticket.models.Apprenant;
import com.gticket.gestionticket.service.serviceApprenant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apprenants")
@AllArgsConstructor
public class ApprenantController {

    private final serviceApprenant serviceApprenant;




}
