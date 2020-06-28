package com.ironhack.Midterm.Project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.model.*;
import com.ironhack.Midterm.Project.service.AccountHoldersService;
import com.ironhack.Midterm.Project.service.CheckingService;
import com.ironhack.Midterm.Project.service.SavingsService;
import com.ironhack.Midterm.Project.service.UserDetailsServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @MockBean
    private AccountHoldersService acchService;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private AccountHolders accountHolders;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        LocalDate dBirth = LocalDate.now();
        Address address = new Address();
        accountHolders = new AccountHolders("Aaron", "Aaron", "aaron", dBirth, address);
        accountHolders.setId(1);
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(post("/user/1/login"))
                .andExpect(status().isNoContent());
    }

    @Test
    void logout() throws Exception {
        mockMvc.perform(post("/user/1/logout"))
                .andExpect(status().isNoContent());
    }

}