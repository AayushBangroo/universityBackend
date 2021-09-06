package com.practice.springboot.dao;

import com.practice.springboot.dto.BookDetailsDTO;
import com.practice.springboot.entity.Book;
import com.practice.springboot.entity.BookDetails;

import java.util.List;

public interface BookDetailsDAO extends GenericDAO<BookDetails> {
    public List<Book> findIssuedBooksByStudentId(Long studentId);

    public BookDetails findByBookId(Long id);
}
