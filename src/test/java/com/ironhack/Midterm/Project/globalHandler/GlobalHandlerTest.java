package com.ironhack.Midterm.Project.globalHandler;

import com.ironhack.Midterm.Project.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GlobalHandlerTest {
    private GlobalHandler globalHandler = new GlobalHandler();
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp(){
        response = new MockHttpServletResponse();
    }

    @Test
    void handleDataNotFoundException() throws IOException {
        globalHandler.handleDataNotFoundException(new DataNotFoundException("DataNotFound"), response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void handleInsterestRateException() throws IOException {
        globalHandler.handleInsterestRateException(new InsterestRateException("Interest Rate"), response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void handleMinimumBalanceException() throws IOException {
        globalHandler.handleMinimumBalanceException(new MinimumBalanceException("Minimum balance"), response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void handleDebitException() throws IOException {
        globalHandler.handleDebitException(new DebitException("Debit"), response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void notLoggedException() throws IOException {
        globalHandler.NotLoggedException(new NotLoggedException("Not logged"), response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void noAccessException() throws IOException {
        globalHandler.NoAccessException(new NoAccessException("No access"), response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void handleFraudException() throws IOException {
        globalHandler.handleFraudException(new FraudException("Fraud"), response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void handleFrozenAccountException() throws IOException {
        globalHandler.handleFrozenAccountException(new FrozenAccountException("Frozen"), response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}