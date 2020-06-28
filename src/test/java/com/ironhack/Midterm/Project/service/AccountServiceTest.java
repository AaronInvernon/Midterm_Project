package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.enums.Status;
import com.ironhack.Midterm.Project.exceptions.DebitException;
import com.ironhack.Midterm.Project.exceptions.FraudException;
import com.ironhack.Midterm.Project.exceptions.FrozenAccountException;
import com.ironhack.Midterm.Project.model.*;
import com.ironhack.Midterm.Project.repository.AddressRepository;
import com.ironhack.Midterm.Project.repository.CheckingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountHoldersService accountHoldersService;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CheckingService checkingService;
    @Autowired
    private AdminService adminService;
    private AccountHolders a;
    private AccountHolders a2;
    private Admin admin;
    private Admin admin2;
    private Checking c;
    private Checking c2;
    private Transference transference;
    private AccountHolders accH;
    private AccountHolders accH2;

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
        admin = new Admin("Aaron", "Aaron", "aaron");
        adminService.create(admin);
        Money amount  = new Money(new BigDecimal("2000"));
        c = new Checking(amount,123456, a);
        checkingRepository.save(c);

        accH2 = new AccountHolders();
        LocalDate dateOfBirth2 = LocalDate.of(1995, 04, 21);
        accH2.setName("Raul");
        accH2.setUsername("Raul");
        accH2.setPassword("raul");
        accH2.setDateOfBirth(dateOfBirth2);
        accH2.setAddress(address);
        a2 = accountHoldersService.create(accH2);
        admin2 = new Admin("Raul", "Raul", "raul");
        Money amount2  = new Money(new BigDecimal("50"));
        adminService.create(admin2);
        c2= new Checking(amount2,9876541, a2);
        checkingRepository.save(c2);
        admin.login();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveAccount() {
        accountService.saveAccount(c);
        assertEquals("Aaron",c.getPrimaryOwner().getName());
    }

    @Test
    void makeTransference() {
        Money tMoney  = new Money(new BigDecimal("10"));
        a.login();
        transference = new Transference(a.getName(), c.getId(), tMoney);
        System.out.println("RESULT => "+ c.getPrimaryOwner().getName()
                            +"\n"+transference
                            +"\n sender =>"+c2.getId()
                            +"\n =>user "+a);
        accountService.makeTransference(transference, c2.getId(), a);
        assertEquals("Aaron", c.getPrimaryOwner().getName());
        assertEquals(c.getBalance().getAmount().add(new BigDecimal("10")), accountService.findById(c.getId(), a).getBalance().getAmount());
    }

    @Test
    void credit() {
        accountService.credit(admin, c.getId(), "100");
        assertEquals(c.getBalance().getAmount().add(new BigDecimal("100")), accountService.findById(c.getId(), admin).getBalance().getAmount());
    }

    @Test
    void debit() {
        accountService.debit(admin, c.getId(), "100");
        assertEquals(c.getBalance().getAmount().subtract(new BigDecimal("100")), accountService.findById(c.getId(), admin).getBalance().getAmount());
    }

    @Test
    void debit_DebitException() {
        assertThrows(DebitException.class, ()-> accountService.debit(admin, c2.getId(), "100"));
    }

    @Test
    void credit_FrozenAccountException() {
        c2.freeze();
        System.out.println("STATUS-> "+ c2.getStatus());
        assertThrows(FrozenAccountException.class, ()-> accountService.credit(admin, c.getId(), "100"));
    }

//    @Test
//    void isFraud() {
//        for(int  i=0; i<4;i++){
//            accountService.credit(admin, c.getId(), "100");
//        }
//        assertThrows(FraudException.class, ()-> accountService.credit(admin, c2.getId(), "100"));
//    }
}