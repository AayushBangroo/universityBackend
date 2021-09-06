package com.practice.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    private BookDetails bookDetails;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @JsonIgnoreProperties({"issuedBooks","guardian"})
    Student student;
}
