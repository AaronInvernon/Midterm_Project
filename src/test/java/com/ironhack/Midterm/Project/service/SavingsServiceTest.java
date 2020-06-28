package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.exceptions.InsterestRateException;
import com.ironhack.Midterm.Project.exceptions.MinimumBalanceException;
import com.ironhack.Midterm.Project.model.*;
import com.ironhack.Midterm.Project.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SavingsServiceTest {

    @Autowired
    private SavingsService savingsService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AccountHoldersService accountHoldersService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CheckingService checkingService;
    private AccountHolders accH;
    private AccountHolders a;
    private Savings s;

    @BeforeEach
    void setUp() {
        accH = new AccountHolders();
        LocalDate dateOfBirth = LocalDate.of(1996, 07, 17);
        Address address = new Address();
        addressRepository.save(address);
        accH.setName("Aaron");
        accH.setUsername("Aaron");
        accH.setPassword("aaron");
        accH.setDateOfBirth(dateOfBirth);
        accH.setAddress(address);
        a = accountHoldersService.create(accH);
        s = new Savings(new Money(new BigDecimal("200")), 123456, a);
        savingsService.create(s, null, null);
        /** private Integer id;
         private Integer postCode;
         private String street;
         private Short portalNumber;
         private Short floor;
         private String door;*/
    }


    @Test
    void create_MinimumBalanceException() {
        a.login();
        assertThrows(MinimumBalanceException.class, () ->s.setMinimumBalance(new BigDecimal("10")));
    }

    @Test
    void create_InsterestRateException() {
        a.login();
        assertThrows(InsterestRateException.class, () ->s.setInterestRate(new BigDecimal("1")));
    }
}