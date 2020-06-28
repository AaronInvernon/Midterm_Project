package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.Admin;
import com.ironhack.Midterm.Project.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    private Admin a;


    @BeforeEach
    void setUp() {
        a = new Admin("Aaron", "Aaron", "aaron");
        adminService.create(a);
    }

    @Test
    void findById() {
        assertEquals(1, userDetailsService.findById(1).getId());
        assertEquals("Aaron", userDetailsService.findById(1).getName());
    }

    @Test
    void login() {
        userDetailsService.login(1);
        assertTrue(userDetailsService.findById(1).isLogged());
    }

    @Test
    void logout() {
        userDetailsService.logout(1);
        assertFalse(userDetailsService.findById(1).isLogged());
    }

    @Test
    void username_Null(){
        assertThrows(UsernameNotFoundException.class, ()->userDetailsService.loadUserByUsername(null));
    }
}