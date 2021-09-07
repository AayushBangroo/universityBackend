package com.practice.springboot.service.book;

import com.practice.springboot.dto.BookDTO;
import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Book;
import com.practice.springboot.entity.BookDetails;
import com.practice.springboot.entity.Student;
import com.practice.springboot.repository.BookDetailsRepository;
import com.practice.springboot.repository.BookRepository;
import com.practice.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookDetailsRepository bookDetailsRepository;

    @Override
    @Transactional
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id);

        if (book == null) {
            return null;
        }

        return new BookDTO(bookRepository.findById(id));
    }

    @Override
    @Transactional
    public void saveBook(BookDTO book) {
        bookRepository.save(book.toEntity());
    }

    @Override
    @Transactional
    public void issue(BookDTO bookDTO, StudentDTO studentDTO) {

        //get issued books by this student
        List<Book> issuedBooks = bookDetailsRepository.findIssuedBooksByStudentId(studentDTO.getStudentId());
        Integer fineAmount = 0;

        //calculate fine Amount
        for (Book book : issuedBooks) {
            fineAmount += calculateFine(book.getBookDetails().getIssueDate());
        }

        if (fineAmount > 0) {
            throw new RuntimeException("You have pending fine amount: Rs." + fineAmount);
        }

        //If no pending fine Amount
        //check if book is already issued
        Book book = bookRepository.findById(bookDTO.getBookId());

        if (book.getStudent() != null) {
            throw new RuntimeException("Book with id - " + book.getBookId() + " and title " +
                    book.getTitle() + " is already issued to someone.");
        }

        Student student = studentRepository.findById(studentDTO.getStudentId());
        //set book details
        BookDetails bookDetails = new BookDetails();
        bookDetails.setBook(book);
        bookDetails.setIssueDate(new Date());
        bookDetails.setStudent(student);

        //assign book to the student
        book.setStudent(student);

        bookDetailsRepository.save(bookDetails);
    }

    @Override
    @Transactional
    public List<BookDTO> findAvailableBooks() {
        return bookRepository.findAvailableBooks().stream()
                .map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    public Integer calculateFine(Date date) {

        Long days = ((new Date()).getTime() - date.getTime()) / 1000L / 60L / 60L / 24L;

        if (days > 1) {
            return 100;
        }
        return 0;
    }
}
