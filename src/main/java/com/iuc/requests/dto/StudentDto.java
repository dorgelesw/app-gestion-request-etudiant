package com.iuc.requests.dto;

import lombok.Data;

@Data
public class StudentDto {

    private String matricule;

    private String nom;

    private String prenom;

    private String email;

    private String niveau;

    private String filiere;
}