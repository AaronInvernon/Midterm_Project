package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.exceptions.NotLoggedException;
import com.ironhack.Midterm.Project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckingServiceTest {

    @Autowired
    private CheckingService checkingService;
    @Autowired
    private AccountHoldersService accountHoldersService;
    @Autowired
    private AdminService adminService;
    private AccountHolders a;
    private CheckingPrimaryOwner checkingPrimaryOwner;
    private Admin admin;
    @BeforeEach
    void setUp(){
        AccountHolders accH = new AccountHolders();
        LocalDate dateOfBirth = LocalDate.of(1996, 07, 17);
        Address address = new Address();

        accH.setName("Aaron");
        accH.setUsername("Aaron");
        accH.setPassword("aaron");
        accH.setDateOfBirth(dateOfBirth);
        accH.setAddress(address);
        a = accountHoldersService.create(accH);

        admin = new Admin("Aaron", "Aaron", "aaron");
        adminService.create(admin);

    }

   @Test
    void create() {
        Money amount  = new Money(new BigDecimal("2000"));
        checkingPrimaryOwner = new CheckingPrimaryOwner(amount,123456, a.getId());
        Checking c = checkingService.create(a.getId(), checkingPrimaryOwner);
        assertEquals("Aaron", c.getPrimaryOwner().getName());
    }

    @Test
    void findById() {
        Money amount  = new Money(new BigDecimal("2000"));
        checkingPrimaryOwner = new CheckingPrimaryOwner(amount,123456, a.getId());
        Checking c = checkingService.create(a.getId(), checkingPrimaryOwner);
        admin.login();
        assertDoesNotThrow(() ->checkingService.findById(1,admin));
    }

    @Test
    void findById_throwNotLoggedException() {
       assertThrows(NotLoggedException.class, () ->checkingService.findById(50,admin));
    }

    @Test
    void findById_throwDataNotFoundException() {
        admin.login();
        assertThrows(DataNotFoundException.class, () ->checkingService.findById(50,admin));
    }

}