package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.AccountHolders;
import com.ironhack.Midterm.Project.model.Address;
import com.ironhack.Midterm.Project.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountHoldersServiceTest {

    @Autowired
    private AccountHoldersService accountHoldersService;

    @Test
    void create() {
        AccountHolders accH = new AccountHolders();
        LocalDate dateOfBirth = LocalDate.of(1996, 07, 17);
        Address address = new Address();

        accH.setName("Aaron");
        accH.setUsername("Aaron");
        accH.setPassword("aaron");
        accH.setDateOfBirth(dateOfBirth);
        accH.setAddress(address);
        AccountHolders a = accountHoldersService.create(accH);


        assertEquals(1, a.getId());
        assertEquals("Aaron", a.getName());
    }
}