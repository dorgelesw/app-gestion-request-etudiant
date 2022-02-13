package com.iuc.requests.repository;

import com.iuc.requests.dao.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {

  Staff findByEmail(String email);

  Staff findByMatricule(String matricule);
}
