package com.iuc.requests.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Getter
@Setter
public class RequestType {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 30, unique = true, nullable = false)
  private String type;

  @OneToMany(mappedBy = "requestType", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private List<RequestReason> requestReasons = new ArrayList<>();
}
