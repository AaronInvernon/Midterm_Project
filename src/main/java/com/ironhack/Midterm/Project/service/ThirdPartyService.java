package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.Utils.PasswordUtility;
import com.ironhack.Midterm.Project.model.Role;
import com.ironhack.Midterm.Project.model.ThirdParty;
import com.ironhack.Midterm.Project.repository.RoleRepository;
import com.ironhack.Midterm.Project.repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    public ThirdParty create(ThirdParty t){
        t.setPassword(PasswordUtility.encryptPassword(t.getPassword()));
        ThirdParty thirdParty = thirdPartyRepository.save(t);
        roleRepository.save(new Role("THIRD_PARTY", t));
        return thirdParty;
    }
}
