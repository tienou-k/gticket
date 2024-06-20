package com.gticket.gestionticket.models;


import jakarta.persistence.*;



@Entity
public class BaseDeConnaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String fileUrl;




}
