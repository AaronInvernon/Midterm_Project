package com.ironhack.Midterm.Project.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne()
    private Checking account;
    private LocalDate dateAt;

    public Transaction(Checking account) {
        this.account = account;
        this.dateAt = LocalDate.now();
    }

    public Transaction() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Checking getAccount() {
        return account;
    }

    public void setAccount(Checking account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return dateAt;
    }

    public void setDate(LocalDate dateAt) {
        this.dateAt = dateAt;
    }
}
