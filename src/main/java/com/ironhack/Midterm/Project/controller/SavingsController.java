package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.model.Savings;
import com.ironhack.Midterm.Project.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class SavingsController {

    @Autowired
    private SavingsService savingsService;

    @GetMapping("/account/saving/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings findById(@PathVariable Integer id){
        return savingsService.findById(id);
    }

    @GetMapping("/account/saving/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal findBalanceById(@PathVariable Integer id){ return  savingsService.findBalanceById(id); }

    @PostMapping("/account/saving")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings create(Savings s, @RequestParam(required = false) String interestRate, @RequestParam(required = false) String minimumBalance){
        return savingsService.create(s, interestRate, minimumBalance);
    }

    @PostMapping("/account/saving/{id}/credit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void credit(@RequestBody String amount, @PathVariable Integer id){
        savingsService.credit(id, amount);
    }

    @PostMapping("/account/saving/{id}/debit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void debit(@RequestBody String amount, @PathVariable Integer id){
        savingsService.debit(id,amount);
    }

}
