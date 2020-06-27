package com.ironhack.Midterm.Project.dto;

import com.ironhack.Midterm.Project.enums.Status;
import com.ironhack.Midterm.Project.model.AccountHolders;
import com.ironhack.Midterm.Project.model.Money;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CheckingPrimaryOwner {

    @Id
    @GeneratedValue
    private Integer id;
    private Money balance;
    private Integer secretKey;
    private AccountHolders primaryOwner;
    private AccountHolders secondaryOwner;
    protected Money minimumBalance;
    private final Money penaltyFee = new Money(new BigDecimal("40"));
    private Money monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate creationDate;

    public CheckingPrimaryOwner(Money balance, Integer secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner) {
            this.balance = balance;
            this.secretKey = secretKey;
            this.primaryOwner = primaryOwner;
            this.secondaryOwner = secondaryOwner;
            this.minimumBalance = new Money(new BigDecimal("250"));
            this.monthlyMaintenanceFee = new Money(new BigDecimal("12"));
            this.status = Status.ACTIVE;
            this.creationDate = LocalDate.now();
    }

    public CheckingPrimaryOwner(Money balance, Integer secretKey, AccountHolders primaryOwner) {
        this(balance, secretKey, primaryOwner, null);
    }

    public CheckingPrimaryOwner() {
    }

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

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
