package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.model.Money;
import com.ironhack.Midterm.Project.model.Savings;
import com.ironhack.Midterm.Project.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;

    public Savings findById(Integer id){
        return savingsRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Saving account not found"));
    }

    public Savings create(Savings s, String interestRate, String minimumBalance){
        if(interestRate != null)s.setInterestRate(new BigDecimal(interestRate));
        if(minimumBalance != null)s.setMinimumBalance(new BigDecimal(minimumBalance));
        return savingsRepository.save(s);
    }

    public void credit(Integer id,String amount){
        Savings s = findById(id);
        Money m = new Money(new BigDecimal(amount));
        s.credit(m);
        savingsRepository.save(s);
    }

    public void debit(Integer id,String amount){
        Savings s = findById(id);
        Money m = new Money(new BigDecimal(amount));
        s.debit(m);
        savingsRepository.save(s);
    }
}
