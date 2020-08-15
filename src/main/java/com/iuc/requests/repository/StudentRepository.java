package com.iuc.requests.repository;

import com.iuc.requests.dao.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
