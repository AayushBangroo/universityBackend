package com.practice.springboot.dao;

import com.practice.springboot.entity.Book;

import java.util.List;

public interface BookDAO extends GenericDAO<Book> {
     List<Book> findAvailableBooks();
}