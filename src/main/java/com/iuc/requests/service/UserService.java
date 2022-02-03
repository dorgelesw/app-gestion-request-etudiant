package com.iuc.requests.service;

import com.iuc.requests.dao.Staff;
import com.iuc.requests.dao.Student;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import com.sun.tools.sjavac.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired private StaffRepository staffRepository;

  @Autowired private StudentRepository studentRepository;

  @Autowired ModelMapper modelMapper;

  public List<StaffDto> findAllStaffs() {

    Iterable<Staff> staffs = staffRepository.findAll();
    List<StaffDto> staffsDto = new ArrayList<>();
    staffs.forEach(
        staff -> {
          staffsDto.add(modelMapper.map(staff, StaffDto.class));
        });

    return staffsDto;
  }

  public StaffDto createStaff(StaffDto staffDto) {

    try {
      return modelMapper.map(
          staffRepository.save(modelMapper.map(staffDto, Staff.class)), StaffDto.class);
    } catch (Exception e) {
      return null;
    }
  }

  public StaffDto findStaffByEmail(String email) {

    try {
      return modelMapper.map(staffRepository.findByEmail(email), StaffDto.class);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  public StaffDto findStaffByMatricule(String userRegistration) {

    try {
      return modelMapper.map(staffRepository.findByMatricule(userRegistration), StaffDto.class);
    } catch (Exception e) {
      return null;
    }
  }

  public void deleteStaffByEmail(String email) {
    try {
      staffRepository.delete(staffRepository.findByEmail(email));
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public void deleteStaffByMatricule(String matricule) {
    try {
      staffRepository.delete(staffRepository.findByMatricule(matricule));
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public StaffDto updateStaff(StaffDto staffDto) {

    Staff currentStaff = modelMapper.map(staffDto, Staff.class);
    Optional<Staff> staffDb = staffRepository.findById(currentStaff.getId());

    if (staffDb.isPresent()) {
      if (!currentStaff.getEmail().equals(staffDb.get().getEmail())) {
        staffDb.get().setEmail(currentStaff.getEmail());
      }
      if (!currentStaff.getFiliere().equals(staffDb.get().getFiliere())) {
        staffDb.get().setFiliere(currentStaff.getFiliere());
      }
      if (!currentStaff.getPosteOccupe().equals(staffDb.get().getPosteOccupe())) {
        staffDb.get().setPosteOccupe(currentStaff.getPosteOccupe());
      }

      if (!currentStaff.getMatricule().equals(staffDb.get().getMatricule())) {
        staffDb.get().setMatricule(currentStaff.getMatricule());
      }
      if (!currentStaff.getNom().equals(staffDb.get().getNom())) {
        staffDb.get().setNom(currentStaff.getNom());
      }
      if (!currentStaff.getPrenom().equals(staffDb.get().getPrenom())) {
        staffDb.get().setNom(currentStaff.getNom());
      }
      if (!currentStaff.getPassword().equals(staffDb.get().getPassword())) {
        staffDb.get().setPassword(currentStaff.getPassword());
      }
      return modelMapper.map(staffRepository.save(staffDb.get()), StaffDto.class);

    } else {
      return null;
    }
  }

  public StudentDto updateStudent(StudentDto studentDto) {

    Student currentStudent = modelMapper.map(studentDto, Student.class);
    Optional<Student> studentDb = studentRepository.findById(currentStudent.getId());

    if (studentDb.isPresent()) {

      if (!currentStudent.getEmail().equals(studentDb.get().getEmail())) {
        studentDb.get().setEmail(currentStudent.getEmail());
      }
      if (!currentStudent.getNiveau().equals(studentDb.get().getNiveau())) {
        studentDb.get().setNiveau(currentStudent.getNiveau());
      }
      if (!currentStudent.getNom().equals(studentDb.get().getNom())) {
        studentDb.get().setNom(currentStudent.getNom());
      }
      if (!currentStudent.getPrenom().equals(studentDb.get().getPrenom())) {
        studentDb.get().setPrenom(currentStudent.getPrenom());
      }
      if (!currentStudent.getMatricule().equals(studentDb.get().getMatricule())) {
        studentDb.get().setMatricule(currentStudent.getMatricule());
      }
      if (!currentStudent.getPassword().equals(studentDb.get().getPassword())) {
        studentDb.get().setPassword(currentStudent.getPassword());
      }
      if (!currentStudent.getNiveau().equals(studentDb.get().getNiveau())) {
        studentDb.get().setNiveau(currentStudent.getNiveau());
      }
      return modelMapper.map(studentRepository.save(studentDb.get()), StudentDto.class);
    } else {
      return null;
    }
  }

  public StudentDto findStrudentByEmail(String email) {
    try {
      return modelMapper.map(studentRepository.findStudentByEmail(email), StudentDto.class);
    } catch (Exception e) {
      return null;
    }
  }

  public StudentDto findStudentByMatricule(String matricule) {
    try {
      return modelMapper.map(studentRepository.findStudentByMatricule(matricule), StudentDto.class);
    } catch (Exception e) {
      return null;
    }
  }

  public List<StudentDto> findAllStudentByFiliere(String filiere) {
    List<StudentDto> studentDtoList = new ArrayList<>();
    try {
      Iterable<Student> students = studentRepository.findAllByFiliere(filiere);

      students.forEach(
          student -> {
            studentDtoList.add(modelMapper.map(student, StudentDto.class));
          });
      return studentDtoList;
    } catch (Exception e) {
      return studentDtoList;
    }
  }

  public StudentDto createStudent(StudentDto studentDto) {

    try {
      return modelMapper.map(
          studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentDto.class);
    } catch (Exception e) {
      return null;
    }
  }

  public void deleteStudentByMatricule(String matricule) {
    try {
      studentRepository.delete(studentRepository.findStudentByMatricule(matricule));
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public void deleteStudentByEmail(String email) {
    try {
      studentRepository.delete(studentRepository.findStudentByEmail(email));
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
