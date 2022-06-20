package com.iuc.requests.repository;

import com.iuc.requests.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

  Student findByEmail(String email);

  Student findByMatricule(String matricule);
}
