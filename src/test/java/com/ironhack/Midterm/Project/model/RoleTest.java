package com.ironhack.Midterm.Project.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private static Role r = new Role();

    @Test
    void setId() {
        r.setId(11);
        assertEquals(11, r.getId());
    }
    @Test
    void setRole() {
        r.setRole("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", r.getRole());
    }


    @Test
    void testToString_works() {
        r.setRole("intern");
        assertTrue(r.toString().contains("intern"));
    }
}