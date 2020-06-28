package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.model.CreditCard;
import com.ironhack.Midterm.Project.model.Savings;
import com.ironhack.Midterm.Project.service.CreditCardService;
import com.ironhack.Midterm.Project.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;


    @PostMapping("/account/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard create(CreditCard cc, @RequestParam(required = false) String interestRate, @RequestParam(required = false) String minimumBalance){
        return creditCardService.create(cc, interestRate, minimumBalance);
    }
}
