package com.practice.springboot.repository;

import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Sql(scripts = "classpath:data-h2.sql")
@TestPropertySource(locations = "classpath:application.properties")
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository underTest;

    @Test
    void findAllTest() {

        List<Teacher> teacherList = underTest.findAll();

        assertThat(teacherList.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void findById() {

        Teacher expected = underTest.findById(1L);

        assertThat(expected).isNotNull();
    }

    @Test
    @Transactional
    void deleteByIdTest() {

        underTest.deleteById(1L);

        Teacher expected = underTest.findById(1L);

        assertThat(expected).isNull();
    }

    @Test
    @Transactional
    void saveStudentTest() {
        Teacher teacher = Teacher.builder()
                .teacherId(10L)
                .firstName("Aman")
                .lastName("Sharma")
                .build();

        underTest.save(teacher);

        assertThat(underTest.findById(10L)).isEqualTo(teacher);
    }
}