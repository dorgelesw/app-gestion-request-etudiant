package com.iuc.requests.controller;

import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/iuc/users")
public class UserController {

  private UserService userService;

  public UserController(UserService userService){
    this.userService =userService;
  }

  @GetMapping("/staff")
  public StaffDto findStaffByEmail(@RequestParam(value = "email") @Email String email) {
    return userService.findStaffByEmail(email);
  }

  @GetMapping("/staff")
  public StaffDto findStaffByMatricule(
      @RequestParam(value = "userRegistration") @Pattern(regexp = "[a-zA-Z0-9]")
          String userRegistration) {
    return userService.findStaffByMatricule(userRegistration);
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
      @RequestParam(value = "userRegistration") @Pattern(regexp = "[a-zA-Z0-9]")
          String userRegistration) {
    userService.deleteStaffByMatricule(userRegistration);
  }

  @DeleteMapping("/staff")
  public void deleteStaffByEmail(@RequestParam(value = "email") @Email String email) {
    userService.deleteStaffByEmail(email);
  }

  @PutMapping("/staff")
  public StaffDto updateStaff(@RequestBody StaffDto staffDto) {

    return userService.updateStaff(staffDto);
  }

  @GetMapping("/student")
  public StudentDto findStudentByEmail(@RequestParam(value = "email") @Email String email) {
    return userService.findStudentByEmail(email);
  }

  @GetMapping("matricule")
  public StudentDto findStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z0-9]") String matricule) {
    return userService.findStudentByMatricule(matricule);
  }

  @GetMapping("filiere")
  public List<StudentDto> findAllStudentByFiliere(@RequestParam(value = "filiere") String filiere) {
    return userService.findAllStudentByFiliere(filiere);
  }

  @PostMapping("/student")
  public StudentDto createStudent(@RequestBody StudentDto studentDto) {
    return userService.createStudent(studentDto);
  }

  @DeleteMapping("student")
  public void deleteStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z0-9]") String matricule) {
    userService.deleteStudentByMatricule(matricule);
  }

  @DeleteMapping("/student")
  public void deleteStudentByEmail(@RequestParam(value = "email") @Email String email) {
    userService.deleteStudentByEmail(email);
  }

  @PutMapping("/student")
  public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
    return userService.updateStudent(studentDto);
  }
}
