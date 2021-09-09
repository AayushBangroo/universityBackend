package com.practice.springboot.dao;

import java.util.List;

public interface GenericDAO<T> {
    List<T> findAll();

    T findById(Long id);

    void deleteById(Long id);

    void save(T data);
}
