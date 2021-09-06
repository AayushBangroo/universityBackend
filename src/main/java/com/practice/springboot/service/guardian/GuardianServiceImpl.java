package com.practice.springboot.service.guardian;

import com.practice.springboot.entity.Guardian;
import com.practice.springboot.repository.GuardianRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GuardianServiceImpl implements GuardianService {
    @Autowired
    private GuardianRespository guardianRespository;

    @Override
    @Transactional
    public Guardian findById(Long id) {
        Guardian guardian= guardianRespository.findById(id);

        return guardian;
    }
}
