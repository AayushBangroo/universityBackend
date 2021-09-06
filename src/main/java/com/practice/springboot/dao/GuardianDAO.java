package com.practice.springboot.dao;

import com.practice.springboot.entity.Guardian;

public interface GuardianDAO {
    public Guardian findById(Long id);

    public void deleteById(Long id);
}
