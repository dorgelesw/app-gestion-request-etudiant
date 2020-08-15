package com.iuc.requests.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
public class User {

    @Column(length = 13, unique = true, nullable = false)
    private String userRegistration;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50)
    private String firstName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    @Email
    private String email;
}
