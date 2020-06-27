package com.ironhack.Midterm.Project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThirdParty extends User{

    private String hashKey;

    public ThirdParty(String name, String username, String password, String hashKey) {
        super(name, username, password);
        this.hashKey = hashKey;
    }

    public ThirdParty() {
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
