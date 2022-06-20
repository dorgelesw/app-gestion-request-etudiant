package com.iuc.requests.controller;

import com.iuc.requests.dto.RequestDto;
import com.iuc.requests.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/iuc")
public class RequestController {

  @Autowired private RequestService requestService;

  @GetMapping(value = "/request", params = "code")
  public RequestDto findRequestByCode(@RequestParam(value = "code") long requestCode) {
    return requestService.getRequestByCode(requestCode);
  }

  @GetMapping("/request")
  public List<RequestDto> listOfRequest() {
    return requestService.getRequest();
  }
}
