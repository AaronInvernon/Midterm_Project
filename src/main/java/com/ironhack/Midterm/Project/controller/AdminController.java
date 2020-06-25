package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.model.Admin;
import com.ironhack.Midterm.Project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/user/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin create(@RequestBody Admin admin){ return adminService.create(admin); }
}
