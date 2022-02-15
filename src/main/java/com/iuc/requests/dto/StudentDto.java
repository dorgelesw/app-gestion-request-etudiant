package com.iuc.requests.dto;

import lombok.Data;

@Data
public class StudentDto {
  private  String matricule;

  private String nom;

  private String prenom;

  private String password;

  private String email;

  private String filiere;

  private String niveau ;

}
