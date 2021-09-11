package com.practice.springboot.controllers;

import com.practice.springboot.entity.Guardian;
import com.practice.springboot.service.guardian.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/guardian/{id}")
    public void deleteGuardianById(@PathVariable Long id){
        Guardian guardian = guardianService.findById(id);

        if(guardian==null){
            throw  new RuntimeException("Guardian not found id - " + id);
        }

        guardianService.deleteById(id);
    }
}
