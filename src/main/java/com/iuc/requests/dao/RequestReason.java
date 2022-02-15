package com.iuc.requests.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Getter
@Setter
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
