package com.ironhack.Midterm.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String city;
    private Integer postCode;
    private String street;
    private Integer portalNumber;
    private Integer floor;
    private String door;
    @OneToMany(mappedBy="address", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<AccountHolders> accountHolders = new ArrayList<>();
    @OneToMany(mappedBy="mailingAddress", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<AccountHolders> mailingAccountHoldersList = new ArrayList<>();

    public Address() {
    }

    public Address(String country, String city, Integer postCode, String street, Integer portalNumber, Integer floor, String door, List<AccountHolders> accountHolders, List<AccountHolders> mailingAccountHoldersList) {
        this.country = country;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.portalNumber = portalNumber;
        this.floor = floor;
        this.door = door;
        this.accountHolders = accountHolders;
        this.mailingAccountHoldersList = mailingAccountHoldersList;
    }

}
