package com.iuc.requests.service;

import com.iuc.requests.conf.AppConfigurationTest;
import com.iuc.requests.conf.H2JpaConfiguration;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AppConfigurationTest.class, H2JpaConfiguration.class})
public class UserServiceIntegrationTest {

  @Autowired H2JpaConfiguration.Populator populator;

  @Autowired UserService userService;

  @Test
  public void giving_2_existing_staffs_findAllStaff_should_be_equal_to_2() {
    populator.populateStaff();
    assertThat(userService.findAllStaffs()).hasSize(2);
    populator.resetStaff();
  }

  @AfterEach
  public void reset() {
    populator.resetStaff();
    populator.resetStudent();
  }

  @Test
  public void giving_a_staff_he_should_be_exist_after_create() {
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-1");
    staffDto.setPrenom("staff-prenom-test-1");
    staffDto.setPosteOccupe("staff-chef-dep-test-1");
    staffDto.setMatricule("2CIUC2020");
    staffDto.setEmail("staff-test-1@iuc.com");
    staffDto.setFiliere("MATHEMATIQUE");
    StaffDto staffDtoCreated = userService.createStaff(staffDto);
    assertThat(staffDtoCreated).isNotNull();
    assertThat(staffDtoCreated).isEqualTo(staffDto);
  }

  @Test
  public  void when_you_find_all_student_by_filiere_it_should_return_all_student(){

    //Arrange
    StudentDto studentDto1 = new StudentDto();
    studentDto1.setNom("student-nom-test-1");
    studentDto1.setPrenom("student-prenom-test-1");
    studentDto1.setNiveau("1");
    studentDto1.setMatricule("1DIUC2021");
    studentDto1.setEmail("vimaltest1@gmail.com");
    studentDto1.setPassword("1234");
    studentDto1.setFiliere("INFORMATIQUE");


    StudentDto studentDto2 = new StudentDto();
    studentDto2.setNom("student-nom-test-2");
    studentDto2.setPrenom("student-prenom-test-2");
    studentDto2.setNiveau("1");
    studentDto2.setMatricule("1CIUC2021");
    studentDto2.setEmail("Student-test-2@iuc.com");
    studentDto2.setPassword("1234");
    studentDto2.setFiliere("INFORMATIQUE");

    userService.createStudent(studentDto1);
    userService.createStudent(studentDto2);

    List<StudentDto> expectedStudentDtoList = new ArrayList<>();
    expectedStudentDtoList.add(studentDto1);
    expectedStudentDtoList.add(studentDto2);

    //Act
    List<StudentDto> actualStudentDtoList = new ArrayList<>();
    actualStudentDtoList = userService.findAllStudentByFiliere("INFORMATIQUE");

    //ASSERT
    assertThat(userService.findAllStudentByFiliere("INFORMATIQUE")).hasSize(2);
    assertThat(actualStudentDtoList.stream().collect(Collectors.toSet())).isEqualTo(expectedStudentDtoList.stream().collect(Collectors.toSet()));
  }

  @Test
  public void when_save_student_it_should_return_student(){

    //Arrange
    StudentDto studentDto = new StudentDto();
    studentDto.setNom("student-nom-test-1");
    studentDto.setPrenom("student-prenom-test-1");
    studentDto.setNiveau("1");
    studentDto.setMatricule("1DIUC20214");
    studentDto.setEmail("vimaltest14@gmail.com");
    studentDto.setPassword("1234");
    studentDto.setFiliere("INFORMATIQUE");

    //Act
    StudentDto studentDtoCreated = userService.createStudent(studentDto);

    //Assert
    assertThat(studentDtoCreated).isNotNull();
    assertThat(studentDtoCreated.getNiveau()).isEqualTo(studentDto.getNiveau());
    assertThat(studentDtoCreated.getEmail()).isEqualTo(studentDto.getEmail());
    assertThat(studentDtoCreated.getFiliere()).isEqualTo(studentDto.getFiliere());
    assertThat(studentDtoCreated.getMatricule()).isEqualTo(studentDto.getMatricule());
    assertThat(studentDtoCreated.getNom()).isEqualTo(studentDto.getNom());
    assertThat(studentDtoCreated.getPrenom()).isEqualTo(studentDto.getPrenom());
    assertThat(studentDtoCreated.getPassword()).isEqualTo(studentDto.getPassword());
  }

  @Test
  public void when_find_student_by_email_it_return_student(){

    //ARRANGE
    StudentDto studentDto = new StudentDto();
    studentDto.setNom("student-nom-test-1");
    studentDto.setPrenom("student-prenom-test-1");
    studentDto.setNiveau("1");
    studentDto.setMatricule("1DIUC52021");
    studentDto.setEmail("vimaltest13@gmail.com");
    studentDto.setPassword("1234");
    studentDto.setFiliere("INFORMATIQUE");
    StudentDto studentDtoCreated =userService.createStudent(studentDto);

    //ACT
    StudentDto actualStudentDto = userService.findStudentByEmail("vimaltest13@gmail.com");

    //ASSERT
    assertThat(studentDtoCreated).isNotNull();
    assertThat(actualStudentDto).isNotNull();
    assertThat(actualStudentDto.getNiveau()).isEqualTo(studentDtoCreated.getNiveau());
    assertThat(actualStudentDto.getEmail()).isEqualTo(studentDtoCreated.getEmail());
    assertThat(actualStudentDto.getFiliere()).isEqualTo(studentDtoCreated.getFiliere());
    assertThat(actualStudentDto.getMatricule()).isEqualTo(studentDtoCreated.getMatricule());
    assertThat(actualStudentDto.getNom()).isEqualTo(studentDtoCreated.getNom());
    assertThat(actualStudentDto.getPrenom()).isEqualTo(studentDtoCreated.getPrenom());
    assertThat(actualStudentDto.getPassword()).isEqualTo(studentDtoCreated.getPassword());
  }

  @Test
  public void when_find_student_by_matricule_it_return_student(){

    //ARRANGE
    StudentDto studentDto = new StudentDto();
    studentDto.setNom("student-nom-test-1");
    studentDto.setPrenom("student-prenom-test-1");
    studentDto.setNiveau("1");
    studentDto.setMatricule("1DIUC52021");
    studentDto.setEmail("vimaltest16@gmail.com");
    studentDto.setPassword("1234");
    studentDto.setFiliere("INFORMATIQUE");

    //ACT
    StudentDto studentDtoCreated =userService.createStudent(studentDto);
    StudentDto actualStudentDto = userService.findStudentByMatricule(studentDtoCreated.getMatricule());

    //ASSERT
    assertThat(studentDtoCreated).isNotNull();
    assertThat(actualStudentDto.getNiveau()).isEqualTo(studentDtoCreated.getNiveau());
    assertThat(actualStudentDto.getEmail()).isEqualTo(studentDtoCreated.getEmail());
    assertThat(actualStudentDto.getFiliere()).isEqualTo(studentDtoCreated.getFiliere());
    assertThat(actualStudentDto.getMatricule()).isEqualTo(studentDtoCreated.getMatricule());
    assertThat(actualStudentDto.getNom()).isEqualTo(studentDtoCreated.getNom());
    assertThat(actualStudentDto.getPrenom()).isEqualTo(studentDtoCreated.getPrenom());
    assertThat(actualStudentDto.getPassword()).isEqualTo(studentDtoCreated.getPassword());
  }

  @Test
  public void when_delete_student_by_email_you_cannot_get_it_if_you_find_it(){
    //ARRANGE
    StudentDto studentDto = new StudentDto();
    studentDto.setNom("student-nom-test-1");
    studentDto.setPrenom("student-prenom-test-1");
    studentDto.setNiveau("1");
    studentDto.setMatricule("1DIUCe2021");
    studentDto.setEmail("vimaltest1e@gmail.com");
    studentDto.setPassword("1234");
    studentDto.setFiliere("INFORMATIQUE");


    //ACT
    StudentDto studentDtoCreated = userService.createStudent(studentDto);
    userService.deleteStudentByEmail(studentDtoCreated.getEmail());
    StudentDto actualStudentDto = userService.findStudentByEmail(studentDtoCreated.getEmail());

    //ASSERT
    assertThat(studentDtoCreated).isNotNull(); //permet de verifier que le student a bien ete cree
    assertThat(actualStudentDto).isNull();
  }

  @Test
  public void when_delete_student_by_matricule_you_cannot_get_it_if_you_find_it(){

    //ARRANGE
    StudentDto studentDto = new StudentDto();
    studentDto.setNom("student-nom-test-1");
    studentDto.setPrenom("student-prenom-test-1");
    studentDto.setNiveau("1");
    studentDto.setMatricule("1DIUC2021");
    studentDto.setEmail("vimaltest1@gmail.com");
    studentDto.setPassword("1234");
    studentDto.setFiliere("INFORMATIQUE");

    //ACT
    StudentDto studentDtoCreated = userService.createStudent(studentDto);
    userService.deleteStudentByMatricule(studentDtoCreated.getMatricule());
    StudentDto actualStudentDto = userService.findStudentByMatricule(studentDtoCreated.getMatricule());

    //ASSERT
    assertThat(studentDtoCreated).isNotNull(); //permet de verifier que le student a bien ete cree
    assertThat(actualStudentDto).isNull();
  }

  @Test
  public void when_save_staff_it_should_return_staff(){
    //Arrange
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-1");
    staffDto.setPrenom("staff-prenom-test-1");
    staffDto.setPosteOccupe("staff-director-test-1");
    staffDto.setMatricule("DIUC2020");
    staffDto.setEmail("staff-test-1@iuc.com");
    staffDto.setPassword("123489");
    staffDto.setFiliere("INFORMATIQUE");

    //Act
    StaffDto createdStaffDto = userService.createStaff(staffDto);

    //Assert
    assertThat(createdStaffDto).isNotNull();
    assertThat(createdStaffDto.getEmail()).isEqualTo(staffDto.getEmail());
    assertThat(createdStaffDto.getFiliere()).isEqualTo(staffDto.getFiliere());
    assertThat(createdStaffDto.getMatricule()).isEqualTo(staffDto.getMatricule());
    assertThat(createdStaffDto.getNom()).isEqualTo(staffDto.getNom());
    assertThat(createdStaffDto.getPrenom()).isEqualTo(staffDto.getPrenom());
    assertThat(createdStaffDto.getPosteOccupe()).isEqualTo(staffDto.getPosteOccupe());
    assertThat(createdStaffDto.getPassword()).isEqualTo(staffDto.getPassword());
  }

  @Test
  public void when_find_staff_by_email_it_return_staff(){

    //Arrange
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-1");
    staffDto.setPrenom("staff-prenom-test-1");
    staffDto.setPosteOccupe("staff-director-test-1");
    staffDto.setMatricule("1DIUC2020");
    staffDto.setEmail("staff-test-1@iuc.com");
    staffDto.setPassword("1234");
    staffDto.setFiliere("INFORMATIQUE");

    //Act
    StaffDto createdStaffDto = userService.createStaff(staffDto);
    StaffDto actualStaffDto = userService.findStaffByEmail(createdStaffDto.getEmail());

    //Assert
    assertThat(createdStaffDto).isNotNull();
    assertThat(actualStaffDto.getEmail()).isEqualTo(createdStaffDto.getEmail());
    assertThat(actualStaffDto.getFiliere()).isEqualTo(createdStaffDto.getFiliere());
    assertThat(actualStaffDto.getMatricule()).isEqualTo(createdStaffDto.getMatricule());
    assertThat(actualStaffDto.getNom()).isEqualTo(createdStaffDto.getNom());
    assertThat(actualStaffDto.getPrenom()).isEqualTo(createdStaffDto.getPrenom());
    assertThat(actualStaffDto.getPosteOccupe()).isEqualTo(createdStaffDto.getPosteOccupe());
    assertThat(actualStaffDto.getPassword()).isEqualTo(createdStaffDto.getPassword());
  }

  @Test
  public void when_find_staff_by_matricule_it_return_staff(){

    //Arrange
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-1");
    staffDto.setPrenom("staff-prenom-test-1");
    staffDto.setPosteOccupe("staff-director-test-1");
    staffDto.setMatricule("1DIUC2020");
    staffDto.setEmail("staff-test-1@iuc.com");
    staffDto.setPassword("1234");
    staffDto.setFiliere("INFORMATIQUE");

    //Act
    StaffDto createdStaffDto = userService.createStaff(staffDto);
    StaffDto actualStaffDto = userService.findStaffByMatricule(createdStaffDto.getMatricule());

    //Assert
    assertThat(createdStaffDto).isNotNull();
    assertThat(actualStaffDto.getEmail()).isEqualTo(createdStaffDto.getEmail());
    assertThat(actualStaffDto.getFiliere()).isEqualTo(createdStaffDto.getFiliere());
    assertThat(actualStaffDto.getMatricule()).isEqualTo(createdStaffDto.getMatricule());
    assertThat(actualStaffDto.getNom()).isEqualTo(createdStaffDto.getNom());
    assertThat(actualStaffDto.getPrenom()).isEqualTo(createdStaffDto.getPrenom());
    assertThat(actualStaffDto.getPosteOccupe()).isEqualTo(createdStaffDto.getPosteOccupe());
    assertThat(actualStaffDto.getPassword()).isEqualTo(createdStaffDto.getPassword());
  }

  @Test
  public void when_delete_staff_by_email_you_cannot_get_it_if_you_find_it(){

    //Arrange
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-1");
    staffDto.setPrenom("staff-prenom-test-1");
    staffDto.setPosteOccupe("staff-director-test-1");
    staffDto.setMatricule("1DIUC2020");
    staffDto.setEmail("staff-test-1@iuc.com");
    staffDto.setPassword("1234");
    staffDto.setFiliere("INFORMATIQUE");

    //Act
    StaffDto staffDtoCreated = userService.createStaff(staffDto);
    userService.deleteStaffByEmail(staffDto.getEmail());
    StaffDto actualStaffDto = userService.findStaffByEmail(staffDto.getEmail());

    //Assert
    assertThat(actualStaffDto).isNull();
    assertThat(staffDtoCreated).isNotNull();
  }

  @Test
  public void when_delete_staff_by_matricule_you_cannot_get_it_if_you_find_it(){

    //Arrange
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-1");
    staffDto.setPrenom("staff-prenom-test-1");
    staffDto.setPosteOccupe("staff-director-test-1");
    staffDto.setMatricule("1DIUC2020");
    staffDto.setEmail("staff-test-1@iuc.com");
    staffDto.setPassword("1234");
    staffDto.setFiliere("INFORMATIQUE");

    //Act
    StaffDto staffDtoCreated = userService.createStaff(staffDto);
    userService.deleteStaffByMatricule(staffDto.getMatricule());
    StaffDto actualStaffDto = userService.findStaffByMatricule(staffDto.getMatricule());

    //Assert
    assertThat(staffDtoCreated).isNotNull();
    assertThat(actualStaffDto).isNull();
  }


  @Test
  public  void when_you_find_all_staff_it_should_return_all_staff(){

    //Arrange
    StaffDto staffDto1 = new StaffDto();
    staffDto1.setNom("staff-nom-test-1");
    staffDto1.setPrenom("staff-prenom-test-1");
    staffDto1.setPosteOccupe("staff-director-test-1");
    staffDto1.setMatricule("1DIUC2020");
    staffDto1.setEmail("staff-test-1@iuc.com");
    staffDto1.setPassword("1234");
    staffDto1.setFiliere("INFORMATIQUE");

    StaffDto staffDto2 = new StaffDto();
    staffDto2.setNom("staff-nom-test-2");
    staffDto2.setPrenom("staff-prenom-test-2");
    staffDto2.setPosteOccupe("staff-chef-dep-test-2");
    staffDto2.setMatricule("1CIUC2020");
    staffDto2.setEmail("staff-test-2@iuc.com");
    staffDto2.setPassword("1234");
    staffDto2.setFiliere("INFORMATIQUE");

    userService.createStaff(staffDto1);
    userService.createStaff(staffDto2);
    List<StaffDto> expectedStaffDtoList = new ArrayList<>();
    expectedStaffDtoList.add(staffDto1);
    expectedStaffDtoList.add(staffDto2);

    //Act
    List<StaffDto> actualStaffDtoList = new ArrayList<>();
    actualStaffDtoList = userService.findAllStaffs();

    //ASSERT
    assertThat(actualStaffDtoList).hasSize(2);
    assertThat(actualStaffDtoList.stream().collect(Collectors.toSet())).isEqualTo(expectedStaffDtoList.stream().collect(Collectors.toSet()));

  }

  @Test
  public void when_update_student_it_should_return_a_same_student_with_new_modified_values(){

    //Arrange
    StudentDto studentDto = new StudentDto();
    studentDto.setNom("student-nom-test-1");
    studentDto.setPrenom("student-prenom-test-1");
    studentDto.setNiveau("1");
    studentDto.setMatricule("1DIUC2021");
    studentDto.setEmail("vimaltest1@gmail.com");
    studentDto.setPassword("1234");
    studentDto.setFiliere("INFORMATIQUE");

    //Act
    StudentDto createdStudentDto = userService.createStudent(studentDto);
    createdStudentDto.setNom("student-nom-test-1-update");
    createdStudentDto.setFiliere("INFORMATIQUE-u");
    createdStudentDto.setNiveau("2");
    createdStudentDto.setPrenom("student-prenom-test-1-upadte");
    createdStudentDto.setPassword("1234update");
    StudentDto updatedStudentDto = userService.updateStudent(createdStudentDto);

    //Assert
    assertThat(updatedStudentDto.getNiveau()).isNotEqualTo(studentDto.getNiveau());
    assertThat(updatedStudentDto.getEmail()).isEqualTo(studentDto.getEmail());
    assertThat(updatedStudentDto.getFiliere()).isNotEqualTo(studentDto.getFiliere());
    assertThat(updatedStudentDto.getMatricule()).isEqualTo(studentDto.getMatricule());
    assertThat(updatedStudentDto.getNom()).isNotEqualTo(studentDto.getNom());
    assertThat(updatedStudentDto.getPrenom()).isNotEqualTo(studentDto.getPrenom());
    assertThat(updatedStudentDto.getPassword()).isNotEqualTo(studentDto.getPassword());

  }

  @Test
  public void when_update_staff_it_should_return_a_same_staff_with_new_modified_values(){

    //Arrange
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-1");
    staffDto.setPrenom("staff-prenom-test-1");
    staffDto.setPosteOccupe("staff-director-test-1");
    staffDto.setMatricule("1DIUC2020");
    staffDto.setEmail("staff-test-1@iuc.com");
    staffDto.setPassword("1234");
    staffDto.setFiliere("INFORMATIQUE");

    //Act
    StaffDto persistedStaffDto = userService.createStaff(staffDto);
    persistedStaffDto.setNom("staff-nom-test-1-update");
    persistedStaffDto.setFiliere("INFORMATIQUE-u");
    persistedStaffDto.setPrenom("staff-prenom-test-1-upadte");
    persistedStaffDto.setPassword("1234update");
    persistedStaffDto.setPosteOccupe("staff-director-test-1-up");
    StaffDto updatedStaffDto = userService.updateStaff(persistedStaffDto);

    //Assert
    assertThat(updatedStaffDto.getEmail()).isEqualTo(staffDto.getEmail());
    assertThat(updatedStaffDto.getFiliere()).isNotEqualTo(staffDto.getFiliere());
    assertThat(updatedStaffDto.getMatricule()).isEqualTo(staffDto.getMatricule());
    assertThat(updatedStaffDto.getNom()).isNotEqualTo(staffDto.getNom());
    assertThat(updatedStaffDto.getPrenom()).isNotEqualTo(staffDto.getPrenom());
    assertThat(updatedStaffDto.getPassword()).isNotEqualTo(staffDto.getPassword());
    assertThat(updatedStaffDto.getPosteOccupe()).isNotEqualTo(staffDto.getPosteOccupe());

  }
}
