package com.iuc.requests.repository;

import com.iuc.requests.dao.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestStateRepository extends JpaRepository<RequestState, Long> {}
