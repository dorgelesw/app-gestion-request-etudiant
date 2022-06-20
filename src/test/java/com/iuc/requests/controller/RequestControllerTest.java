package com.iuc.requests.controller;

import com.iuc.requests.dto.RequestDto;
import com.iuc.requests.service.RequestService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(RequestController.class)
class RequestControllerTest {
  @Autowired MockMvc mockMvc;
  @MockBean RequestService requestService;
  List<RequestDto> requestDtoList = new ArrayList<>();

  @Test
  void giving_requestCode_should_search_and_return_a_request_dto() throws Exception {
    Long code = 1L;
    RequestDto requestDto = new RequestDto();
    requestDto.setRequestCode(code);
    requestDto.setRequestType("Student1");
    requestDto.setRequestReason("Test");
    requestDto.setComment("onlineTest");
    requestDto.setRequestStatut("INFORMATIQUE");

    Mockito.doReturn(requestDto).when(requestService).getRequestByCode(code);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/iuc/request").param("code", "1"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.requestType", Matchers.is(requestDto.getRequestType())));
  }

  @Test
  void search_and_return_a_list_of_request_dto() throws Exception {
    RequestDto requestDto = new RequestDto();
    requestDto.setRequestCode(1L);
    requestDto.setRequestType("Student1");
    requestDto.setRequestReason("Test");
    requestDto.setComment("onlineTest");
    requestDto.setRequestStatut("INFORMATIQUE");
    requestDtoList.add(requestDto);

    Mockito.doReturn(requestDtoList).when(requestService).getRequest();

    mockMvc
        .perform(MockMvcRequestBuilders.get("/iuc/request"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
