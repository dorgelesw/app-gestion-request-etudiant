package com.iuc.requests.controller;

import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/iuc/users")
public class StaffController {

    @Autowired
    private UserService userService;

    @GetMapping("/staff")
    public StaffDto getStaffByEmail(@RequestParam(value = "email") String email) {
        return null;
    }

    @GetMapping("/staff")
    public StaffDto getStaffByRegistration(@RequestParam(value = "userRegistration") String userRegistration) {
        return null;
    }

    @GetMapping("/staffs")
    public List<StaffDto> listOfStaffs() {
        return null;
    }
}
