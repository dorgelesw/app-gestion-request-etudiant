package com.iuc.requests.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@MappedSuperclass
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 13, unique = true, nullable = false)
  private String matricule;

  @Column(length = 50, nullable = false)
  private String nom;

  @Column(length = 50)
  private String prenom;

  private String password;

  @Column(unique = true)
  @Email
  private String email;

  @Column private String filiere;
}
