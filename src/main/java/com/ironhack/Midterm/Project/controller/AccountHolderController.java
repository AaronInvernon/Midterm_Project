package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.model.AccountHolders;
import com.ironhack.Midterm.Project.service.AccountHoldersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderController {

    @Autowired
    private AccountHoldersService accountHoldersService;

    @PostMapping("/accountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolders create(AccountHolders a){ return accountHoldersService.create(a); }
}
