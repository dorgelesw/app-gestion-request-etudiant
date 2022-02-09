package com.iuc.requests.service;


import com.iuc.requests.conf.AppConfigurationTest;
import com.iuc.requests.conf.H2JpaConfiguration;
import com.iuc.requests.dao.Staff;
import com.iuc.requests.dao.Student;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfigurationTest.class, H2JpaConfiguration.class})
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired ModelMapper modelMapper;

    @Autowired H2JpaConfiguration.Populator populator;

    @Autowired StaffRepository staffRepository;

    @Autowired StudentRepository studentRepository;

    @Autowired
    private UserService userService;


    @Before
    public void populate() {
        //populator.populateStaff();
        //populator.populateStudent();

    }

    @After
    public void reset(){
        populator.resetStaff();
        populator.resetStudent();
    }

    @Test
    public  void when_you_find_all_student_by_filiere_it_should_return_all_student(){

        //Arrange
        Student student1 = new Student();
        student1.setNom("student-nom-test-1");
        student1.setPrenom("student-prenom-test-1");
        student1.setNiveau("1");
        student1.setMatricule("1DIUC2021");
        student1.setEmail("vimaltest1@gmail.com");
        student1.setPassword("1234");
        student1.setFiliere("INFORMATIQUE");
        StudentDto student1Dto = modelMapper.map(student1,StudentDto.class);

        Student student2 = new Student();
        student2.setNom("student-nom-test-2");
        student2.setPrenom("student-prenom-test-2");
        student2.setNiveau("1");
        student2.setMatricule("1CIUC2021");
        student2.setEmail("Student-test-2@iuc.com");
        student2.setPassword("1234");
        student2.setFiliere("INFORMATIQUE");
        StudentDto student2Dto = modelMapper.map(student2,StudentDto.class);

        userService.createStudent(student1Dto);
        userService.createStudent(student2Dto);

        List<StudentDto> expectedStudentDtoList = new ArrayList<>();
        expectedStudentDtoList.add(student1Dto);
        expectedStudentDtoList.add(student2Dto);

        //Act

        List<StudentDto> actualStudentDtoList = new ArrayList<>();
        actualStudentDtoList = userService.findAllStudentByFiliere("INFORMATIQUE");

        //ASSERT
        assertThat(actualStudentDtoList.stream().collect(Collectors.toSet())).isEqualTo(expectedStudentDtoList.stream().collect(Collectors.toSet()));
    }

    @Test
    public void when_save_student_it_should_return_student(){

        //Arrange

        Student student1 = new Student();
        student1.setNom("student-nom-test-1");
        student1.setPrenom("student-prenom-test-1");
        student1.setNiveau("1");
        student1.setMatricule("1DIUC2021");
        student1.setEmail("vimaltest1@gmail.com");
        student1.setPassword("1234");
        student1.setFiliere("INFORMATIQUE");
        StudentDto expectedStudentDto = modelMapper.map(student1,StudentDto.class);

        //Act
        StudentDto actualStudentDto = userService.createStudent(expectedStudentDto);

        //Assert

        assertThat(actualStudentDto.getNiveau()).isEqualTo(expectedStudentDto.getNiveau());
        assertThat(actualStudentDto.getEmail()).isEqualTo(expectedStudentDto.getEmail());
        assertThat(actualStudentDto.getFiliere()).isEqualTo(expectedStudentDto.getFiliere());
        assertThat(actualStudentDto.getMatricule()).isEqualTo(expectedStudentDto.getMatricule());
        assertThat(actualStudentDto.getNom()).isEqualTo(expectedStudentDto.getNom());
        assertThat(actualStudentDto.getPrenom()).isEqualTo(expectedStudentDto.getPrenom());
        assertThat(actualStudentDto.getPassword()).isEqualTo(expectedStudentDto.getPassword());


    }

    @Test
    public void when_find_student_by_email_it_return_student(){

        //ARRANGE
        Student student1 = new Student();
        student1.setNom("student-nom-test-1");
        student1.setPrenom("student-prenom-test-1");
        student1.setNiveau("1");
        student1.setMatricule("1DIUC2021");
        student1.setEmail("vimaltest1@gmail.com");
        student1.setPassword("1234");
        student1.setFiliere("INFORMATIQUE");
        StudentDto expectedStudentDto = modelMapper.map(student1,StudentDto.class);
        userService.createStudent(expectedStudentDto);

        //ACT
        StudentDto actualStudentDto = userService.findStudentByEmail("vimaltest1@gmail.com");

        //ASSERT
        assertThat(actualStudentDto.getNiveau()).isEqualTo(expectedStudentDto.getNiveau());
        assertThat(actualStudentDto.getEmail()).isEqualTo(expectedStudentDto.getEmail());
        assertThat(actualStudentDto.getFiliere()).isEqualTo(expectedStudentDto.getFiliere());
        assertThat(actualStudentDto.getMatricule()).isEqualTo(expectedStudentDto.getMatricule());
        assertThat(actualStudentDto.getNom()).isEqualTo(expectedStudentDto.getNom());
        assertThat(actualStudentDto.getPrenom()).isEqualTo(expectedStudentDto.getPrenom());
        assertThat(actualStudentDto.getPassword()).isEqualTo(expectedStudentDto.getPassword());
    }


    @Test
    public void when_find_student_by_matricule_it_return_student(){

        //ARRANGE
        Student student1 = new Student();
        student1.setNom("student-nom-test-1");
        student1.setPrenom("student-prenom-test-1");
        student1.setNiveau("1");
        student1.setMatricule("1DIUC2021");
        student1.setEmail("vimaltest1@gmail.com");
        student1.setPassword("1234");
        student1.setFiliere("INFORMATIQUE");
        StudentDto expectedStudentDto = modelMapper.map(student1,StudentDto.class);
        userService.createStudent(expectedStudentDto);

        //ACT
        StudentDto actualStudentDto = userService.findStudentByMatricule("1DIUC2021");

        //ASSERT
        assertThat(actualStudentDto.getNiveau()).isEqualTo(expectedStudentDto.getNiveau());
        assertThat(actualStudentDto.getEmail()).isEqualTo(expectedStudentDto.getEmail());
        assertThat(actualStudentDto.getFiliere()).isEqualTo(expectedStudentDto.getFiliere());
        assertThat(actualStudentDto.getMatricule()).isEqualTo(expectedStudentDto.getMatricule());
        assertThat(actualStudentDto.getNom()).isEqualTo(expectedStudentDto.getNom());
        assertThat(actualStudentDto.getPrenom()).isEqualTo(expectedStudentDto.getPrenom());
        assertThat(actualStudentDto.getPassword()).isEqualTo(expectedStudentDto.getPassword());
    }

    @Test
    public void when_delete_student_by_email_you_cannot_get_it_if_you_find_it(){
        //ARRANGE
        Student student1 = new Student();
        student1.setNom("student-nom-test-1");
        student1.setPrenom("student-prenom-test-1");
        student1.setNiveau("1");
        student1.setMatricule("1DIUC2021");
        student1.setEmail("vimaltest1@gmail.com");
        student1.setPassword("1234");
        student1.setFiliere("INFORMATIQUE");
        StudentDto studentDto = modelMapper.map(student1,StudentDto.class);

        //ACT
        StudentDto studentDtoCreated = userService.createStudent(studentDto);
        StudentDto expectedStudentDto = null;
        userService.deleteStudentByEmail("vimaltest1@gmail.com");
        StudentDto actualStudentDto = userService.findStudentByEmail("vimaltest1@gmail.com");

        //ASSERT
        assertThat(studentDtoCreated).isNotNull(); //permet de verifier que le student a bien ete cree
        assertThat(actualStudentDto).isEqualTo(expectedStudentDto);
    }

    @Test
    public void when_delete_student_by_matricule_you_cannot_get_it_if_you_find_it(){

        //ARRANGE
        Student student1 = new Student();
        student1.setNom("student-nom-test-1");
        student1.setPrenom("student-prenom-test-1");
        student1.setNiveau("1");
        student1.setMatricule("1DIUC2021");
        student1.setEmail("vimaltest1@gmail.com");
        student1.setPassword("1234");
        student1.setFiliere("INFORMATIQUE");
        StudentDto studentDto = modelMapper.map(student1,StudentDto.class);

        //ACT
        StudentDto studentDtoCreated = userService.createStudent(studentDto);
        StudentDto expectedStudentDto = null;

        userService.deleteStudentByMatricule("1DIUC2021");
        StudentDto actualStudentDto = userService.findStudentByMatricule("1DIUC2021");

        //ASSERT
        assertThat(studentDtoCreated).isNotNull(); //permet de verifier que le student a bien ete cree
        assertThat(actualStudentDto).isEqualTo(expectedStudentDto);
    }


    @Test
    public void when_save_staff_it_should_return_staff(){
        //Arrange
        Staff staff1 = new Staff();
        staff1.setNom("staff-nom-test-1");
        staff1.setPrenom("staff-prenom-test-1");
        staff1.setPosteOccupe("staff-director-test-1");
        staff1.setMatricule("DIUC2020");
        staff1.setEmail("staff-test-1@iuc.com");
        staff1.setPassword("123489");
        staff1.setFiliere("INFORMATIQUE");
        StaffDto expectedStaffDto = modelMapper.map(staff1,StaffDto.class);

        //Act
        StaffDto actualStaffDto = userService.createStaff(expectedStaffDto);

        //Assert
        assertThat(actualStaffDto.getEmail()).isEqualTo(expectedStaffDto.getEmail());
        assertThat(actualStaffDto.getFiliere()).isEqualTo(expectedStaffDto.getFiliere());
        assertThat(actualStaffDto.getMatricule()).isEqualTo(expectedStaffDto.getMatricule());
        assertThat(actualStaffDto.getNom()).isEqualTo(expectedStaffDto.getNom());
        assertThat(actualStaffDto.getPrenom()).isEqualTo(expectedStaffDto.getPrenom());
        assertThat(actualStaffDto.getPosteOccupe()).isEqualTo(expectedStaffDto.getPosteOccupe());
        assertThat(actualStaffDto.getPassword()).isEqualTo(expectedStaffDto.getPassword());
    }

    @Test
    public void when_find_staff_by_email_it_return_staff(){

        //Arrange
        Staff staff1 = new Staff();
        staff1.setNom("staff-nom-test-1");
        staff1.setPrenom("staff-prenom-test-1");
        staff1.setPosteOccupe("staff-director-test-1");
        staff1.setMatricule("1DIUC2020");
        staff1.setEmail("staff-test-1@iuc.com");
        staff1.setPassword("1234");
        staff1.setFiliere("INFORMATIQUE");
        StaffDto expectedStaffDto = modelMapper.map(staff1,StaffDto.class);
        userService.createStaff(expectedStaffDto);

        //Act
        StaffDto actualStaffDto = userService.findStaffByEmail("staff-test-1@iuc.com");

        //Assert
        assertThat(actualStaffDto.getEmail()).isEqualTo(expectedStaffDto.getEmail());
        assertThat(actualStaffDto.getFiliere()).isEqualTo(expectedStaffDto.getFiliere());
        assertThat(actualStaffDto.getMatricule()).isEqualTo(expectedStaffDto.getMatricule());
        assertThat(actualStaffDto.getNom()).isEqualTo(expectedStaffDto.getNom());
        assertThat(actualStaffDto.getPrenom()).isEqualTo(expectedStaffDto.getPrenom());
        assertThat(actualStaffDto.getPosteOccupe()).isEqualTo(expectedStaffDto.getPosteOccupe());
        assertThat(actualStaffDto.getPassword()).isEqualTo(expectedStaffDto.getPassword());
    }

    @Test
    public void when_find_staff_by_matricule_it_return_staff(){

        //Arrange
        Staff staff1 = new Staff();
        staff1.setNom("staff-nom-test-1");
        staff1.setPrenom("staff-prenom-test-1");
        staff1.setPosteOccupe("staff-director-test-1");
        staff1.setMatricule("1DIUC2020");
        staff1.setEmail("staff-test-1@iuc.com");
        staff1.setPassword("1234");
        staff1.setFiliere("INFORMATIQUE");
        StaffDto expectedStaffDto = modelMapper.map(staff1,StaffDto.class);
        userService.createStaff(expectedStaffDto);

        //Act
        StaffDto actualStaffDto = userService.findStaffByMatricule("1DIUC2020");

        //Assert
        assertThat(actualStaffDto.getEmail()).isEqualTo(expectedStaffDto.getEmail());
        assertThat(actualStaffDto.getFiliere()).isEqualTo(expectedStaffDto.getFiliere());
        assertThat(actualStaffDto.getMatricule()).isEqualTo(expectedStaffDto.getMatricule());
        assertThat(actualStaffDto.getNom()).isEqualTo(expectedStaffDto.getNom());
        assertThat(actualStaffDto.getPrenom()).isEqualTo(expectedStaffDto.getPrenom());
        assertThat(actualStaffDto.getPosteOccupe()).isEqualTo(expectedStaffDto.getPosteOccupe());
        assertThat(actualStaffDto.getPassword()).isEqualTo(expectedStaffDto.getPassword());
    }

    @Test
    public void when_delete_staff_by_email_you_cannot_get_it_if_you_find_it(){

        //Arrange
        Staff staff1 = new Staff();
        staff1.setNom("staff-nom-test-1");
        staff1.setPrenom("staff-prenom-test-1");
        staff1.setPosteOccupe("staff-director-test-1");
        staff1.setMatricule("1DIUC2020");
        staff1.setEmail("staff-test-1@iuc.com");
        staff1.setPassword("1234");
        staff1.setFiliere("INFORMATIQUE");
        StaffDto expectedStaffDto = modelMapper.map(staff1,StaffDto.class);
        userService.createStaff(expectedStaffDto);
        expectedStaffDto = null;

        //Act
        userService.deleteStaffByEmail("staff-test-1@iuc.com");
        StaffDto actualStaffDto = userService.findStaffByEmail("staff-test-1@iuc.com");

        //Assert
        assertThat(actualStaffDto).isEqualTo(expectedStaffDto);
    }

    @Test
    public void when_delete_staff_by_matricule_you_cannot_get_it_if_you_find_it(){

        //Arrange
        Staff staff1 = new Staff();
        staff1.setNom("staff-nom-test-1");
        staff1.setPrenom("staff-prenom-test-1");
        staff1.setPosteOccupe("staff-director-test-1");
        staff1.setMatricule("1DIUC2020");
        staff1.setEmail("staff-test-1@iuc.com");
        staff1.setPassword("1234");
        staff1.setFiliere("INFORMATIQUE");
        StaffDto expectedStaffDto = modelMapper.map(staff1,StaffDto.class);

        //Act
        StaffDto staffDtoCreated=userService.createStaff(expectedStaffDto);
        expectedStaffDto = null;
        userService.deleteStudentByMatricule("1DIUC2020");
        StaffDto actualStaffDto = userService.findStaffByMatricule("1DIUC2020");

        //Assert
        assertThat(staffDtoCreated).isNotNull();
        assertThat(actualStaffDto).isEqualTo(actualStaffDto);
    }


    @Test
    public  void when_you_find_all_staff_it_should_return_all_student(){

        //Arrange
        Staff staff1 = new Staff();
        staff1.setNom("staff-nom-test-1");
        staff1.setPrenom("staff-prenom-test-1");
        staff1.setPosteOccupe("staff-director-test-1");
        staff1.setMatricule("1DIUC2020");
        staff1.setEmail("staff-test-1@iuc.com");
        staff1.setPassword("1234");
        staff1.setFiliere("INFORMATIQUE");
        StaffDto staff1Dto = modelMapper.map(staff1,StaffDto.class);

        Staff staff2 = new Staff();
        staff2.setNom("staff-nom-test-2");
        staff2.setPrenom("staff-prenom-test-2");
        staff2.setPosteOccupe("staff-chef-dep-test-2");
        staff2.setMatricule("1CIUC2020");
        staff2.setEmail("staff-test-2@iuc.com");
        staff2.setPassword("1234");
        staff2.setFiliere("INFORMATIQUE");
        StaffDto staff2Dto = modelMapper.map(staff2,StaffDto.class);

        userService.createStaff(staff1Dto);
        userService.createStaff(staff2Dto);

        List<StaffDto> expectedStaffDtoList = new ArrayList<>();
        expectedStaffDtoList.add(staff1Dto);
        expectedStaffDtoList.add(staff2Dto);

        //Act

        List<StaffDto> actualStaffDtoList = new ArrayList<>();
        actualStaffDtoList = userService.findAllStaffs();

        //ASSERT
        assertThat(actualStaffDtoList.stream().collect(Collectors.toSet())).isEqualTo(expectedStaffDtoList.stream().collect(Collectors.toSet()));

    }

    @Test
    public void when_update_student_it_should_return_a_same_student_with_new_modified_values(){

        //Arrange
        Student student1 = new Student();
        student1.setNom("student-nom-test-1");
        student1.setPrenom("student-prenom-test-1");
        student1.setNiveau("1");
        student1.setMatricule("1DIUC2021");
        student1.setEmail("vimaltest1@gmail.com");
        student1.setPassword("1234");
        student1.setFiliere("INFORMATIQUE");
        StudentDto studentDto = modelMapper.map(student1,StudentDto.class);

        //Act
        StudentDto persistedStudentDto = userService.createStudent(studentDto);
        persistedStudentDto.setNom("student-nom-test-1-update");
        persistedStudentDto.setFiliere("INFORMATIQUE-u");
        persistedStudentDto.setNiveau("2");
        persistedStudentDto.setPrenom("student-prenom-test-1-upadte");
        persistedStudentDto.setPassword("1234update");

        StudentDto updatedStudentDto = userService.updateStudent(persistedStudentDto);

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
        Staff staff1 = new Staff();
        staff1.setNom("staff-nom-test-1");
        staff1.setPrenom("staff-prenom-test-1");
        staff1.setPosteOccupe("staff-director-test-1");
        staff1.setMatricule("1DIUC2020");
        staff1.setEmail("staff-test-1@iuc.com");
        staff1.setPassword("1234");
        staff1.setFiliere("INFORMATIQUE");
        StaffDto staffDto = modelMapper.map(staff1,StaffDto.class);

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
