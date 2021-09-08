package com.practice.springboot.service.bookDetails;

import com.practice.springboot.dto.BookDTO;
import com.practice.springboot.dto.BookDetailsDTO;
import com.practice.springboot.entity.BookDetails;
import com.practice.springboot.repository.BookDetailsRepository;
import com.practice.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookDetailsServiceImpl implements BookDetailsService {

    @Autowired
    BookDetailsRepository bookDetailsRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional
    public List<BookDetailsDTO> findAll() {
        return bookDetailsRepository.findAll().stream()
                .map(BookDetailsDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDetailsDTO findById(Long id) {
        return new BookDetailsDTO(bookDetailsRepository.findById(id));
    }

    @Override
    public BookDetailsDTO findByBookId(Long id) {

        BookDetails bookDetails;
        try {
            bookDetails = bookDetailsRepository.findByBookId(id);
        } catch (NoResultException e) {
            bookDetails = null;
        }

        if (bookDetails == null) return null;
        return new BookDetailsDTO(bookDetails);
    }

    @Override
    @Transactional
    public void saveBookDetails(BookDetails bookDetails) {
        bookDetailsRepository.save(bookDetails);
    }

    @Override
    @Transactional
    public List<BookDTO> findIssuedBooksByStudentId(Long studentId) {
        return bookDetailsRepository.findIssuedBooksByStudentId(studentId).stream()
                .map(BookDTO::new).collect(Collectors.toList());
    }
}
