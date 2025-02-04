package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.exceptions.InsterestRateException;
import com.ironhack.Midterm.Project.exceptions.MinimumBalanceException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Savings extends Checking{

    protected BigDecimal interestRate;
    protected LocalDate createDate;


    public Savings(Money balance, Integer secretKey, AccountHolders primaryOwner){
        super(balance, secretKey, primaryOwner);
        this.interestRate = new BigDecimal("0.0025");
        super.setMonthlyMaintenanceFee(new BigDecimal("0"));
        this.setMinimumBalance(minimumBalance);
        this.createDate = LocalDate.now();
    }

    public Savings() {
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) throws InsterestRateException{
        BigDecimal max = new BigDecimal("0.5");
        Integer valMax = max.compareTo(interestRate);
        BigDecimal min = new BigDecimal("0");
        Integer valMin = min.compareTo(interestRate);
        if (valMax == -1 || valMin == 1) throw new InsterestRateException("The interest rate must be between 0 and 0.5");
        this.interestRate = interestRate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public void setMinimumBalance(BigDecimal minimumBalance) throws MinimumBalanceException {
        BigDecimal max = new BigDecimal("1000");
        BigDecimal min = new BigDecimal("100");
        Money mBalance = new Money(minimumBalance);
        Integer valMax = max.compareTo(mBalance.getAmount());
        Integer valMin = min.compareTo(mBalance.getAmount());
        if (valMax == -1 || valMin == 1) throw new MinimumBalanceException("The minimum balance must be between 100 and 1000");
        this.minimumBalance = minimumBalance;
    }

    @Override
    public Money getBalance() {
        LocalDate today = LocalDate.now();

        for(Integer years = Period.between(this.createDate, today).getYears(); years > 0; years--){
            credit(new Money(this.getBalance().getAmount().multiply(interestRate)));
            setCreateDate(today);
        }
        return this.balance;
    }


}
