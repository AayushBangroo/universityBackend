package com.practice.springboot.dto;

import com.practice.springboot.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long bookId;

    private String title;

    private String author;

    public BookDTO(Book b){
        this.setBookId(b.getBookId());
        this.setAuthor(b.getAuthor());
        this.setTitle(b.getTitle());
    }

    public Book toEntity(){
        Book book = new Book();
        book.setTitle(this.getTitle());
        book.setAuthor(this.getAuthor());

        return book;
    }
}
