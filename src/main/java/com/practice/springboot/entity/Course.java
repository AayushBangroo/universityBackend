package com.practice.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "title")
    private String title;

    @Column(name = "credit")
    private Integer credit;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("course")
    private CourseMaterial courseMaterial;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnoreProperties("courseList")
    List<Student> studentList;

    public void addStudent(Student student) {
        if (studentList == null) {
            studentList = new ArrayList<>();
        }
        studentList.add(student);
    }
}
