package com.iuc.requests.service;


import com.iuc.requests.conf.AppConfigurationTest;
import com.iuc.requests.conf.H2JpaConfiguration;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfigurationTest.class, H2JpaConfiguration.class})
public class UserServiceTest {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private H2JpaConfiguration.Populator populator;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void populate() {
        populator.populateStaff();
        //populator.populateStudent();
    }

    @After
    public void reset(){
        populator.resetStaff();
        //populator.restStudent;
    }

    @Test
    public  void testFindAllStaffs(){

    }




}
