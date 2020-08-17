package com.iuc.requests.repository;

import com.iuc.requests.dao.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, Long> {

    Staff findByEmail(String email);

    Staff findByMatricule(String matricule);
}
