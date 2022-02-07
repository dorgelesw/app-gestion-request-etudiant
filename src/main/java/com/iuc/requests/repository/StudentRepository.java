package com.iuc.requests.repository;

import com.iuc.requests.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

  Student findByEmail(String email);

  Student findByMatricule(String matricule);

  @Query("select s FROM Student s where  s.filiere = ?1")
  List<Student> findAllByFiliere(String filiere);
}
