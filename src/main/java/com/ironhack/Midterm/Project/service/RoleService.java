package com.ironhack.Midterm.Project.service;


import com.ironhack.Midterm.Project.model.Role;
import com.ironhack.Midterm.Project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;
}