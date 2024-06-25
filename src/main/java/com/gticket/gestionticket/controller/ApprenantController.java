package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.service.serviceApprenant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/apprenants")
@AllArgsConstructor
public class ApprenantController {

    private final serviceApprenant serviceApprenant;




}
