package com.iuc.requests.service;

import com.iuc.requests.dao.Staff;
import com.iuc.requests.dao.Student;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService{

  @Autowired private StaffRepository staffRepository;
  @Autowired private StudentRepository studentRepository;
  @Autowired private ModelMapper modelMapper;

  public List<StaffDto> findAllStaffs() {
    List<Staff> staffs = new ArrayList<>();
    staffs = staffRepository.findAll();
     return  staffs.stream().map(staff -> modelMapper.map(staff, StaffDto.class)).collect(Collectors.toList());

  }

  public StaffDto createStaff(StaffDto staffDto) {
    Staff staff = staffRepository.save(modelMapper.map(staffDto, Staff.class));
    return staff != null ? modelMapper.map(staff, StaffDto.class) : null;
  }

  public StaffDto findStaffByEmail(String email) {

    Staff staff = staffRepository.findByEmail(email);
    return staff != null ? modelMapper.map(staff, StaffDto.class) : null;
  }

  public StaffDto findStaffByMatricule(String userRegistration) {
    Staff staff = staffRepository.findByMatricule(userRegistration);
    return staff != null ? modelMapper.map(staff, StaffDto.class) : null;
  }

  public void deleteStaffByEmail(String email) {
    Staff staff = staffRepository.findByEmail(email);
    if (staff != null){
      staffRepository.delete(staff);
    }
  }

  public void deleteStaffByMatricule(String matricule) {

    Staff staff = staffRepository.findByMatricule(matricule);
    if (staff != null) {
      staffRepository.delete(staff);
    }
  }

  public StaffDto updateStaff(StaffDto staffDto) {
    Staff currentStaff = modelMapper.map(staffDto, Staff.class);
    Staff staffToUpdate = staffRepository.findByEmail(currentStaff.getEmail());

    if (staffToUpdate != null) {
      if (!currentStaff.getEmail().equals(staffToUpdate.getEmail())) {
        staffToUpdate.setEmail(currentStaff.getEmail());
      }
      if (!currentStaff.getFiliere().equals(staffToUpdate.getFiliere())) {
        staffToUpdate.setFiliere(currentStaff.getFiliere());
      }
      if (!currentStaff.getPosteOccupe().equals(staffToUpdate.getPosteOccupe())) {
        staffToUpdate.setPosteOccupe(currentStaff.getPosteOccupe());
      }

      if (!currentStaff.getMatricule().equals(staffToUpdate.getMatricule())) {
        staffToUpdate.setMatricule(currentStaff.getMatricule());
      }
      if (!currentStaff.getNom().equals(staffToUpdate.getNom())) {
        staffToUpdate.setNom(currentStaff.getNom());
      }
      if (!currentStaff.getPrenom().equals(staffToUpdate.getPrenom())) {
        staffToUpdate.setPrenom(currentStaff.getPrenom());
      }
      if (!currentStaff.getPassword().equals(staffToUpdate.getPassword())) {
        staffToUpdate.setPassword(currentStaff.getPassword());
      }
      return staffRepository.save(staffToUpdate) != null
          ? modelMapper.map(staffRepository.save(staffToUpdate), StaffDto.class)
          : null;
    } else {
      return null;
    }
  }

  public StudentDto updateStudent(StudentDto studentDto) {
    Student currentStudent = modelMapper.map(studentDto, Student.class);
    Student studentDb = studentRepository.findByEmail(currentStudent.getEmail());

    if (studentDb != null) {

      if (!currentStudent.getEmail().equals(studentDb.getEmail())) {
        studentDb.setEmail(currentStudent.getEmail());
      }
      if (!currentStudent.getNiveau().equals(studentDb.getNiveau())) {
        studentDb.setNiveau(currentStudent.getNiveau());
      }
      if (!currentStudent.getNom().equals(studentDb.getNom())) {
        studentDb.setNom(currentStudent.getNom());
      }
      if (!currentStudent.getPrenom().equals(studentDb.getPrenom())) {
        studentDb.setPrenom(currentStudent.getPrenom());
      }
      if (!currentStudent.getMatricule().equals(studentDb.getMatricule())) {
        studentDb.setMatricule(currentStudent.getMatricule());
      }
      if (!currentStudent.getPassword().equals(studentDb.getPassword())) {
        studentDb.setPassword(currentStudent.getPassword());
      }
      if (!currentStudent.getNiveau().equals(studentDb.getNiveau())) {
        studentDb.setNiveau(currentStudent.getNiveau());
      }
      if (!currentStudent.getFiliere().equals(studentDb.getFiliere())) {
        studentDb.setFiliere(currentStudent.getFiliere());
      }
      return studentRepository.save(studentDb) != null
          ? modelMapper.map(studentRepository.save(studentDb), StudentDto.class)
          : null;

    } else {
      return null;
    }
  }

  public StudentDto findStudentByEmail(String email) {
    Student student = studentRepository.findByEmail(email);
    return student != null ? modelMapper.map(student, StudentDto.class) : null;
  }

  public StudentDto findStudentByMatricule(String matricule) {
    Student student = studentRepository.findByMatricule(matricule);
    return student != null ? modelMapper.map(student, StudentDto.class) : null;
  }

  public List<StudentDto> findAllStudentByFiliere(String filiere) {
    List<Student> students = new ArrayList<>();
    students = studentRepository.findAllByFiliere(filiere);
    return students.stream().map(student -> modelMapper.map(student,StudentDto.class)).collect(Collectors.toList());
  }

  public StudentDto createStudent(StudentDto studentDto) {
    Student student = studentRepository.save(modelMapper.map(studentDto, Student.class));
    return student != null ? modelMapper.map(student, StudentDto.class) : null;
  }

  public void deleteStudentByMatricule(String matricule) {
    Student student = studentRepository.findByMatricule(matricule);
    if (student != null) {
      studentRepository.delete(student);
    }
  }

  public void deleteStudentByEmail(String email) {
    Student student = studentRepository.findByEmail(email);
    if (student != null) {
      studentRepository.delete(student);
    }

}

}
