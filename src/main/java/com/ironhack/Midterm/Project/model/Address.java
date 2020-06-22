package com.ironhack.Midterm.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {
    private String country;
    private String city;
    private Integer postCode;
    private String street;
    private Short portalNumber;
    private Short floor;
    private String door;
    @JsonIgnore
    @OneToMany(mappedBy="address", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<AccountHolders> accountHolders = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy="mailingAddress", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<AccountHolders> mailingAccountHoldersList = new ArrayList<>();

    public Address() {
    }

    public Address(String country, String city, Integer postCode, String street, Short portalNumber, Short floor, String door, List<AccountHolders> accountHolders, List<AccountHolders> mailingAccountHoldersList) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Short getPortalNumber() {
        return portalNumber;
    }

    public void setPortalNumber(Short portalNumber) {
        this.portalNumber = portalNumber;
    }

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public List<AccountHolders> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(List<AccountHolders> accountHolders) {
        this.accountHolders = accountHolders;
    }

    public List<AccountHolders> getMailingAccountHoldersList() {
        return mailingAccountHoldersList;
    }

    public void setMailingAccountHoldersList(List<AccountHolders> mailingAccountHoldersList) {
        this.mailingAccountHoldersList = mailingAccountHoldersList;
    }
}
