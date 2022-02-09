package com.iuc.requests.dto;

import lombok.Data;

@Data
public class StaffDto {

    private String matricule;

    private String nom;

    private String prenom;

    private String email;

    private String posteOccupe;

    private String filiere;

    private String password;
}
