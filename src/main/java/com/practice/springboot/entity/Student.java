package com.practice.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guardian_id")
    private Guardian guardian;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties("student")
    List<Book> issuedBooks;

    @ManyToMany(mappedBy = "studentList")
    List<Course> courseList;

    public void addCourse(Course course) {
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        courseList.add(course);
    }
}
