package com.ironhack.Midterm.Project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.model.*;
import com.ironhack.Midterm.Project.service.AccountHoldersService;
import com.ironhack.Midterm.Project.service.AccountService;
import com.ironhack.Midterm.Project.service.AdminService;
import com.ironhack.Midterm.Project.service.SavingsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class AccountControllerTest {


    @MockBean
    private AccountService accountService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private Savings savings;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        savings = new Savings(new Money(new BigDecimal(100)), 1234, null);
        savings.setId(1);
        when(accountService.findById(any(), any())).thenReturn(savings);
        when(accountService.findBalanceById(any(), any())).thenReturn(savings.getBalance().getAmount());
    }


    @Test
    @WithMockUser(username = "user", password = "user")
    void findById() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(get("/account/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savings)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    void findBalanceById() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(get("/account/1/balance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savings)))
                .andExpect(status().isOk());
    }

/*    @Test
    @WithMockUser(username = "user", password = "user")
    void credit() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/account/checking/1/credit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(balance)))
                .andExpect(status().isOk());
    }

    @Test
    void debit() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/account/checking/1/debit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(balance)))
                .andExpect(status().isOk());
    }*/

    @Test
    void makeTransference() {
    }
}