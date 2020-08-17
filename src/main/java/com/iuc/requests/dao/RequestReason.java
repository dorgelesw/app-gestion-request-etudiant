package com.iuc.requests.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class RequestReason {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String motif;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requestType_id")
    private RequestType requestType;
}
