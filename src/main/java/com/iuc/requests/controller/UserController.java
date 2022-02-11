package com.iuc.requests.controller;

import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.repository.StudentRepository;
import com.iuc.requests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/iuc/users")
public class UserController {

  @Autowired
  private UserService userService;

/**
  public UserController(UserService userService){
    this.userService =userService;
  }
*/

  @GetMapping("/findStaffByMatricule")
  public StaffDto findStaffByMatricule(
      @RequestParam(value = "userRegistration") @Pattern(regexp = "[a-zA-Z0-9]")
          String userRegistration) {
    return userService.findStaffByMatricule(userRegistration);
  }

  @GetMapping("/findStaffByEmail")
  public StaffDto findStaffByEmail(
          @RequestParam(value = "email") @Email
                  String email) {
    return userService.findStaffByEmail(email);
  }

  @GetMapping("/staffs")
  public List<StaffDto> listOfStaffs() {
    return userService.findAllStaffs();
  }

  @PostMapping(value = "/saveStaff")
  public StaffDto createStaff(@RequestBody StaffDto staffDto) {
    return userService.createStaff(staffDto);
  }

  @DeleteMapping("/deleteStaffByMatricule")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteStaffByMatricule(
      @RequestParam(value = "userRegistration") @Pattern(regexp = "[a-zA-Z0-9]")
          String userRegistration) {
    userService.deleteStaffByMatricule(userRegistration);
  }

  @DeleteMapping("/deleteStaffByEmail")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteStaffByEmail(@RequestParam(value = "email") @Email String email) {
    userService.deleteStaffByEmail(email);
  }

  @PutMapping("/updateStaff")
  public StaffDto updateStaff(@RequestBody StaffDto staffDto) {
    return userService.updateStaff(staffDto);
  }

  @GetMapping("/findStudentByEmail")
  public StudentDto findStudentByEmail(@RequestParam(value = "email") @Email String email) {
    return userService.findStudentByEmail(email);
  }

  @GetMapping("/findStudentByMatricule")
  public StudentDto findStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z0-9]") String matricule) {
    return userService.findStudentByMatricule(matricule);
  }

  @GetMapping("findStudentsByFiliere")
  public List<StudentDto> findAllStudentByFiliere(@RequestParam(value = "filiere") String filiere) {
    return userService.findAllStudentByFiliere(filiere);
  }

  @PostMapping(value="/saveStudent")
  public StudentDto createStudent(@RequestBody StudentDto studentDto) {
    return userService.createStudent(studentDto);
  }

  @DeleteMapping("deleteStudentByMatricule")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteStudentByMatricule(
      @RequestParam(value = "matricule") @Pattern(regexp = "[a-zA-Z0-9]") String matricule) {
    userService.deleteStudentByMatricule(matricule);
  }

  @DeleteMapping("/deleteStudentByEmail")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteStudentByEmail1(@RequestParam(value = "email") @Email String email) {
    userService.deleteStudentByEmail(email);
  }

  @PutMapping("/updateStudent")
  public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
    return userService.updateStudent(studentDto);
  }
}
