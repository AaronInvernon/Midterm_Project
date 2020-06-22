package com.ironhack.Midterm.Project.exceptions;

public class CreditCardLimitException extends RuntimeException{
    public CreditCardLimitException(String message) {
        super (message);
    }
}
