package com.iuc.requests.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Entity
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 13, unique = true, nullable = false)
    @Pattern(regexp = "[a-zA-Z0-9]")
    private String matricule;

    @Column(length = 50, nullable = false)
    private String nom;

    @Column(length = 50)
    private String prenom;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Column(length = 15,nullable = false)
    @Pattern(regexp = "[a-zA-Z0-9]")
    private String filiere;
}
