package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.model.*;
import com.ironhack.Midterm.Project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CheckingService checkingService;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    private Transaction t;

    public void saveAccount(Checking account){
        if(account instanceof CreditCard) creditCardRepository.save((CreditCard)account);
        if(account instanceof Savings) savingsRepository.save((Savings)account);
        if(account instanceof StudentChecking) studentCheckingRepository.save((StudentChecking)account);
        else checkingRepository.save(account);
    }

    @Transactional
    public void makeTransference(Transference transference, Integer senderId, User user){
        Checking checkingSender = checkingService.findById(senderId, user);
        Checking checkingReceiver = checkingService.findById(transference.getReceiverId(), user);
        AccountHolders sender = accountHoldersRepository.findByName(transference.getSenderName());
        Checking cPrimary = checkingRepository.findCheckingByName(checkingSender.getPrimaryOwners().getName(), checkingSender.getId());

        if(sender.isLogged() && cPrimary.getId() == sender.getId()){
            checkingSender.debit(transference.getAmount());
            checkingReceiver.credit(transference.getAmount());
            t = new Transaction(checkingSender);

            checkingRepository.save(checkingReceiver);
            checkingRepository.save(checkingSender);
            transactionRepository.save(t);
        }
    }


    public void credit(User user, Integer id,String amount){
        Checking c = checkingService.findById(id, user);
        Money m = new Money(new BigDecimal(amount));
        c.credit(m);
        t = new Transaction(c);
        checkingRepository.save(c);
        transactionRepository.save(t);
    }

    public void debit(User user, Integer id,String amount){
        Checking c = checkingService.findById(id, user);
        Money m = new Money(new BigDecimal(amount));
        c.debit(m);
        t = new Transaction(c);
        checkingRepository.save(c);
        transactionRepository.save(t);
    }
}
