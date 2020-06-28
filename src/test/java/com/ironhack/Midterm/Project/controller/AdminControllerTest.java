package com.ironhack.Midterm.Project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Midterm.Project.model.Admin;
import com.ironhack.Midterm.Project.service.AdminService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AdminControllerTest {

    @MockBean
    private AdminService adminService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private Admin admin;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        admin = new Admin("Aaron", "Aaron", "aaron");
        admin.setId(1);
        when(adminService.create(any())).thenReturn(admin);
    }

    @Test
    public void create() throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            mockMvc.perform(post("/user/admin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(admin)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value("1"));
    }
}