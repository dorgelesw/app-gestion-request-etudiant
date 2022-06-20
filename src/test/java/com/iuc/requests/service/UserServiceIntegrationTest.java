package com.iuc.requests.service;

import com.iuc.requests.conf.AppConfigurationTest;
import com.iuc.requests.conf.H2JpaConfiguration;
import com.iuc.requests.dto.StaffDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

  @Test
  public void giving_a_staff_he_should_be_exist_after_create() {
    StaffDto staffDto = new StaffDto();
    staffDto.setNom("staff-nom-test-1");
    staffDto.setPrenom("staff-prenom-test-1");
    staffDto.setPosteOccupe("staff-chef-dep-test-1");
    staffDto.setMatricule("2CIUC2020");
    staffDto.setEmail("staff-test-1@iuc.com");
    staffDto.setFiliere("MATHEMATIQUE");
    StaffDto staffDtoCreated = userService.save(staffDto);
    assertThat(staffDtoCreated).isNotNull();
    assertThat(staffDtoCreated).isEqualTo(staffDto);
  }


}
