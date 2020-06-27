package com.ironhack.Midterm.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolders extends User{

    private LocalDate dateOfBirth;
    @ManyToOne(fetch=FetchType.LAZY)
    private Address address;
    @ManyToOne(fetch=FetchType.LAZY)
    private Address mailingAddress;
    @OneToMany(mappedBy="primaryOwners", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Checking> primaryOwners = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy="secondaryOwners", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Checking> secondaryOwners = new ArrayList<>();

    public AccountHolders(String name, String username, String password, LocalDate dateOfBirth, Address address) {
        super(name, username, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public AccountHolders() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
