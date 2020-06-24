package com.ironhack.Midterm.Project.controller;

import com.ironhack.Midterm.Project.model.ThirdParty;
import com.ironhack.Midterm.Project.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyController {

    @Autowired
    private ThirdPartyService thirdPartyService;

    @PostMapping("/thirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty create(@RequestBody ThirdParty t){ return thirdPartyService.create(t); }
}
