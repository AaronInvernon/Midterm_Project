package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.enums.Status;
import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.exceptions.FrozenAccountException;
import com.ironhack.Midterm.Project.exceptions.NoAccessException;
import com.ironhack.Midterm.Project.exceptions.NotLoggedException;
import com.ironhack.Midterm.Project.model.*;
import com.ironhack.Midterm.Project.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.rmi.AccessException;

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
    private TransactionService transactionService;
    @Autowired
    private SavingsService savingsService;
    private Transaction t;
    private  final Logger LOGGER = LogManager.getLogger(AccountService.class);

    public Checking findById(Integer id, User user){
        LOGGER.info("[INIT] - find aChecking account by id " + id);
        if(!user.isLogged()) throw new NotLoggedException("No user logged id");
        Checking c = checkingRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Checking not found"));
        if(access(user, c))
        if(!user.isAccess()) throw new NoAccessException("The user can't access this account");
        LOGGER.info("[END] - find aChecking account by id");
        return c;
    }

    public BigDecimal findBalanceById(User user, Integer id){

        LOGGER.info("[INIT] - find balance of Checking account by id " + id);
        return findById(id, user).getBalance().getAmount();
    }

    public void saveAccount(Checking account){
        LOGGER.info("[INIT] - save account");
        if(account instanceof CreditCard) creditCardRepository.save((CreditCard)account);
        if(account instanceof Savings) savingsService.create((Savings)account, null, null);
        if(account instanceof StudentChecking) studentCheckingRepository.save((StudentChecking)account);
        else checkingRepository.save(account);
    }

    @Transactional
    public void makeTransference(Transference transference, Integer senderId, User user){
        LOGGER.info("[INIT] - make a transference between two accounts");
        Checking checkingSender = checkingService.findById(senderId, user);
        Checking checkingReceiver = checkingService.findById(transference.getReceiverId(), user);

        if(!user.isLogged()) throw new NotLoggedException("User not logged");
        if(!access(user, checkingSender)) throw new NoAccessException("User can't access");
        if(checkingReceiver.getPrimaryOwner().getName() == transference.getReceiverName()) throw new DataNotFoundException("User not found");

        checkingSender.debit(transference.getAmount());
        checkingReceiver.credit(transference.getAmount());
        t = new Transaction(checkingSender);
        transactionService.isFraud(checkingSender);
        checkingRepository.save(checkingReceiver);
        checkingRepository.save(checkingSender);
        transactionRepository.save(t);

    }


    public void credit(User user, Integer id,String amount){
        LOGGER.info("[INIT] - credit in account " + id);
        Checking c = findById(id, user);
        Money m = new Money(new BigDecimal(amount));
        t = new Transaction(c);
        transactionService.isFraud(c);
        if(c.getStatus() == Status.FROZEN) throw new FrozenAccountException("This account is frozen");
        c.credit(m);
        checkingRepository.save(c);
        transactionRepository.save(t);
        LOGGER.info("[END] - credit in account " + id);
    }

    public void debit(User user, Integer id,String amount){
        LOGGER.info("[INIT] - debit in account " + id);
        Checking c = findById(id, user);
        Money m = new Money(new BigDecimal(amount));
        if(c.getStatus() == Status.FROZEN) throw new FrozenAccountException("This account is frozen");
        c.debit(m);
        t = new Transaction(c);
        transactionService.isFraud(c);
        checkingRepository.save(c);
        transactionRepository.save(t);
        LOGGER.info("[END] - credit in account " + id);
    }

    public boolean access(User user, Checking c){
        return user instanceof Admin || c.getPrimaryOwner().getId() == user.getId() || c.getSecondaryOwner().getId() == user.getId();
    }
}
