package com.practice.springboot.dao;

import com.practice.springboot.entity.Guardian;

public interface GuardianDAO {
    Guardian findById(Long id);

    void deleteById(Long id);
}
