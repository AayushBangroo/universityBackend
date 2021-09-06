package com.practice.springboot.controllers;

import com.practice.springboot.dto.BookDTO;
import com.practice.springboot.dto.BookDetailsDTO;
import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.service.book.BookService;
import com.practice.springboot.service.bookDetails.BookDetailsService;
import com.practice.springboot.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    BookDetailsService bookDetailsService;

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.findById(id);

        if (bookDTO == null) {
            throw new RuntimeException("No such book exists id - " + id);
        }

        return bookService.findById(id);
    }

    @PostMapping
    public BookDTO saveBook(@RequestBody BookDTO book) {
        bookService.saveBook(book);

        return book;
    }

    @PostMapping("/issue/{studentId}/{bookId}")
    public BookDTO issueBook(@PathVariable("studentId") Long studentId, @PathVariable("bookId") Long bookId) {
        BookDTO bookDTO = bookService.findById(bookId);
        StudentDTO studentDTO = studentService.findById(studentId);

        if (bookDTO == null) {
            throw new RuntimeException("No such book exists id - " + bookId);
        }
        if (studentDTO == null) {
            throw new RuntimeException("No such student exists id - " + studentId);

        }

        //issue the book to student
        bookService.issue(bookDTO, studentDTO);

        return bookService.findById(bookId);
    }

    @GetMapping("/available")
    public List<BookDTO> getAvailableBooks() {
        return bookService.findAvailableBooks();
    }

    //Details
    @GetMapping("/details/{bookId}")
    public BookDetailsDTO getBookDetails(@PathVariable("bookId") Long id) {
        BookDTO bookDTO = bookService.findById(id);
        BookDetailsDTO bookDetailsDTO = bookDetailsService.findById(id);

        if (bookDTO == null) {
            throw new RuntimeException("No such book exists id - " + id);
        }
        if (bookDetailsDTO == null) {
            throw new RuntimeException("Book is not allocated to any student - " + id);
        }

        BookDetailsDTO bookDetails = bookDetailsService.findByBookId(id);
        return bookDetails;
    }

    @GetMapping("issued/{studentId}")
    public List<BookDTO> getIssuedBooksToStudent(@PathVariable Long studentId) {
        return bookDetailsService.findIssuedBooksByStudentId(studentId);
    }
}
