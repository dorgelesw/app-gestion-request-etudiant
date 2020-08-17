package com.iuc.requests.dao;

import lombok.Data;

import javax.persistence.CascadeType;
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
public class RequestType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String type;

    @OneToMany(mappedBy = "requestType", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<RequestReason> requestReasons = new ArrayList<>();

}
