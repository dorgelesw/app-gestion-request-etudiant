package com.iuc.requests.controller;

import com.iuc.requests.dto.StaffDto;
import com.iuc.requests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/iuc/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/staff")
    public StaffDto findStaffByEmail(@RequestParam(value = "email") @Email String email) {
        return null;
    }

    @GetMapping("/staff")
    public StaffDto findStaffByMatricule(@RequestParam(value = "userRegistration") String userRegistration) {
        return null;
    }

    @GetMapping("/staffs")
    public List<StaffDto> listOfStaffs() {
        return userService.findAllStaffs();
    }

    @PostMapping("/staff")
    public StaffDto save(@RequestBody StaffDto staffDto) {
        return userService.save(staffDto);
    }
}
