package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.CreditCard;
import com.ironhack.Midterm.Project.repository.CreditCardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;
    private  final Logger LOGGER = LogManager.getLogger(CreditCardService.class);

    public CreditCard create(CreditCard cc, String interestRate, String minimumBalance){
        LOGGER.info("[INIT] - create a credit card account");
        if(interestRate != null)cc.setInterestRate(new BigDecimal(interestRate));
        if(minimumBalance != null)cc.setMinimumBalance(new BigDecimal(minimumBalance));
        LOGGER.info("[END] - create a credit card account");
        return creditCardRepository.save(cc);
    }
}
