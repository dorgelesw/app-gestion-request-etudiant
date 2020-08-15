package com.iuc.requests.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
@DynamicUpdate
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private Long requestRef;

    @Column(nullable = false)
    private String requestType;

    @Column(nullable = false)
    private String requestReason;

    @Column
    private String comment;

    @OneToOne
    @JoinColumn(name = "requestState_id")
    private RequestState requestState;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
}
