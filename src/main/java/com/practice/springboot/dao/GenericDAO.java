package com.practice.springboot.dao;

import java.util.List;

public interface GenericDAO<T> {
    public List<T> findAll();

    public T findById(Long id);

    public void deleteById(Long id);

    public void save(T data);
}
