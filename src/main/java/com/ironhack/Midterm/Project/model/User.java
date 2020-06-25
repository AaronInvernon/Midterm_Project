package com.ironhack.Midterm.Project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public abstract class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private boolean logged;
    private String username;
    private String password;
    @OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, mappedBy="user")
    private Set<Role> roles = new HashSet<>();

    public void login(){
        this.logged = true;
    }
    public void logout(){
        this.logged = false;
    }

    public User(String name, boolean logged, String username, String password) {
        this.name = name;
        this.logged = logged;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + roles +
                '}';
    }
}
