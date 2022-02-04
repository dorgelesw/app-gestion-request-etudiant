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

    Staff staff = staffRepository.save(modelMapper.map(staffDto, Staff.class));
    return !staff.equals(null) ? modelMapper.map(staff, StaffDto.class) : null;
  }

  public StaffDto findStaffByEmail(String email) {

    Staff staff = staffRepository.findByEmail(email);
    return !staff.equals(null) ? modelMapper.map(staff, StaffDto.class) : null;
  }

  public StaffDto findStaffByMatricule(String userRegistration) {

    Staff staff = staffRepository.findByMatricule(userRegistration);
    return !staff.equals(null) ? modelMapper.map(staff, StaffDto.class) : null;
  }

  public void deleteStaffByEmail(String email) {

    Staff staff = staffRepository.findByEmail(email);
    if (!staff.equals(null)) {
      staffRepository.delete(staff);
    }
  }

  public void deleteStaffByMatricule(String matricule) {

    Staff staff = staffRepository.findByMatricule(matricule);
    if (!staff.equals(null)) {
      staffRepository.delete(staff);
    }
  }

  public StaffDto updateStaff(StaffDto staffDto) {

    Staff currentStaff = modelMapper.map(staffDto, Staff.class);
    Staff staffDb = staffRepository.findByEmail(currentStaff.getEmail());

    if (!staffDb.equals(null)) {
      if (!currentStaff.getEmail().equals(staffDb.getEmail())) {
        staffDb.setEmail(currentStaff.getEmail());
      }
      if (!currentStaff.getFiliere().equals(staffDb.getFiliere())) {
        staffDb.setFiliere(currentStaff.getFiliere());
      }
      if (!currentStaff.getPosteOccupe().equals(staffDb.getPosteOccupe())) {
        staffDb.setPosteOccupe(currentStaff.getPosteOccupe());
      }

      if (!currentStaff.getMatricule().equals(staffDb.getMatricule())) {
        staffDb.setMatricule(currentStaff.getMatricule());
      }
      if (!currentStaff.getNom().equals(staffDb.getNom())) {
        staffDb.setNom(currentStaff.getNom());
      }
      if (!currentStaff.getPrenom().equals(staffDb.getPrenom())) {
        staffDb.setNom(currentStaff.getNom());
      }
      if (!currentStaff.getPassword().equals(staffDb.getPassword())) {
        staffDb.setPassword(currentStaff.getPassword());
      }
      return !staffRepository.save(staffDb).equals(null)
          ? modelMapper.map(staffRepository.save(staffDb), StaffDto.class)
          : null;
    } else {
      return null;
    }
  }

  public StudentDto updateStudent(StudentDto studentDto) {

    Student currentStudent = modelMapper.map(studentDto, Student.class);
    Student studentDb = studentRepository.findStudentByEmail(currentStudent.getEmail());

    if (studentDb.equals(null)) {

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
      return !studentRepository.save(studentDb).equals(null)
          ? modelMapper.map(studentRepository.save(studentDb), StudentDto.class)
          : null;

    } else {
      return null;
    }
  }

  public StudentDto findStrudentByEmail(String email) {
    Student student = studentRepository.findStudentByEmail(email);
    return !student.equals(null) ? modelMapper.map(student, StudentDto.class) : null;
  }

  public StudentDto findStudentByMatricule(String matricule) {
    Student student = studentRepository.findStudentByMatricule(matricule);
    return !student.equals(null) ? modelMapper.map(student, StudentDto.class) : null;
  }

  public List<StudentDto> findAllStudentByFiliere(String filiere) {

      List<StudentDto> studentDtoList = new ArrayList<>();
      Iterable<Student> students = studentRepository.findAllByFiliere(filiere);
      if (!students.equals(null)) {
        students.forEach(
            student -> {
              studentDtoList.add(modelMapper.map(student, StudentDto.class));
            });
      }
      return studentDtoList;

  }

  public StudentDto createStudent(StudentDto studentDto) {
    Student student = studentRepository.save(modelMapper.map(studentDto, Student.class));
    return !student.equals(null) ? modelMapper.map(student, StudentDto.class) : null;
  }

  public void deleteStudentByMatricule(String matricule) {
    Student student = studentRepository.findStudentByMatricule(matricule);
    if (!student.equals(null)) {
      studentRepository.delete(student);
    }
  }

  public void deleteStudentByEmail(String email) {

    Student student = studentRepository.findStudentByEmail(email);
    if (!student.equals(null)) {
      studentRepository.delete(student);
    }
  }
}
