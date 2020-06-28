package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.exceptions.FraudException;
import com.ironhack.Midterm.Project.model.Checking;
import com.ironhack.Midterm.Project.model.Transaction;
import com.ironhack.Midterm.Project.model.User;
import com.ironhack.Midterm.Project.repository.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private  final Logger LOGGER = LogManager.getLogger(TransactionService.class);

    public void isFraud(Checking account) {
        LOGGER.info("[INIT] - is fraud");
        Transaction lastTransaction = transactionRepository.findLastTransaction(account.getId());
        Long time = lastTransaction==null ? 2 : Duration.between(lastTransaction.getDate(), LocalDateTime.now()).toSeconds();
        if(time<=1){
            LOGGER.info("Fraud by time");
            throw new FraudException("The account is now frozen because a possible fraud has been detected");
        }
        Integer today = transactionRepository.todayTotal(account.getId(), LocalDate.now().atStartOfDay().toLocalDate());
        Integer defaultMax = transactionRepository.maxTotal(account.getId(), LocalDate.now().atStartOfDay().toLocalDate());
        double max = defaultMax==null ? 3 : (Math.max(defaultMax, 2)*1.5);
        if (today > max) {
            account.freeze();
            accountService.saveAccount(account);
            LOGGER.info("Fraud by  number of operations");
            throw new FraudException("The account is now frozen because a possible fraud has been detected");
        }
    }

}
