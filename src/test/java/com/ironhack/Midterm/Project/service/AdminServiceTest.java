package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        Admin admin = new Admin();
        admin.setName("Aaron");
        admin.setUsername("Aaron");
        admin.setPassword("aaron");
        Admin a = adminService.create(admin);

        assertEquals("Aaron", a.getName());
    }
}