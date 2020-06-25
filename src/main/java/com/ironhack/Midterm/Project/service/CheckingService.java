package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.model.*;
import com.ironhack.Midterm.Project.repository.AccountHoldersRepository;
import com.ironhack.Midterm.Project.repository.CheckingRepository;
import com.ironhack.Midterm.Project.repository.StudentCheckingRepository;
import com.ironhack.Midterm.Project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private AccountHolders accountHolder;
    private AccountHolders secondaryOwner;
    private LocalDate today;
    private StudentChecking newStudentChecking;
    private Checking newChecking;
    private Transaction t;

    public Checking create(Integer accountHolderId, CheckingPrimaryOwner checking) throws DataNotFoundException {
        today = LocalDate.now();
        accountHolder = accountHoldersRepository.findById(accountHolderId).orElseThrow(() -> new DataNotFoundException("User not found"));
        if(checking.getSecondaryOwner() != null){
            newChecking.setSecondaryOwner(checking.getSecondaryOwner());
            newStudentChecking.setSecondaryOwner(checking.getSecondaryOwner());
        }
        if(Period.between(accountHolder.getDateOfBirth(), today).getYears() < 24){
            newStudentChecking = new StudentChecking(checking.getBalance(), checking.getSecretKey(), accountHolder);
            newStudentChecking.setMinimumBalance(new BigDecimal("0"));
            newStudentChecking.setMonthlyMaintenanceFee(new BigDecimal("0"));
            return studentCheckingRepository.save(newStudentChecking);
        }
        newChecking = new StudentChecking(checking.getBalance(), checking.getSecretKey(), accountHolder);
        return checkingRepository.save(newChecking);
    }

    public List<Checking> findAll(){
        return checkingRepository.findAll();
    }

    public Checking findById(Integer id){
        return checkingRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Checking not found"));
    }

    public BigDecimal findBalanceById(Integer id){
        return findById(id).getBalance().getAmount();
    }

    public void credit(Integer id,String amount){
        Checking c = findById(id);
        Money m = new Money(new BigDecimal(amount));
        c.credit(m);
        t = new Transaction(c.getPrimaryOwner(), c);
        checkingRepository.save(c);
        transactionRepository.save(t);
    }

    public void debit(Integer id,String amount){
        Checking c = findById(id);
        Money m = new Money(new BigDecimal(amount));
        c.debit(m);
        t = new Transaction(c.getPrimaryOwner(), c);
        checkingRepository.save(c);
        transactionRepository.save(t);
    }

    @Transactional
    public void makeTransference(Transference transference, Integer senderId){
        Checking checkingSender = findById(senderId);
        Checking checkingReceiver = findById(transference.getReceiverId());
        AccountHolders sender = accountHoldersRepository.findByName(transference.getSenderName());
        Checking cPrimary = checkingRepository.findCheckingByName(checkingSender.getPrimaryOwners().getName(), checkingSender.getId());

        if(sender.isLogged() && cPrimary.getId() == sender.getId()){
            checkingSender.debit(transference.getAmount());
            checkingReceiver.credit(transference.getAmount());

            checkingRepository.save(checkingReceiver);
            checkingRepository.save(checkingSender);
            transactionRepository.save(t);
        }

    }
}

