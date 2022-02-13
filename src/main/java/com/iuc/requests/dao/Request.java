package com.iuc.requests.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Getter
@Setter
public class Request {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "request_code", length = 50, unique = true, nullable = false)
  private Long requestCode;

  @Column(nullable = false)
  private String requestType;

  @Column(nullable = false)
  private String requestReason;

  @Column private String comment;

  @Column(nullable = false)
  private String requestStatut;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "staff_id")
  private Staff staff;
}
