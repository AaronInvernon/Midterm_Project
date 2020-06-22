package com.ironhack.Midterm.Project.globalHandler;

import com.ironhack.Midterm.Project.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler{

    @ExceptionHandler(CreditCardLimitException.class)
    public void handleDataNotFoundException(CreditCardLimitException e, HttpServletResponse response)  throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(InsterestRateException.class)
    public void handleInsterestRateException(InsterestRateException e, HttpServletResponse response)  throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MinimumBalanceException.class)
    public void handleMinimumBalanceException(MinimumBalanceException e, HttpServletResponse response)  throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public void handleDataNotFoundException(DataNotFoundException e, HttpServletResponse response)  throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(DebitException.class)
    public void handleDebitException(DebitException e, HttpServletResponse response)  throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }
}