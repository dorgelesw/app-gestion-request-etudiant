package com.iuc.requests.repository;

import com.iuc.requests.dao.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
