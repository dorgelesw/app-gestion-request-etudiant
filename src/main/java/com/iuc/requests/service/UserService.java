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
    return modelMapper.map(staff, StaffDto.class);
  }

  /**
   * Auteur : Eric Wouwo Date : 27/01/2022
   *
   * @param email
   * @return
   */
  public Staff findStaffByEmail(String email) {
    return staffRepository.findByEmail(email);
  }

  /**
   * Auteur : Eric Wouwo Date : 27/01/2022
   *
   * @param userRegistration
   * @return
   */
  public Staff findStaffByMatricule(String userRegistration) {
    return staffRepository.findByMatricule(userRegistration);
  }

  /**
   * Auteur : Eric Wouwo Date : 27/01/2022
   *
   * @param id
   */
  public void deletStaffById(final Long id) {

    Optional<Staff> staffOptional = staffRepository.findById(id);
    if (staffOptional.isPresent()) {
      staffRepository.deleteById(id);
    }
  }

  /**
   * Auteur : Eric Wouwo Date : 27/01/2022
   *
   * @param staffDto
   * @return
   */
  public StaffDto updateStaff(StaffDto staffDto) {
    return modelMapper.map(
        staffRepository.save(modelMapper.map(staffDto, Staff.class)), StaffDto.class);
  }

  /**
   * Auteur : Eric Wouwo Date : 27/01/2022
   *
   * @param id
   * @return
   */
  public Staff getStaffById(final Long id) {
    Optional<Staff> staffOptional = staffRepository.findById(id);
    return staffOptional.isPresent() ? staffOptional.get() : null;
  }

  /**
   * Auteur : Eric Wouwo Date : 28/01/2022
   *
   * @param email
   * @return
   */
  public Student findStrudentByEmail(String email) {
    return studentRepository.findStudentByEmail(email);
  }

  public Student findStudentByMatricule(String matricule) {
    return studentRepository.findStudentByMatricule(matricule);
  }

  /**
   * Auteur : Eric Wouwo Date : 28/01/2022
   *
   * @param filiere
   * @return
   */
  public List<StudentDto> findAllStudentByFiliere(String filiere) {
    Iterable<Student> students = studentRepository.findAllByFiliere(filiere);
    List<StudentDto> studentDtoList = new ArrayList<>();
    students.forEach(
        student -> {
          studentDtoList.add(modelMapper.map(student, StudentDto.class));
        });
    return studentDtoList;
  }

  /**
   * Auteur : Eric Wouwo Date : 28/01/2022
   *
   * @param studentDto
   * @return
   */
  public StudentDto createStudent(StudentDto studentDto) {
    return modelMapper.map(
        studentRepository.save(modelMapper.map(studentDto, Student.class)), StudentDto.class);
  }

  /**
   * Auteur : Eric Wouwo Date : 28/01/2022
   *
   * @param id
   */
  public void deleteStudentById(final Long id) {
    Optional<Student> studentOptional = studentRepository.findById(id);
    if (studentOptional.isPresent()) {
      studentRepository.deleteById(id);
    }
  }

  /**
   * Auteur : Eric Wouwo Date : 28/01/2022
   *
   * @param id
   * @return
   */
  public Student getStudentById(final Long id) {
    Optional<Student> studentOptional = studentRepository.findById(id);
    return studentOptional.isPresent() ? studentOptional.get() : null;
  }
}
