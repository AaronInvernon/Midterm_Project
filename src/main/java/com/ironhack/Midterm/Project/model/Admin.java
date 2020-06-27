package com.ironhack.Midterm.Project.model;


import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin(String name, String username, String password) {
        super(name, username, password);
    }

    public Admin() {
    }
}
