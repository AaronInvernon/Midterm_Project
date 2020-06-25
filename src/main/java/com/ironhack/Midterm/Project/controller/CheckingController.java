package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.model.Checking;
import com.ironhack.Midterm.Project.model.Money;
import com.ironhack.Midterm.Project.model.User;
import com.ironhack.Midterm.Project.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
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

    @GetMapping("/account/checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking findById(@AuthenticationPrincipal User user, @PathVariable Integer id){
        return checkingService.findById(id,user);
    }

    @GetMapping("/account/checking/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal findBalanceById(@AuthenticationPrincipal User user, @PathVariable Integer id) { return checkingService.findBalanceById(user, id); }

}
