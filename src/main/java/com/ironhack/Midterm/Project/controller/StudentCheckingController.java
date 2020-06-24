package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.model.StudentChecking;
import com.ironhack.Midterm.Project.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentCheckingController {
    @Autowired
    private StudentCheckingService studentCheckingService;

    @GetMapping("/account/studentChecking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking findById(@PathVariable Integer id){
        return studentCheckingService.findById(id);
    }

    @PostMapping("/account/studentChecking/{id}/credit")
    @ResponseStatus(HttpStatus.OK)
    public void credit(@RequestBody String amount, @PathVariable Integer id){
        studentCheckingService.credit(id, amount);
    }

    @PostMapping("/account/studentChecking/{id}/debit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void debit(@RequestBody String amount, @PathVariable Integer id){
        studentCheckingService.debit(id,amount);
    }

}
