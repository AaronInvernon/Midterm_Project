package com.ironhack.Midterm.Project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.Money;
import com.ironhack.Midterm.Project.model.Savings;
import com.ironhack.Midterm.Project.model.ThirdParty;
import com.ironhack.Midterm.Project.service.SavingsService;
import com.ironhack.Midterm.Project.service.ThirdPartyService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ThirdPartyControllerTest {

    @MockBean
    private ThirdPartyService thirdPartyService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ThirdParty thirdParty;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        thirdParty = new ThirdParty("aaron", "aaron", "aaron", "aaron");
        thirdParty.setId(1);
        when(thirdPartyService.create(any())).thenReturn(thirdParty);
    }
    @Test
    @WithMockUser(username = "user", password = "user")
    public void create() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/user/thirdParty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(thirdParty)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }
}