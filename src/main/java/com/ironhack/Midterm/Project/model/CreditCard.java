package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.exceptions.CreditCardLimitException;
import com.ironhack.Midterm.Project.exceptions.InsterestRateException;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Savings{

    private Money creditLimit;

    public CreditCard(Money balance, Integer secretKey, AccountHolders primaryOwner){
        super(balance, secretKey, primaryOwner);
        this.creditLimit = new Money(new BigDecimal("100"));
        this.setInterestRate(new BigDecimal("0.2"));
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) throws CreditCardLimitException {
        if(creditLimit == null) this.creditLimit = new Money(new BigDecimal("100"));
        BigDecimal max = new BigDecimal("100000");
        Integer valMax = max.compareTo(creditLimit.getAmount());
        BigDecimal min = new BigDecimal("100");
        Integer valMin = min.compareTo(creditLimit.getAmount());
        if (valMax == -1 || valMin == 1) throw new CreditCardLimitException("The limit credit card must be between 100 and 100000");
        this.creditLimit = creditLimit;
    }


    @Override
    public void setInterestRate(BigDecimal interestRate) throws InsterestRateException{
        BigDecimal max = new BigDecimal("0.2");
        Integer valMax = max.compareTo(interestRate);
        BigDecimal min = new BigDecimal("0.1");
        Integer valMin = min.compareTo(interestRate);
        if (valMax == -1 || valMin == 1) throw new InsterestRateException("The interest rate must be between 0.1 and 0.2");
        this.interestRate = interestRate;
    }

    @Override
    public Money getBalance() {
        LocalDate today = LocalDate.now();

        for(Integer months = Period.between(this.createDate, today).getMonths(); months > 0; months--){
            this.setBalance(new Money(this.getBalance().increaseAmount(this.getBalance().getAmount().multiply(interestRate))));
            setCreateDate(today);
        }
        return this.balance;
    }
}

