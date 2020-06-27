package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.AccountHolders;
import com.ironhack.Midterm.Project.model.Address;
import com.ironhack.Midterm.Project.model.ThirdParty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ThirdPartyServiceTest {

    @Autowired
    private ThirdPartyService thirdPartyService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
        ThirdParty thirdParty = new ThirdParty("Aaron", "Aaron", "aaron", "123654");

        ThirdParty tp = thirdPartyService.create(thirdParty);
        assertEquals("Aaron", tp.getName());
    }
}