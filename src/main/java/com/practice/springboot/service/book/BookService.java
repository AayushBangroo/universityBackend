package com.practice.springboot.service.book;

import com.practice.springboot.dto.BookDTO;
import com.practice.springboot.dto.BookDetailsDTO;
import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Book;

import java.util.Date;
import java.util.List;

public interface BookService {
    public List<BookDTO> findAll();

    public BookDTO findById(Long id);

    public void saveBook(BookDTO book);

    public void issue(BookDTO bookDTO, StudentDTO studentDTO);

    public List<BookDTO> findAvailableBooks();

    public Integer calculateFine(Date date);
}
