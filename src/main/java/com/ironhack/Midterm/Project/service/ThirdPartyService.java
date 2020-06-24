package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.model.ThirdParty;
import com.ironhack.Midterm.Project.repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService {
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    public ThirdParty create(ThirdParty t){ return thirdPartyRepository.save(t); }
}
