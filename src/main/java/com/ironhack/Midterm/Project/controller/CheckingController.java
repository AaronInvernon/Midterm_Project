package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.model.Checking;
import com.ironhack.Midterm.Project.model.Money;
import com.ironhack.Midterm.Project.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class CheckingController {

    @Autowired
    private CheckingService checkingService;

    @PostMapping("/account/checking/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking create(@RequestBody CheckingPrimaryOwner checkingPrimaryOwner, @PathVariable Integer accountHolderId){
        return checkingService.create(accountHolderId, checkingPrimaryOwner);
    }

    @GetMapping("/account/checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> findAll(){
        return checkingService.findAll();
    }

    @GetMapping("/account/checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking findById(@PathVariable Integer id){
        return checkingService.findById(id);
    }

    @PostMapping("/account/checking/{id}/credit")
    @ResponseStatus(HttpStatus.OK)
    public void credit(@RequestBody String amount, @PathVariable Integer id){
        checkingService.credit(id, amount);
    }

    @PostMapping("/account/checking/{id}/debit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void debit(@RequestBody String amount, @PathVariable Integer id){
        checkingService.debit(id,amount);
    }

    @PostMapping("/account/{id}/(transference")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeTransference(@RequestBody Transference transference,@PathVariable Integer senderId){
        checkingService.makeTransference(transference, senderId);
    }
}
