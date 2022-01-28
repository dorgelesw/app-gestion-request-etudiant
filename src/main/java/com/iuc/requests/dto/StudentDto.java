package com.iuc.requests.dto;

import com.iuc.requests.dao.Request;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDto {

    private  String matricule;

    private String nom;

    private String prenom;

    private String password;

    private String email;

    private String filiere;

    private String niveau ;

    private List<Request> requests = new ArrayList<>();
}
