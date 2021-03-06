package com.practice.springboot.dao;

import com.practice.springboot.entity.Book;
import com.practice.springboot.entity.BookDetails;

import java.util.List;

public interface BookDetailsDAO extends GenericDAO<BookDetails> {
     List<Book> findIssuedBooksByStudentId(Long studentId);

     BookDetails findByBookId(Long id);
}
