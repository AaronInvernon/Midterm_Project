package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.model.Money;
import com.ironhack.Midterm.Project.model.Savings;
import com.ironhack.Midterm.Project.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;

    public Savings create(Savings s, String interestRate, String minimumBalance){
        if(interestRate != null)s.setInterestRate(new BigDecimal(interestRate));
        if(minimumBalance != null)s.setMinimumBalance(new BigDecimal(minimumBalance));
        return savingsRepository.save(s);
    }


}