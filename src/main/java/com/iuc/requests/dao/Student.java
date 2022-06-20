package com.iuc.requests.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Getter
@Setter
public class Student extends User {

  @Column(length = 2)
  private String niveau;

  @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
  private List<Request> requests = new ArrayList<>();
}
