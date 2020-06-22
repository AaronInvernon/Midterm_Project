package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.enums.Status;
import com.ironhack.Midterm.Project.exceptions.InsterestRateException;
import com.ironhack.Midterm.Project.exceptions.MinimumBalanceException;
import jdk.tools.jaotc.LoadedClass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Savings extends Checking{

    @Id
    @GeneratedValue
    private Integer id;
    protected BigDecimal interestRate;
    protected LocalDate createDate;


    public Savings(Money balance, Integer secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner){
        super(balance, secretKey, primaryOwner, secondaryOwner);
        this.interestRate = new BigDecimal("0.0025");
        super.setMonthlyMaintenanceFee(new Money(new BigDecimal("0")));
        this.setMinimumBalance(minimumBalance);
        this.createDate = LocalDate.now();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) throws InsterestRateException{
        BigDecimal max = new BigDecimal("0.5");
        Integer valMax = max.compareTo(interestRate);
        BigDecimal min = new BigDecimal("0");
        Integer valMin = min.compareTo(interestRate);
        if (valMax == 1 || valMin == -1) throw new InsterestRateException("The interest rate must be between 0 and 0.5");
        this.interestRate = interestRate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public void setMinimumBalance(Money minimumBalance) throws MinimumBalanceException {
        BigDecimal max = new BigDecimal("1000");
        BigDecimal min = new BigDecimal("100");
        Integer valMax = max.compareTo(minimumBalance.getAmount());
        Integer valMin = min.compareTo(minimumBalance.getAmount());
        if (valMax == 1 || valMin == -1) throw new MinimumBalanceException("The minimum balance must be between 100 and 1000");
        this.minimumBalance = minimumBalance;
    }

    @Override
    public Money getBalance() {
        LocalDate today = LocalDate.now();

        for(Integer years = Period.between(this.createDate, today).getYears(); years > 0; years--){
            credit(new Money(this.getBalance().getAmount().multiply(interestRate)));
        }
        return this.balance;
    }


}
