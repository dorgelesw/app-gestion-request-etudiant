package com.iuc.requests.controller;

import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/iuc/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/staff", params = "matricule")
  public StaffDto findStaffByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z0-9]") String matricule) {
    return userService.findStaffByMatricule(matricule);
  }

  @GetMapping(value = "/staff", params = "email")
  public StaffDto findStaffByEmail(@RequestParam(value = "email") @Email String email) {
    return userService.findStaffByEmail(email);
  }

  @GetMapping("/staffs")
  public List<StaffDto> listOfStaffs() {
    return userService.findAllStaffs();
  }

  @PostMapping(value = "/staff")
  public StaffDto createStaff(@RequestBody StaffDto staffDto) {
    return userService.createStaff(staffDto);
  }

  @DeleteMapping(value = "/staff", params = "matricule")
  public void deleteStaffByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z0-9]") String matricule) {
    userService.deleteStaffByMatricule(matricule);
  }

  @DeleteMapping(value = "/staff", params = "email")
  public void deleteStaffByEmail(@RequestParam(value = "email") @Email String email) {
    userService.deleteStaffByEmail(email);
  }

  @PutMapping("/staff")
  public StaffDto updateStaff(@RequestBody StaffDto staffDto) {
    return userService.updateStaff(staffDto);
  }

  @GetMapping(value = "/student", params = "email")
  public StudentDto findStudentByEmail(@RequestParam(value = "email") @Email String email) {
    return userService.findStudentByEmail(email);
  }

  @GetMapping(value = "/student", params = "matricule")
  public StudentDto findStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z0-9]") String matricule) {
    return userService.findStudentByMatricule(matricule);
  }

  @GetMapping(value = "students", params = "filiere")
  public List<StudentDto> findAllStudentByFiliere(@RequestParam(value = "filiere") String filiere) {
    return userService.findAllStudentByFiliere(filiere);
  }

  @PostMapping(value = "/student")
  public StudentDto createStudent(@RequestBody StudentDto studentDto) {
    return userService.createStudent(studentDto);
  }

  @DeleteMapping(value = "student", params = "matricule")
  public void deleteStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z0-9]") String matricule) {
    userService.deleteStudentByMatricule(matricule);
  }

  @DeleteMapping(value = "/student", params = "email")
  public void deleteStudentByEmail1(@RequestParam(value = "email") @Email String email) {
    userService.deleteStudentByEmail(email);
  }

   @PutMapping("/student")
  public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
  return userService.updateStudent(studentDto);
  }
}
