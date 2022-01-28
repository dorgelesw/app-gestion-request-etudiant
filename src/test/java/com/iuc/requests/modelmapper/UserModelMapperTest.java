package com.iuc.requests.modelmapper;

import com.iuc.requests.conf.AppConfigurationTest;
import com.iuc.requests.conf.H2JpaConfiguration;
import com.iuc.requests.dao.Staff;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.dao.Student;
import com.iuc.requests.dto.StudentDto;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {AppConfigurationTest.class, H2JpaConfiguration.class})
public class UserModelMapperTest {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    H2JpaConfiguration.Populator populator;

    @Autowired
    StaffRepository staffRepository;
    StudentRepository studentRepository;

    @Before
    public void populate() {
        populator.populateStaff();

    }

    @After
    public void reset() {
        populator.resetStaff();

    }

    @Test
    public void testModelMapperStaffToStaffDto() {

        Staff staff = staffRepository.findByEmail("staff-test-1@iuc.com");
        StaffDto staffDto = modelMapper.map(staff, StaffDto.class);

        assertThat(staff.getNom()).isEqualTo(staffDto.getNom());
        assertThat(staff.getPrenom()).isEqualTo(staffDto.getPrenom());
        assertThat(staff.getPosteOccupe()).isEqualTo(staffDto.getPosteOccupe());
        assertThat(staff.getMatricule()).isEqualTo(staffDto.getMatricule());
        assertThat(staff.getEmail()).isEqualTo(staffDto.getEmail());
        assertThat(staff.getFiliere()).isEqualTo(staffDto.getFiliere());
    }

    @Test
    public void testModelMapperStaffDtoToStaff() {

        StaffDto staffDto = new StaffDto();
        staffDto.setNom("staff-nom-test-3");
        staffDto.setPrenom("staff-prenom-test-3");
        staffDto.setPosteOccupe("staff-chef-dep-test-3");
        staffDto.setMatricule("2CIUC2020");
        staffDto.setEmail("staff-test-3@iuc.com");
        staffDto.setFiliere("MATHEMATIQUE");

        Staff staff = modelMapper.map(staffDto, Staff.class);

        assertThat(staff.getNom()).isEqualTo(staffDto.getNom());
        assertThat(staff.getPrenom()).isEqualTo(staffDto.getPrenom());
        assertThat(staff.getPosteOccupe()).isEqualTo(staffDto.getPosteOccupe());
        assertThat(staff.getMatricule()).isEqualTo(staffDto.getMatricule());
        assertThat(staff.getEmail()).isEqualTo(staffDto.getEmail());
        assertThat(staff.getFiliere()).isEqualTo(staffDto.getFiliere());

        staff.setPassword("1234");
        Staff persistedStaff = staffRepository.save(staff);
        Staff retrievedStaff = staffRepository.findByEmail("staff-test-3@iuc.com");

        assertThat(persistedStaff.getNom()).isEqualTo(retrievedStaff.getNom());
        assertThat(persistedStaff.getPrenom()).isEqualTo(retrievedStaff.getPrenom());
        assertThat(persistedStaff.getPosteOccupe()).isEqualTo(retrievedStaff.getPosteOccupe());
        assertThat(persistedStaff.getMatricule()).isEqualTo(retrievedStaff.getMatricule());
        assertThat(persistedStaff.getEmail()).isEqualTo(retrievedStaff.getEmail());
        assertThat(persistedStaff.getFiliere()).isEqualTo(retrievedStaff.getFiliere());
        assertThat(persistedStaff.getPassword()).isEqualTo(retrievedStaff.getPassword());
    }
    @Test
    public void testModelMapperStudentToStudentDto() {

        Student student = studentRepository.findByEmail("student-test-1@iuc.com");
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);

        assertThat(student.getNom()).isEqualTo(studentDto.getNom());
        assertThat(student.getPrenom()).isEqualTo(studentDto.getPrenom());
        assertThat(student.getNiveau()).isEqualTo(studentDto.getNiveau());
        assertThat(student.getMatricule()).isEqualTo(studentDto.getMatricule());
        assertThat(student.getEmail()).isEqualTo(studentDto.getEmail());
        assertThat(student.getFiliere()).isEqualTo(studentDto.getFiliere());
    }

    @Test
    public void testModelMapperStudentDtoToStudent() {

        StudentDto studentDto = new StudentDto();
        studentDto.setNom("student-nom-test-3");
        studentDto.setPrenom("student-prenom-test-3");
        studentDto.setNiveau("student-level-1-test-3");
        studentDto.setMatricule("2CIUC2020");
        studentDto.setEmail("student-test-3@iuc.com");
        studentDto.setFiliere("MATHEMATIQUE");

        Student student = modelMapper.map( studentDto, Student.class);

        assertThat(student.getNom()).isEqualTo(studentDto.getNom());
        assertThat(student.getPrenom()).isEqualTo(studentDto.getPrenom());
        assertThat(student.getNiveau()).isEqualTo(studentDto.getNiveau());
        assertThat(student.getMatricule()).isEqualTo(studentDto.getMatricule());
        assertThat(student.getEmail()).isEqualTo(studentDto.getEmail());
        assertThat(student.getFiliere()).isEqualTo(studentDto.getFiliere());

        student.setPassword("1234");
        Student persistedStudent = studentRepository.save(student);
        Student retrievedStudent = studentRepository.findByEmail("student-test-3@iuc.com");

        assertThat(persistedStudent.getNom()).isEqualTo(retrievedStudent.getNom());
        assertThat(persistedStudent.getPrenom()).isEqualTo(retrievedStudent.getPrenom());
        assertThat(persistedStudent.getNiveau()).isEqualTo(retrievedStudent.getNiveau());
        assertThat(persistedStudent.getMatricule()).isEqualTo(retrievedStudent.getMatricule());
        assertThat(persistedStudent.getEmail()).isEqualTo(retrievedStudent.getEmail());
        assertThat(persistedStudent.getFiliere()).isEqualTo(retrievedStudent.getFiliere());
        assertThat(persistedStudent.getPassword()).isEqualTo(retrievedStudent.getPassword());
    }
}
