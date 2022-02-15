package com.iuc.requests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import com.iuc.requests.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StaffRepository staffRepository;

    @Test
    public void when_find_staff_by_matricule_it_return_staff() throws Exception {

        //ARRANGE
        StaffDto staffDto = new StaffDto();
        staffDto.setNom("staff-nom-test-3");
        staffDto.setPrenom("staff-prenom-test-3");
        staffDto.setPosteOccupe("staff-chef-dep-test-3");
        staffDto.setMatricule("2CIUC2020");
        staffDto.setEmail("staff-test-3@iuc.com");
        staffDto.setFiliere("MATHEMATIQUE");
        staffDto.setPassword("12345");

        //ACT
        Mockito.when(userService.findStaffByMatricule(staffDto.getMatricule())).thenReturn(staffDto);

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/iuc/users/staff?matricule=2CIUC2020")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is(staffDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is(staffDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is(staffDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is(staffDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posteOccupe",Matchers.is(staffDto.getPosteOccupe())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is(staffDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is(staffDto.getPassword())));

        Mockito.verify(userService,Mockito.times(1)).findStaffByMatricule(staffDto.getMatricule());

    }

    @Test
    public void when_find_staff_by_email_it_return_staff() throws Exception {
        //ARRANGE
        StaffDto staffDto = new StaffDto();
        staffDto.setNom("staff-nom-test-3");
        staffDto.setPrenom("staff-prenom-test-3");
        staffDto.setPosteOccupe("staff-chef-dep-test-3");
        staffDto.setMatricule("2CIUC2020");
        staffDto.setEmail("staff-test-3@iuc.com");
        staffDto.setFiliere("MATHEMATIQUE");
        staffDto.setPassword("12345");

        //ACT
        Mockito.when(userService.findStaffByEmail(staffDto.getEmail())).thenReturn(staffDto);

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/iuc/users/staff?email=staff-test-3@iuc.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is(staffDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is(staffDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is(staffDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is(staffDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posteOccupe",Matchers.is(staffDto.getPosteOccupe())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is(staffDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is(staffDto.getPassword())));

        Mockito.verify(userService, Mockito.times(1)).findStaffByEmail(staffDto.getEmail());

    }

    @Test
    public void when_delete_staff_by_email_you_cannot_get_it_if_you_find_it() throws Exception {

        //ARRANGE
        StaffDto staffDto = new StaffDto();
        staffDto.setNom("staff-nom-test-3");
        staffDto.setPrenom("staff-prenom-test-3");
        staffDto.setPosteOccupe("staff-chef-dep-test-3");
        staffDto.setMatricule("2CIUC2020");
        staffDto.setEmail("staff-test-3@iuc.com");
        staffDto.setFiliere("MATHEMATIQUE");
        staffDto.setPassword("12345");

        //ACT
        Mockito.doNothing().when(userService).deleteStaffByEmail(staffDto.getEmail());

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.delete("/iuc/users/staff?email=staff-test-3@iuc.com")
                        .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService,Mockito.times(1)).deleteStaffByEmail(staffDto.getEmail());

    }

    @Test
    public void when_delete_staff_by_matricule_you_cannot_get_it_if_you_find_it() throws Exception {

        //ARRANGE
        StaffDto staffDto = new StaffDto();
        staffDto.setNom("staff-nom-test-3");
        staffDto.setPrenom("staff-prenom-test-3");
        staffDto.setPosteOccupe("staff-chef-dep-test-3");
        staffDto.setMatricule("2CIUC2020");
        staffDto.setEmail("staff-test-3@iuc.com");
        staffDto.setFiliere("MATHEMATIQUE");
        staffDto.setPassword("12345");

        //ACT
        Mockito.doNothing().when(userService).deleteStaffByMatricule(staffDto.getMatricule());

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.delete("/iuc/users/staff?matricule=2CIUC2020")
                        .contentType(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService,Mockito.times(1)).deleteStaffByMatricule(staffDto.getMatricule());

    }

    @Test
    public void when_delete_student_by_matricule_you_cannot_get_it_if_you_find_it() throws Exception {

        //ARRANGE
        StudentDto studentDto = new StudentDto();
        studentDto.setNom("student-nom-test-3");
        studentDto.setPrenom("student-prenom-test-3");
        studentDto.setNiveau("1");
        studentDto.setMatricule("1EIUC2021");
        studentDto.setEmail("student-test-3@iuc.com");
        studentDto.setFiliere("MATHEMATIQUE");
        studentDto.setPassword("1234");

        //ACT
        Mockito.doNothing().when(userService).deleteStudentByMatricule(studentDto.getMatricule());

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.delete("/iuc/users/student?matricule=1EIUC2021")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService,Mockito.times(1)).deleteStudentByMatricule(studentDto.getMatricule());

    }

    @Test
    public void when_delete_student_by_email_you_cannot_get_it_if_you_find_it() throws Exception {

        //ARRANGE
        StudentDto studentDto = new StudentDto();
        studentDto.setNom("student-nom-test-3");
        studentDto.setPrenom("student-prenom-test-3");
        studentDto.setNiveau("1");
        studentDto.setMatricule("1EIUC2021");
        studentDto.setEmail("student-test-3@iuc.com");
        studentDto.setFiliere("MATHEMATIQUE");
        studentDto.setPassword("1234");

        //ACT
        Mockito.doNothing().when(userService).deleteStudentByEmail(studentDto.getEmail());

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.delete("/iuc/users/student?email=student-test-3@iuc.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService,Mockito.times(1)).deleteStudentByEmail(studentDto.getEmail());

    }

    @Test
    public void when_find_student_by_email_it_return_student() throws Exception {
        //ARRANGE
        StudentDto studentDto = new StudentDto();
        studentDto.setNom("student-nom-test-3");
        studentDto.setPrenom("student-prenom-test-3");
        studentDto.setNiveau("1");
        studentDto.setMatricule("1EIUC2021");
        studentDto.setEmail("student-test-3@iuc.com");
        studentDto.setFiliere("MATHEMATIQUE");
        studentDto.setPassword("1234");

        //ACT
        Mockito.when(userService.findStudentByEmail(studentDto.getEmail())).thenReturn(studentDto);

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/iuc/users/student?email=student-test-3@iuc.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is(studentDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is(studentDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is(studentDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is(studentDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.niveau",Matchers.is(studentDto.getNiveau())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is(studentDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is(studentDto.getPassword())));

        Mockito.verify(userService,Mockito.times(1)).findStudentByEmail(studentDto.getEmail());

    }

    @Test
    public void when_find_student_by_matricule_it_return_student() throws Exception {
        //ARRANGE
        StudentDto studentDto = new StudentDto();
        studentDto.setNom("student-nom-test-3");
        studentDto.setPrenom("student-prenom-test-3");
        studentDto.setNiveau("1");
        studentDto.setMatricule("1EIUC2021");
        studentDto.setEmail("student-test-3@iuc.com");
        studentDto.setFiliere("MATHEMATIQUE");
        studentDto.setPassword("1234");

        //ACT
        Mockito.when(userService.findStudentByMatricule(studentDto.getMatricule())).thenReturn(studentDto);

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/iuc/users/student?matricule=1EIUC2021")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is(studentDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is(studentDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is(studentDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is(studentDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.niveau",Matchers.is(studentDto.getNiveau())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is(studentDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is(studentDto.getPassword())));

        Mockito.verify(userService,Mockito.times(1)).findStudentByMatricule(studentDto.getMatricule());

    }

    @Test
    public void when_you_find_all_staff_it_should_return_all_staff() throws Exception {
        //ARRANGE
        StaffDto staffDto = new StaffDto();
        staffDto.setNom("staff-nom-test-3");
        staffDto.setPrenom("staff-prenom-test-3");
        staffDto.setPosteOccupe("staff-chef-dep-test-3");
        staffDto.setMatricule("2CIUC2020");
        staffDto.setEmail("staff-test-3@iuc.com");
        staffDto.setFiliere("MATHEMATIQUE");
        staffDto.setPassword("12345");

        StaffDto staffDto1 = new StaffDto();
        staffDto1.setNom("staff-nom-test-4");
        staffDto1.setPrenom("staff-prenom-test-4");
        staffDto1.setPosteOccupe("staff-chef-dep-test-4");
        staffDto1.setMatricule("2CIUC2024");
        staffDto1.setEmail("staff-test-4@iuc.com");
        staffDto1.setFiliere("MATHEMATIQUE");
        staffDto1.setPassword("123456");

        List<StaffDto>  staffDtoList = new ArrayList<>();
        staffDtoList.add(staffDto);
        staffDtoList.add(staffDto1);

        //ACT
        Mockito.when(userService.findAllStaffs()).thenReturn(staffDtoList);

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/iuc/users/staffs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom",Matchers.is(staffDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom",Matchers.is(staffDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].matricule",Matchers.is(staffDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email",Matchers.is(staffDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].posteOccupe",Matchers.is(staffDto.getPosteOccupe())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].filiere",Matchers.is(staffDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password",Matchers.is(staffDto.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom",Matchers.is(staffDto1.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].prenom",Matchers.is(staffDto1.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].matricule",Matchers.is(staffDto1.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email",Matchers.is(staffDto1.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].posteOccupe",Matchers.is(staffDto1.getPosteOccupe())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].filiere",Matchers.is(staffDto1.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].password",Matchers.is(staffDto1.getPassword())));

        Mockito.verify(userService, Mockito.times(1)).findAllStaffs();
    }

    @Test
    public void when_you_find_all_student_by_filiere_it_should_return_all_student() throws Exception {
        //ARRANGE
        StudentDto studentDto = new StudentDto();
        studentDto.setNom("student-nom-test-3");
        studentDto.setPrenom("student-prenom-test-3");
        studentDto.setNiveau("1");
        studentDto.setMatricule("1EIUC2021");
        studentDto.setEmail("student-test-3@iuc.com");
        studentDto.setFiliere("MATHEMATIQUE");
        studentDto.setPassword("1234");

        StudentDto studentDto1 = new StudentDto();
        studentDto1.setNom("student-nom-test-4");
        studentDto1.setPrenom("student-prenom-test-4");
        studentDto1.setNiveau("2");
        studentDto1.setMatricule("1EIUC2024");
        studentDto1.setEmail("student-test-4@iuc.com");
        studentDto1.setFiliere("MATHEMATIQUE");
        studentDto1.setPassword("12345");

        List<StudentDto>  studentDtoList = new ArrayList<>();
        studentDtoList.add(studentDto);
        studentDtoList.add(studentDto1);

        //ACT
        Mockito.when(userService.findAllStudentByFiliere("MATHEMATIQUE")).thenReturn(studentDtoList);

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/iuc/users/students?filiere=MATHEMATIQUE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom",Matchers.is(studentDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom",Matchers.is(studentDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].matricule",Matchers.is(studentDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email",Matchers.is(studentDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].niveau",Matchers.is(studentDto.getNiveau())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].filiere",Matchers.is(studentDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password",Matchers.is(studentDto.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom",Matchers.is(studentDto1.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].prenom",Matchers.is(studentDto1.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].matricule",Matchers.is(studentDto1.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email",Matchers.is(studentDto1.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].niveau",Matchers.is(studentDto1.getNiveau())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].filiere",Matchers.is(studentDto1.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].password",Matchers.is(studentDto1.getPassword())));

        Mockito.verify(userService,Mockito.times(1)).findAllStudentByFiliere("MATHEMATIQUE");

    }


    @Test
     public void when_save_student_it_should_return_student() throws Exception {
        //ARRANGE
        StudentDto studentDto = new StudentDto();
        studentDto.setNom("student-nom-test-3");
        studentDto.setPrenom("student-prenom-test-3");
        studentDto.setNiveau("1");
        studentDto.setMatricule("1EIUC24021");
        studentDto.setEmail("student-test-93@iuc.com");
        studentDto.setFiliere("MATHEMATIQUE");
        studentDto.setPassword("1234");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStudentDto = objectMapper.writeValueAsString(studentDto);

        //ACT
        BDDMockito.given(userService.createStudent(studentDto)).willReturn(studentDto);

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.post("/iuc/users/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStudentDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is(studentDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is(studentDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is(studentDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is(studentDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.niveau",Matchers.is(studentDto.getNiveau())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is(studentDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is(studentDto.getPassword())));

        Mockito.verify(userService, Mockito.times(1)).createStudent(studentDto);
    }


    @Test
    public void when_save_staff_it_should_return_staff() throws Exception {
        //ARRANGE
        StaffDto staffDto = new StaffDto();
        staffDto.setNom("staff-nom-test-3");
        staffDto.setPrenom("staff-prenom-test-3");
        staffDto.setPosteOccupe("staff-chef-dep-test-3");
        staffDto.setMatricule("2CIUC2027");
        staffDto.setEmail("staff-test-34@iuc.com");
        staffDto.setFiliere("MATHEMATIQUE");
        staffDto.setPassword("12345");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStaffDto = objectMapper.writeValueAsString(staffDto);


        //ACT
        BDDMockito.given(userService.createStaff(staffDto)).willReturn(staffDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/iuc/users/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStaffDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is(staffDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is(staffDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is(staffDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is(staffDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posteOccupe",Matchers.is(staffDto.getPosteOccupe())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is(staffDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is(staffDto.getPassword())));

        Mockito.verify(userService, Mockito.times(1)).createStaff(staffDto);
    }

  @Test
  public void when_update_staff_it_should_return_staff() throws Exception {
    //ARRANGE
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-3");
    staffDto.setPrenom("staff-prenom-test-3");
    staffDto.setPosteOccupe("staff-chef-dep-test-3");
    staffDto.setMatricule("2CIUC2027");
    staffDto.setEmail("staff-test-34@iuc.com");
    staffDto.setFiliere("MATHEMATIQUE");
    staffDto.setPassword("12345");
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStaffDto = objectMapper.writeValueAsString(staffDto);


    //ACT
    BDDMockito.given(userService.updateStaff(staffDto)).willReturn(staffDto);
    mockMvc.perform(MockMvcRequestBuilders.put("/iuc/users/staff")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonStaffDto))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is(staffDto.getNom())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is(staffDto.getPrenom())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is(staffDto.getMatricule())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is(staffDto.getEmail())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.posteOccupe",Matchers.is(staffDto.getPosteOccupe())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is(staffDto.getFiliere())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is(staffDto.getPassword())));

    Mockito.verify(userService,Mockito.times(1)).updateStaff(staffDto);
  }

    @Test
    public void when_update_student_it_should_return_a_same_student_with_new_modified_values() throws Exception {
        //ARRANGE
        StudentDto studentDto = new StudentDto();
        studentDto.setNom("student-nom-test-3");
        studentDto.setPrenom("student-prenom-test-3");
        studentDto.setNiveau("1");
        studentDto.setMatricule("1EIUC24021");
        studentDto.setEmail("student-test-93@iuc.com");
        studentDto.setFiliere("MATHEMATIQUE");
        studentDto.setPassword("1234");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStudentDto = objectMapper.writeValueAsString(studentDto);

        //ACT
        BDDMockito.given(userService.updateStudent(studentDto)).willReturn(studentDto);

        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.put("/iuc/users/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStudentDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is(studentDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is(studentDto.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is(studentDto.getMatricule())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is(studentDto.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.niveau",Matchers.is(studentDto.getNiveau())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is(studentDto.getFiliere())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is(studentDto.getPassword())));

        Mockito.verify(userService, Mockito.times(1)).updateStudent(studentDto);
    }







}
