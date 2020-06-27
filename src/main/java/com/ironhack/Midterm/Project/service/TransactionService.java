package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.exceptions.FraudException;
import com.ironhack.Midterm.Project.model.Checking;
import com.ironhack.Midterm.Project.model.Transaction;
import com.ironhack.Midterm.Project.model.User;
import com.ironhack.Midterm.Project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    public void isFraud(Checking account) {
        Integer today = transactionRepository.todayTotal(account.getId(), LocalDate.now().atStartOfDay().toLocalDate());
        Integer defaultMax = transactionRepository.maxTotal(account.getId(), LocalDate.now().atStartOfDay().toLocalDate());
        double max = defaultMax==null ? 2 : (Math.max(defaultMax, 2)*1.5);
        if (today > max) {
            account.freeze();
            accountService.saveAccount(account);
            throw new FraudException("The account is now frozen because a possible fraud has been detected");
        }
    }

    public boolean fraudTransactionBySeconds(Checking account, LocalDateTime now){
        /*List<Transaction> transactionList = transactionRepository.findLastTransaction(account.getId());
        if(transactionList.isEmpty()) return false;
        Duration duration = Duration.between(transactionList.get(0).getDate(), now);
        return duration.toSeconds() <=1;*/
    }
}
