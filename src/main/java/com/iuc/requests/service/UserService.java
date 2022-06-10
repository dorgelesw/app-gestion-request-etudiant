package com.iuc.requests.service;

import com.iuc.requests.dao.Staff;
import com.iuc.requests.dao.Student;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableTransactionManagement
public class UserService {

  private final StaffRepository staffRepository;
  private final StudentRepository studentRepository;
  private final ModelMapper modelMapper;

  public UserService(
      StaffRepository staffRepository,
      StudentRepository studentRepository,
      ModelMapper modelMapper) {
    this.staffRepository = staffRepository;
    this.studentRepository = studentRepository;
    this.modelMapper = modelMapper;
  }

  public List<StaffDto> findAllStaffs() {
    List<Staff> staffs;
    staffs = staffRepository.findAll();
    return staffs.stream()
        .map(staff -> modelMapper.map(staff, StaffDto.class))
        .collect(Collectors.toList());
  }

  public StaffDto createStaff(StaffDto staffDto) {
    Staff staff = staffRepository.save(modelMapper.map(staffDto, Staff.class));
    return modelMapper.map(staff, StaffDto.class);
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
    if (staff != null) {
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

    if (staffDto != null) {

      Staff currentStaff = modelMapper.map(staffDto, Staff.class);
      Staff dbStaff = staffRepository.findByEmail(staffDto.getEmail());
      modelMapper
          .getConfiguration()
          .setSkipNullEnabled(true); // source fields who is null will be skiped
      modelMapper.map(currentStaff, dbStaff);
      Staff staffUpdated = staffRepository.save(dbStaff);
      return modelMapper.map(staffUpdated, StaffDto.class);
    } else {
      return null;
    }
  }

  public StudentDto updateStudent(StudentDto studentDto) {

    if (studentDto != null) {
      // setup
      Student currentStudent = modelMapper.map(studentDto, Student.class);
      Student dbStudent = studentRepository.findByEmail(studentDto.getEmail());
      modelMapper
          .getConfiguration()
          .setSkipNullEnabled(true); // source fields who is null will be skiped
      modelMapper.map(currentStudent, dbStudent);
      Student studentUpdated = studentRepository.save(dbStudent);
      return modelMapper.map(studentUpdated, StudentDto.class);
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
    List<Student> students;
    students = studentRepository.findAllByFiliere(filiere);
    return students.stream()
        .map(student -> modelMapper.map(student, StudentDto.class))
        .collect(Collectors.toList());
  }

  public StudentDto createStudent(StudentDto studentDto) {
    Student student = studentRepository.save(modelMapper.map(studentDto, Student.class));
    return modelMapper.map(student, StudentDto.class);
  }

  public StudentDto createStudentV2(StudentDto studentDto) {
    Student student;
    if (Objects.nonNull(studentDto)) {
      student = studentRepository.findByEmail(studentDto.getEmail());
      if (Objects.nonNull(student)) {
        log.info("The email {} already used", studentDto.getEmail());
        throw new IllegalArgumentException(
            String.format("The value %s is already in the database.", studentDto.getEmail()));
      } else {
        String matricule = generateStudentMatricule();
        studentDto.setMatricule(matricule);
        student = studentRepository.save(modelMapper.map(studentDto, Student.class));
        return modelMapper.map(student, StudentDto.class);
      }
    }
    throw new NullPointerException("Student Object can't be null");
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

  private String generateStudentMatricule() {

    Long currentID = 0L;
    Optional<Student> lastInsertedStudent = studentRepository.findTopByOrderByIdDesc();
    if (lastInsertedStudent.isPresent()) currentID = lastInsertedStudent.get().getId();

    return String.format("ST%05d", currentID + 1);
  }
}
