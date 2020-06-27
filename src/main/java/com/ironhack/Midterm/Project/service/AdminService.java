package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.Utils.PasswordUtility;
import com.ironhack.Midterm.Project.model.Admin;
import com.ironhack.Midterm.Project.model.Role;
import com.ironhack.Midterm.Project.repository.AdminRepository;
import com.ironhack.Midterm.Project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Admin create(Admin admin){
        admin.setPassword(PasswordUtility.encryptPassword(admin.getPassword()));
        Admin a = adminRepository.save(admin);
        roleRepository.save(new Role("ADMIN", admin));
        return a;
    }
}
