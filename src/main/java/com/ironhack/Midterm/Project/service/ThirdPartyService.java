package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.Utils.PasswordUtility;
import com.ironhack.Midterm.Project.model.Role;
import com.ironhack.Midterm.Project.model.ThirdParty;
import com.ironhack.Midterm.Project.repository.RoleRepository;
import com.ironhack.Midterm.Project.repository.ThirdPartyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    private  final Logger LOGGER = LogManager.getLogger(ThirdPartyService.class);

    public ThirdParty create(ThirdParty t){
        LOGGER.info("[INIT] - create third Party user");
        t.setPassword(PasswordUtility.encryptPassword(t.getPassword()));
        ThirdParty thirdParty = thirdPartyRepository.save(t);
        roleRepository.save(new Role("ROLE_THIRD_PARTY", t));
        LOGGER.info("[END] - third Party created with id" + t.getId());
        return thirdParty;
    }
}
