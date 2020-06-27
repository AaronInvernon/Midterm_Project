package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.model.Savings;
import com.ironhack.Midterm.Project.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SavingsController {

    @Autowired
    private SavingsService savingsService;


    @PostMapping("/account/saving")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings create(Savings s, @RequestParam(required = false) String interestRate, @RequestParam(required = false) String minimumBalance){
        return savingsService.create(s, interestRate, minimumBalance);
    }

}
