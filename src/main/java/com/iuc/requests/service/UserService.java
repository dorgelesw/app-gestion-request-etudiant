package com.iuc.requests.service;

import com.iuc.requests.dao.Staff;
import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.repository.StaffRepository;
import com.iuc.requests.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  @Autowired ModelMapper modelMapper;
  @Autowired private StaffRepository staffRepository;
  @Autowired private StudentRepository studentRepository;

  public List<StaffDto> findAllStaffs() {

    Iterable<Staff> staffs = staffRepository.findAll();
    List<StaffDto> staffsDto = new ArrayList<>();
    staffs.forEach(
        staff -> {
          staffsDto.add(modelMapper.map(staff, StaffDto.class));
        });

    return staffsDto;
  }

  public StaffDto save(StaffDto staffDto) {
    Staff staff = staffRepository.save(modelMapper.map(staffDto, Staff.class));
    return modelMapper.map(staff, StaffDto.class);
  }
}
