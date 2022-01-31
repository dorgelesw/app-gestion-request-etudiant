package com.iuc.requests.controller;

import com.iuc.requests.dao.Staff;
import com.iuc.requests.dao.Student;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/iuc/users")
public class UserController {

  @Autowired private UserService userService;
  @Autowired ModelMapper modelMapper;

  @GetMapping("/staff")
  public StaffDto findStaffByEmail(@RequestParam(value = "email") @Email String email) {
    return modelMapper.map(userService.findStaffByEmail(email), StaffDto.class);
  }

  @GetMapping("/staff")
  public StaffDto findStaffByMatricule(
      @RequestParam(value = "userRegistration") @Pattern(regexp = "[a-zA-Z]")
          String userRegistration) {
    return modelMapper.map(userService.findStaffByMatricule(userRegistration), StaffDto.class);
  }

  @GetMapping("/staffs")
  public List<StaffDto> listOfStaffs() {
    return userService.findAllStaffs();
  }

  @PostMapping("/staff")
  public StaffDto createStaff(@RequestBody StaffDto staffDto) {
    return userService.createStaff(staffDto);
  }

  @DeleteMapping("/staff")
  public void deleteStaffByMatricule(
      @RequestParam(value = "userRegistration") @Pattern(regexp = "[a-zA-Z]")
          String userRegistration) {
    userService.deletStaffById(userService.findStaffByMatricule(userRegistration).getId());
  }

  @DeleteMapping("/staff")
  public void deleteStaffByEmail(@RequestParam(value = "email") @Email String email) {
    userService.deletStaffById(userService.findStaffByEmail(email).getId());
  }

  @PutMapping("/staff")
  public StaffDto updateStaff(@RequestBody StaffDto staffDto) {

    Staff currentStaff = modelMapper.map(staffDto, Staff.class);

    if (!userService.getStaffById(currentStaff.getId()).equals(null)) {

      Staff staffDb = modelMapper.map(userService.getStaffById(currentStaff.getId()), Staff.class);
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
      return createStaff(modelMapper.map(staffDb, StaffDto.class));

    } else {
      return null;
    }
  }

  @GetMapping("/student")
  public StudentDto findStrudentByEmail(@RequestParam(value = "email") @Email String email) {

    return modelMapper.map(userService.findStrudentByEmail(email), StudentDto.class);
  }

  @GetMapping("matricule")
  public StudentDto findStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z]") String matricule) {
    return modelMapper.map(userService.findStudentByMatricule(matricule), StudentDto.class);
  }

  @GetMapping("filiere")
  public List<StudentDto> findAllStudentByFiliere(
      @RequestParam(value = "filiere") @Pattern(regexp = "[a-zA-Z]") String filiere) {
    return userService.findAllStudentByFiliere(filiere);
  }

  @PostMapping("/student")
  public StudentDto createStudent(@RequestBody StudentDto studentDto) {
    return userService.createStudent(studentDto);
  }

  @DeleteMapping("student")
  public void deleteStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z]") String matricule) {
    userService.deleteStudentById(userService.findStudentByMatricule(matricule).getId());
  }

  @DeleteMapping("/student")
  public void deleteStudentByEmail(@RequestParam(value = "email") @Email String email) {
    userService.deleteStudentById(userService.findStrudentByEmail(email).getId());
  }

  @PutMapping("/student")
  public StudentDto updateStudent(@RequestBody StudentDto studentDto) {

    Student currentStudent = modelMapper.map(studentDto, Student.class);

    if (userService.getStudentById(currentStudent.getId()).equals(null)) {

      Student studentDb =
          modelMapper.map(userService.getStudentById(currentStudent.getId()), Student.class);
      if (!currentStudent.getEmail().isEmpty()
          && !currentStudent.getEmail().equals(studentDb.getEmail())) {
        studentDb.setEmail(currentStudent.getEmail());
      }
      if (!currentStudent.getNiveau().isEmpty()
          && !currentStudent.getNiveau().equals(studentDb.getNiveau())) {
        studentDb.setNiveau(currentStudent.getNiveau());
      }
      if (!currentStudent.getNom().isEmpty()
          && !currentStudent.getNom().equals(studentDb.getNom())) {
        studentDb.setNom(currentStudent.getNom());
      }
      if (!currentStudent.getPrenom().isEmpty()
          && !currentStudent.getPrenom().equals(studentDb.getPrenom())) {
        studentDb.setPrenom(currentStudent.getPrenom());
      }
      if (!currentStudent.getMatricule().isEmpty()
          && !currentStudent.getMatricule().equals(studentDb.getMatricule())) {
        studentDb.setMatricule(currentStudent.getMatricule());
      }
      if (!currentStudent.getPassword().isEmpty()
          && !currentStudent.getPassword().equals(studentDb.getPassword())) {
        studentDb.setPassword(currentStudent.getPassword());
      }
      if (!currentStudent.getNiveau().isEmpty()
          && !currentStudent.getNiveau().equals(studentDb.getNiveau())) {
        studentDb.setNiveau(currentStudent.getNiveau());
      }
      return userService.createStudent(modelMapper.map(studentDb, StudentDto.class));

    } else {
      return null;
    }
  }
}
