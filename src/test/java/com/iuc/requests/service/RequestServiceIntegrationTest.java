package com.iuc.requests.service;

import com.iuc.requests.conf.AppConfigurationTest;
import com.iuc.requests.conf.H2JpaConfiguration;
import com.iuc.requests.dto.RequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AppConfigurationTest.class, H2JpaConfiguration.class})
public class RequestServiceIntegrationTest {
  @Autowired H2JpaConfiguration.Populator populator;
  @Autowired RequestService requestService;

  @Test
  public void
      giving_existing_request_by_requestCode_search_request_should_return_correct_request_code() {
    populator.populateRequest();
    RequestDto requestByCode = requestService.getRequestByCode(1L);
    Assertions.assertNotNull(requestByCode); // object should not be null
    Assertions.assertEquals(
        "Student1", requestByCode.getRequestType()); // request type should be as db
    populator.resetRequest();
  }

  @Test
  public void giving_no_exist_request_by_requestCode_search_request_should_return_null() {
    populator.populateRequest();
    RequestDto requestByCode = requestService.getRequestByCode(11L);
    Assertions.assertNull(requestByCode); // object should not be null
    populator.resetRequest();
  }
}
