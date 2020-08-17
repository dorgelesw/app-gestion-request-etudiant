package com.iuc.requests.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Staff extends User {

    @Column(name = "poste_ocuppe", length = 50)
    private String posteOccupe;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();
}
