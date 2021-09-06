package com.practice.springboot.controllers;

import com.practice.springboot.entity.Guardian;
import com.practice.springboot.service.guardian.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GuardianRestController {

    @Autowired
    private GuardianService guardianService;

    @GetMapping("/guardian/{id}")
    public Guardian getGuardianById(@PathVariable("id")Long id) {
        Guardian guardian = guardianService.findById(id);

        if(guardian==null){
            throw  new RuntimeException("Guardian not found id - " + id);
        }

        return guardian;
    }
}
