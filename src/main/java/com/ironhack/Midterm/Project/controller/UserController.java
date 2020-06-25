package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/user/{id}/login")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void login(@PathVariable Integer id){ userDetailsService.login(id); }

    @PostMapping("/user/{id}/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@PathVariable Integer id){ userDetailsService.logout(id); }
}
