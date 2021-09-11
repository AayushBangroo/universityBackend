package com.practice.springboot.service.guardian;

import com.practice.springboot.entity.Guardian;

public interface GuardianService {
    Guardian findById(Long id);

    void deleteById(Long id);
}
