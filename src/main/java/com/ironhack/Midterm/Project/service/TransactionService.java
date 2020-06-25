package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.exceptions.FraudException;
import com.ironhack.Midterm.Project.model.Checking;
import com.ironhack.Midterm.Project.model.User;
import com.ironhack.Midterm.Project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    public void isFraud(Checking account) {
        Integer today = transactionRepository.todayTotal(account.getId(), LocalDate.now().atStartOfDay().toLocalDate());
        Integer max = transactionRepository.maxTotal(account.getId());
        if (today>=max) {
            account.freeze();
            accountService.saveAccount(account);
            throw new FraudException("The account is now frozen because a possible fraud has been detected");
        }
    }
}
