package com.ironhack.Midterm.Project.model;

public abstract class User {
    private String name;
    private boolean logged;

    public void login(){
        this.logged = true;
    }
    public void logout(){
        this.logged = false;
    }

    public User(String name, boolean logged) {
        this.name = name;
        this.logged = logged;
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
}
