package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.AccountHolders;
import com.ironhack.Midterm.Project.model.Role;
import com.ironhack.Midterm.Project.repository.AccountHoldersRepository;
import com.ironhack.Midterm.Project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHoldersService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountHoldersRepository accountRepository;

    public AccountHolders create(AccountHolders a){
        roleRepository.save(new Role("ACCOUNT_HOLDER", a));
        return accountRepository.save(a);
    }

}
