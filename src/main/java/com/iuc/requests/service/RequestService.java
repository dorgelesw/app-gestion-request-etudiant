package com.iuc.requests.service;

import com.iuc.requests.dao.Request;
import com.iuc.requests.dto.RequestDto;
import com.iuc.requests.repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {
  @Autowired ModelMapper modelMapper;
  @Autowired private RequestRepository requestRepository;

  public List<RequestDto> getRequest() {
    Iterable<Request> requests = requestRepository.findAll();
    List<RequestDto> requestDto = new ArrayList<>();
    requests.forEach(
        request -> {
          requestDto.add(modelMapper.map(request, RequestDto.class));
        });

    return requestDto;
  }

  public RequestDto getRequestByCode(Long code) {

    Request request = requestRepository.findByRequestCode(code); // appel une method

    if (request != null) {
      RequestDto requestDto =
          modelMapper.map(request, RequestDto.class); // convert a request to a requestdto
      return requestDto;

    } else return null;
  }

  public RequestDto save(RequestDto requestDto) {
    Request request = requestRepository.save(modelMapper.map(requestDto, Request.class));
    return modelMapper.map(request, RequestDto.class);
  }
}
