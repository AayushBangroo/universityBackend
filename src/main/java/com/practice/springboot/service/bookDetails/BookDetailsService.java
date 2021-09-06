package com.practice.springboot.service.bookDetails;

import com.practice.springboot.dto.BookDTO;
import com.practice.springboot.dto.BookDetailsDTO;
import com.practice.springboot.entity.BookDetails;

import java.util.List;

public interface BookDetailsService {
    public List<BookDetailsDTO> findAll();

    public BookDetailsDTO findById(Long id);

    public BookDetailsDTO findByBookId(Long id);

    public void saveBookDetails(BookDetails bookDetails);

    public List<BookDTO> findIssuedBooksByStudentId(Long studentId);

}
