package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.Utils.PasswordUtility;
import com.ironhack.Midterm.Project.model.AccountHolders;
import com.ironhack.Midterm.Project.model.Role;
import com.ironhack.Midterm.Project.repository.AccountHoldersRepository;
import com.ironhack.Midterm.Project.repository.AddressRepository;
import com.ironhack.Midterm.Project.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHoldersService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountHoldersRepository accountRepository;
    @Autowired
    private AddressRepository addressRepository;
    private  final Logger LOGGER = LogManager.getLogger(AccountHoldersService.class);

    public AccountHolders create(AccountHolders a){
        LOGGER.info("[INIT] - create account Holders");
        a.setPassword(PasswordUtility.encryptPassword(a.getPassword()));
        addressRepository.save(a.getAddress());
        AccountHolders account = accountRepository.save(a);
        roleRepository.save(new Role("ROLE_ACCOUNT_HOLDER", a));
        LOGGER.info("[END] - account holder created with id" + a.getId());
        return account;
    }

}
