package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.Utils.PasswordUtility;
import com.ironhack.Midterm.Project.model.Admin;
import com.ironhack.Midterm.Project.model.Role;
import com.ironhack.Midterm.Project.repository.AdminRepository;
import com.ironhack.Midterm.Project.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdminRepository adminRepository;
    private  final Logger LOGGER = LogManager.getLogger(AdminService.class);

    public Admin create(Admin admin){
        LOGGER.info("[INIT] - create admin");
        admin.setPassword(PasswordUtility.encryptPassword(admin.getPassword()));
        Admin a = adminRepository.save(admin);
        roleRepository.save(new Role("ROLE_ADMIN", admin));
        LOGGER.info("[END] - third Party created with id" + a.getId());
        return a;
    }
}
