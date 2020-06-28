package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.model.Money;
import com.ironhack.Midterm.Project.model.Savings;
import com.ironhack.Midterm.Project.repository.SavingsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;
    private  final Logger LOGGER = LogManager.getLogger(SavingsService.class);

    public Savings create(Savings s, String interestRate, String minimumBalance){
        LOGGER.info("[INIT] - create a saving account");
        if(interestRate != null)s.setInterestRate(new BigDecimal(interestRate));
        if(minimumBalance != null)s.setMinimumBalance(new BigDecimal(minimumBalance));

        LOGGER.info("[END] - create a saving account");
        return savingsRepository.save(s);
    }


}