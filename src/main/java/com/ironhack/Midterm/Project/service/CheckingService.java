package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.model.AccountHolders;
import com.ironhack.Midterm.Project.model.Checking;
import com.ironhack.Midterm.Project.model.Money;
import com.ironhack.Midterm.Project.model.StudentChecking;
import com.ironhack.Midterm.Project.repository.AccountHoldersRepository;
import com.ironhack.Midterm.Project.repository.CheckingRepository;
import com.ironhack.Midterm.Project.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    private AccountHolders accountHolder;
    private AccountHolders secondaryOwner;
    private LocalDate today;
    private StudentChecking newStudentChecking;
    private Checking newChecking;

    public Checking create(Integer accountHolderId, CheckingPrimaryOwner checking) throws DataNotFoundException {
        today = LocalDate.now();
        accountHolder = accountHoldersRepository.findById(accountHolderId).orElseThrow(() -> new DataNotFoundException("User not found"));
        if(checking.getSecondaryOwner() != null){
            newChecking.setSecondaryOwner(checking.getSecondaryOwner());
            newStudentChecking.setSecondaryOwner(checking.getSecondaryOwner());
        }
        if(Period.between(accountHolder.getDateOfBirth(), today).getYears() < 24){
            newStudentChecking = new StudentChecking(checking.getBalance(), checking.getSecretKey(), accountHolder, checking.getStatus());
            newStudentChecking.setMinimumBalance(new Money(new BigDecimal("0")));
            newStudentChecking.setMonthlyMaintenanceFee(new Money(new BigDecimal("0")));
            return studentCheckingRepository.save(newStudentChecking);
        }
        newChecking = new StudentChecking(checking.getBalance(), checking.getSecretKey(), accountHolder, checking.getStatus());
        return checkingRepository.save(newChecking);

    }
}

