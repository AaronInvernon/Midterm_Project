package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.AccountHolders;
import com.ironhack.Midterm.Project.repository.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHoldersService {

    @Autowired
    private AccountHoldersRepository accountRepository;

    public AccountHolders create(AccountHolders a){ return accountRepository.save(a); }

}
