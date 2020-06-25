package com.ironhack.Midterm.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolders extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    public AccountHolders(String name, boolean logged, String username, String password, LocalDate dateOfBirth, Address address) {
        super(name, logged, username, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
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

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public List<Checking> getPrimaryOwners() {
        return primaryOwners;
    }

    public void setPrimaryOwners(List<Checking> primaryOwners) {
        this.primaryOwners = primaryOwners;
    }

    public List<Checking> getSecondaryOwners() {
        return secondaryOwners;
    }

    public void setSecondaryOwners(List<Checking> secondaryOwners) {
        this.secondaryOwners = secondaryOwners;
    }
}
