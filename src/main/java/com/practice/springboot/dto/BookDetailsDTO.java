package com.practice.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.springboot.entity.BookDetails;
import com.practice.springboot.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsDTO {

    private Long bookDetailsId;

    @JsonProperty("book")
    private BookDTO bookDTO;

    private Date issueDate;

    @JsonProperty("student")
    private StudentDTO studentDTO;

    public BookDetailsDTO(BookDetails b) {
        this.setBookDetailsId(b.getBookDetailId());
        this.setBookDTO(new BookDTO(b.getBook()));
        this.setIssueDate(b.getIssueDate());
        this.setStudentDTO(new StudentDTO(b.getBook().getStudent()));
    }
}
