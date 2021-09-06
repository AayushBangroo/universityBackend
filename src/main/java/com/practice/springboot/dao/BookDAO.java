package com.practice.springboot.dao;

import com.practice.springboot.dao.GenericDAO;
import com.practice.springboot.entity.Book;
import com.practice.springboot.entity.Student;

import java.util.List;

public interface BookDAO extends GenericDAO<Book> {
    public List<Book> findAvailableBooks();
}