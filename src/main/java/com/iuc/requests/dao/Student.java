package com.iuc.requests.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 15, nullable = false)
    private String faculty;

    @Column(length = 2, nullable = false)
    private String level;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();
}
