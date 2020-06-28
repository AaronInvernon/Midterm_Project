package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.exceptions.CreditCardLimitException;
import com.ironhack.Midterm.Project.exceptions.InsterestRateException;
import com.ironhack.Midterm.Project.repository.AddressRepository;
import com.ironhack.Midterm.Project.repository.CreditCardRepository;
import com.ironhack.Midterm.Project.service.AccountHoldersService;
import com.ironhack.Midterm.Project.service.AccountService;
import com.ironhack.Midterm.Project.service.CheckingService;
import com.ironhack.Midterm.Project.service.SavingsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditCardTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AccountHoldersService accountHoldersService;
    @Autowired
    private CheckingService checkingService;
    @Autowired
    private CreditCardRepository creditCardRepository;
    private AccountHolders accH;
    private AccountHolders a;
    private Savings s;

    CreditCard creditCard;

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
        creditCard = new CreditCard(new Money(new BigDecimal("100")), 123456, a);
    }


    @Test
    void setCreditLimit_CreditCardLimitException() {
        assertThrows(CreditCardLimitException.class, ()-> creditCard.setCreditLimit(new Money(new BigDecimal("10"))));
    }

    @Test
    void setInterestRate_InsterestRateException() {
        assertThrows(InsterestRateException.class, ()-> creditCard.setInterestRate(new BigDecimal("1")));
    }
}