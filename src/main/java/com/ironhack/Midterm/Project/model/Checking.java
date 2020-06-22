package com.ironhack.Midterm.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Midterm.Project.enums.Status;
import com.ironhack.Midterm.Project.exceptions.DebitException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Checking {

    @Id
    @GeneratedValue
    private Integer id;
    protected Money balance;
    private Integer secretKey;
    private AccountHolders primaryOwner;
    private AccountHolders secondaryOwner;
    protected Money minimumBalance;
    private final Money penaltyFee = new Money(new BigDecimal("40"));
    private Money monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne()
    private AccountHolders primaryOwners;
    @ManyToOne()
    private AccountHolders secondaryOwners;


    public Checking() {
    }

    public Checking(Money balance, Integer secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = new Money(new BigDecimal("250"));
        this.monthlyMaintenanceFee = new Money(new BigDecimal("12"));
        this.status = Status.ACTIVE;
    }

    public Checking(Money balance, Integer secretKey, AccountHolders primaryOwner) {
        this(balance, secretKey, primaryOwner, null);
    }


    /*Getters & setters*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Integer getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Integer secretKey) {
        this.secretKey = secretKey;
    }

    public AccountHolders getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolders primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolders getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolders secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance){
        this.minimumBalance = minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public AccountHolders getPrimaryOwners() {
        return primaryOwners;
    }

    public void setPrimaryOwners(AccountHolders primaryOwners) {
        this.primaryOwners = primaryOwners;
    }

    public AccountHolders getSecondaryOwners() {
        return secondaryOwners;
    }

    public void setSecondaryOwners(AccountHolders secondaryOwners) {
        this.secondaryOwners = secondaryOwners;
    }

    public void credit(Money m){
        this.balance.increaseAmount(m.getAmount());
    }

    public void debit(Money m){
        BigDecimal balanceSubtracted = this.balance.getAmount().subtract(m.getAmount());
        if (m.getAmount().compareTo(this.balance.getAmount()) == 1 ||
            balanceSubtracted.subtract(this.penaltyFee.getAmount()).compareTo(new BigDecimal("0"))<0) throw new DebitException("The balance isn't enough");
        if(balanceSubtracted.compareTo(this.minimumBalance.getAmount()) == -1){
            this.balance.decreaseAmount(this.penaltyFee);
        }
        this.balance.decreaseAmount(m);
    }
}
