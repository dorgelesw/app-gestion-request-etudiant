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
    return modelMapper.map(userService.createStaff(staffDto), StaffDto.class);
  }

  @DeleteMapping("/staff")
  public void deleteStaffByMatricule(
      @RequestParam(value = "userRegistration") @Pattern(regexp = "[a-zA-Z]")
          String userRegistration) {
    userService.deleteStaffByMatricule(userRegistration);
  }

  @DeleteMapping("/staff")
  public void deleteStaffByEmail(@RequestParam(value = "email") @Email String email) {
    userService.deleteStaffByEmail(email);
  }

  @PutMapping("/staff")
  public StaffDto updateStaff(@RequestBody StaffDto staffDto) {
    return modelMapper.map(userService.updateStaff(staffDto), StaffDto.class);
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
    return (List<StudentDto>)
        modelMapper.map(userService.findAllStudentByFiliere(filiere), StudentDto.class);
  }

  @PostMapping("/student")
  public StudentDto createStudent(@RequestBody StudentDto studentDto) {
    return modelMapper.map(userService.createStudent(studentDto), StudentDto.class);
  }

  @DeleteMapping("student")
  public void deleteStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z]") String matricule) {
    userService.deleteStudentByMatricule(matricule);
  }

  @DeleteMapping("/student")
  public void deleteStudentByEmail(@RequestParam(value = "email") @Email String email) {
    userService.deleteStudentByEmail(email);
  }

  @PutMapping("/student")
  public StudentDto updateStudent(@RequestBody StudentDto studentDto) {

    return modelMapper.map(userService.updateStudent(studentDto), StudentDto.class);
  }
}
