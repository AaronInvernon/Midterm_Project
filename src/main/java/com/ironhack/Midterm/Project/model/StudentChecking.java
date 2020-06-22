package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.enums.Status;

import javax.persistence.*;

@Entity
public class StudentChecking extends Checking{

    @Id
    @GeneratedValue
    private Integer id;

    public StudentChecking(Money balance, Integer secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
    }
    public StudentChecking(Money balance, Integer secretKey, AccountHolders primaryOwner) {
        super(balance, secretKey, primaryOwner, null);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
