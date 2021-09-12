package com.practice.springboot.repository;

import com.practice.springboot.entity.Student;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application.properties")
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    @Transactional
    void findAllStudentsTest() {
        //given
        Student student = Student.builder()
                .firstName("Aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .build();

        Student student2 = Student.builder()
                .firstName("Aman")
                .lastName("Sharma")
                .emailId("aman@gmail.com")
                .build();

        //when
        studentRepository.save(student);
        studentRepository.save(student2);

        List<Student> studentList = studentRepository.findAll();

        //then
        assertThat(studentList.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void findByIdTest() {
        //given
        Student student = Student.builder()
                .studentId(1L)
                .firstName("Aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .build();

        studentRepository.save(student);

        Student expected = studentRepository.findById(1L);

        assertThat(expected).isNotNull();
    }

    @Test
    @Transactional
    void deleteByIdTest() {

        studentRepository.deleteById(1L);
        Student expected = studentRepository.findById(1L);

        assertThat(expected).isNull();
    }

    @Test
    @Transactional
    void save() {
        //given
        Student student = new Student(22L, "Aayush", "Bangroo",
                "abc@gmail.com", null, null, null);
        studentRepository.save(student);
        //when
        Student expected = studentRepository.findById(22L);

        //then
        assertThat(expected).isEqualTo(student);
    }
}