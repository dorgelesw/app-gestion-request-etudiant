package com.iuc.requests.controller;

import com.iuc.requests.dao.Staff;
import com.iuc.requests.dao.Student;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/iuc/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/staff")
    public StaffDto findStaffByEmail(@RequestParam(value = "email") @Email String email) {
        if(email.isEmpty()) {
            return null;
        } else{
            return !userService.findStaffByEmail(email).equals(null)? userService.findStaffByEmail(email): null;
        }

    }

    @GetMapping("/staff")
    public StaffDto findStaffByMatricule(@RequestParam(value = "userRegistration") String userRegistration) {
        if(userRegistration.isEmpty()) {
            return null;
        } else{
            return userService.findStaffByMatricule(userRegistration).equals(null)? userService.findStaffByMatricule(userRegistration): null;
        }
    }

    @GetMapping("/staffs")
    public List<StaffDto> listOfStaffs() {
        return userService.findAllStaffs();
    }

    @PostMapping("/staff")
    public StaffDto saveStaff(@RequestBody StaffDto staffDto) {
        return userService.saveStaff(staffDto);
    }


    @DeleteMapping("/staff")
    public void deleteStaffByMatricule(@RequestParam(value = "userRegistration") String userRegistration){

        if(!userService.findStaffByMatricule(userRegistration).equals(null)){

            userService.deletStaffById(modelMapper.map(userService.findStaffByMatricule(userRegistration), Staff.class).getId());
        }
    }

    @DeleteMapping("/staff")
    public  void deleteStaffByEmail(@RequestParam(value="email") String email){
        if(!userService.findStaffByEmail(email).equals(null)){
            userService.deletStaffById(modelMapper.map(userService.findStaffByEmail(email), Staff.class).getId());
        }
    }

    @PutMapping("/staff")
    public StaffDto upadteStaff(@RequestBody StaffDto staffDto ){

        Staff currentStaff = modelMapper.map(staffDto,Staff.class);

        if(!userService.getStaffById(currentStaff.getId()).equals(null)){

            Staff staffDb = modelMapper.map(userService.getStaffById(currentStaff.getId()),Staff.class);
            if(!currentStaff.getEmail().isEmpty() && !currentStaff.getEmail().equals(staffDb.getEmail())){
                staffDb.setEmail(currentStaff.getEmail());
            }
            if(!currentStaff.getFiliere().isEmpty() && !currentStaff.getFiliere().equals(staffDb.getFiliere())){
                staffDb.setFiliere(currentStaff.getFiliere());
            }
            if(!currentStaff.getPosteOccupe().isEmpty() && !currentStaff.getPosteOccupe().equals(staffDb.getPosteOccupe())){
                staffDb.setPosteOccupe(currentStaff.getPosteOccupe());
            }

            if(!currentStaff.getMatricule().isEmpty() && !currentStaff.getMatricule().equals(staffDb.getMatricule())){
                staffDb.setMatricule(currentStaff.getMatricule());
            }
            if(!currentStaff.getNom().isEmpty() && !currentStaff.getNom().equals(staffDb.getNom())){
                staffDb.setNom(currentStaff.getNom());
            }
            if(!currentStaff.getPrenom().isEmpty() && !currentStaff.getPrenom().equals(staffDb.getPrenom())){
                staffDb.setNom(currentStaff.getNom());
            }
            if(!currentStaff.getPassword().isEmpty() && !currentStaff.getPassword().equals(staffDb.getPassword())){
                staffDb.setPassword(currentStaff.getPassword());
            }

            return saveStaff(modelMapper.map(staffDb,StaffDto.class));

        }
        else{
            return null;
        }

    }

    @GetMapping("/student")
    public StudentDto findStrudentByEmail(@RequestParam(value="email") @Email String email){

        if(email.isEmpty()){
            return  null ;
        }
        else{
            return  !userService.findStrudentByEmail(email).equals(null)?userService.findStrudentByEmail(email):null;
        }

    }

    @GetMapping("matricule")
    public  StudentDto findStudentByMatricule(@RequestParam(value="matricule") String matricule){

        if(matricule.isEmpty()){
            return  null;
        }
        else{
            return  !userService.findStudentByMatricule(matricule).equals(null)? userService.findStudentByMatricule(matricule): null;
        }
    }

    @GetMapping("filiere")
    public  List<StudentDto> findAllStudentByFiliere(@RequestParam(value="filiere") String filiere){
        if(filiere.isEmpty()){
            return null;
        }
        else{
            return  userService.findAllStudentByFiliere(filiere).equals(null)? userService.findAllStudentByFiliere(filiere): null;
        }
    }

    @PostMapping("/student")
    public StudentDto saveStudent(@RequestBody StudentDto studentDto){
        return userService.saveStudent(studentDto);
    }


    @DeleteMapping("student")
    public void deleteStudentByMatricule(@RequestParam(value="matricule") String matricule){
        if(!userService.findStudentByMatricule(matricule).equals(null)){
            userService.deleteStudentById(modelMapper.map(userService.findStudentByMatricule(matricule), Student.class).getId());
        }
    }

    @DeleteMapping("/student")
    public void deleteStudentByEmail(@RequestParam(value="email") @Email String email){
        if(!userService.findStrudentByEmail(email).equals(null)){
            userService.deleteStudentById(modelMapper.map(userService.findStrudentByEmail(email),Student.class).getId());
        }
    }

    @PutMapping("/student")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto){

        Student currentStudent = modelMapper.map(studentDto, Student.class);

        if(userService.getStudentById(currentStudent.getId()).equals(null)){

            Student studentDb = modelMapper.map(userService.getStudentById(currentStudent.getId()),Student.class);
            if(!currentStudent.getEmail().isEmpty() && !currentStudent.getEmail().equals(studentDb.getEmail())){
                studentDb.setEmail(currentStudent.getEmail());
            }
            if(!currentStudent.getNiveau().isEmpty() && !currentStudent.getNiveau().equals(studentDb.getNiveau())){
                studentDb.setNiveau(currentStudent.getNiveau());
            }
            if(!currentStudent.getNom().isEmpty() && !currentStudent.getNom().equals(studentDb.getNom())){
                studentDb.setNom(currentStudent.getNom());
            }
            if(!currentStudent.getPrenom().isEmpty() && !currentStudent.getPrenom().equals(studentDb.getPrenom())){
                studentDb.setPrenom(currentStudent.getPrenom());
            }
            if(!currentStudent.getMatricule().isEmpty() && !currentStudent.getMatricule().equals(studentDb.getMatricule())){
                studentDb.setMatricule(currentStudent.getMatricule());
            }
            if(!currentStudent.getPassword().isEmpty() && !currentStudent.getPassword().equals(studentDb.getPassword())){
                studentDb.setPassword(currentStudent.getPassword());
            }
            if(!currentStudent.getNiveau().isEmpty() && !currentStudent.getNiveau().equals(studentDb.getNiveau())){
                studentDb.setNiveau(currentStudent.getNiveau());
            }
            return userService.saveStudent(modelMapper.map(studentDb,StudentDto.class));

        }else{
            return  null;
        }

    }

}
