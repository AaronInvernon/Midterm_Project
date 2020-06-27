package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.model.User;
import com.ironhack.Midterm.Project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/account/{id}/credit")
    @ResponseStatus(HttpStatus.OK)
    public void credit(@AuthenticationPrincipal User user, @RequestBody String amount, @PathVariable Integer id){
        accountService.credit(user, id, amount);
    }

    @PostMapping("/account/{id}/debit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void debit(@AuthenticationPrincipal User user, @RequestBody String amount, @PathVariable Integer id){
        accountService.debit(user, id,amount);
    }

    @PostMapping("/account/{id}/transference")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeTransference(@AuthenticationPrincipal User user, @RequestBody Transference transference, @PathVariable Integer senderId){
        accountService.makeTransference(transference, senderId, user);
    }


}
