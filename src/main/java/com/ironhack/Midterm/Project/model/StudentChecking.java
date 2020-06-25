package com.ironhack.Midterm.Project.model;

import com.ironhack.Midterm.Project.enums.Status;

import javax.persistence.*;

@Entity
public class StudentChecking extends Checking{


    public StudentChecking(Money balance, Integer secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
    }
    public StudentChecking(Money balance, Integer secretKey, AccountHolders primaryOwner) {
        super(balance, secretKey, primaryOwner, null);
    }
}
