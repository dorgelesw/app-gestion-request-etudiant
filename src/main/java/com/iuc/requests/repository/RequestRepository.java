package com.iuc.requests.repository;

import com.iuc.requests.dao.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
  Request findByRequestCode(long code);
}
