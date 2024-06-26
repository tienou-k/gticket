package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.service.ApprenantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/apprenants")
@AllArgsConstructor
public class ApprenantController {

    private final ApprenantService ApprenantService;




}
