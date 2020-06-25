package com.ironhack.Midterm.Project.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Admin extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Admin(String name, boolean logged, String username, String password) {
        super(name, logged, username, password);
    }
}
