package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.CreditCard;
import com.ironhack.Midterm.Project.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard create(CreditCard cc, String interestRate, String minimumBalance){
        if(interestRate != null)cc.setInterestRate(new BigDecimal(interestRate));
        if(minimumBalance != null)cc.setMinimumBalance(new BigDecimal(minimumBalance));
        return creditCardRepository.save(cc);
    }
}
