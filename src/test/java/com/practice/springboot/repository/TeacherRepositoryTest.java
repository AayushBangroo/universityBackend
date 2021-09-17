package com.practice.springboot.repository;

import com.practice.springboot.entity.Teacher;
import com.practice.springboot.service.teacher.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository underTest;

    @Autowired
    TeacherService teacherService;

    @Test
    @Transactional
    void findAllTest() {

        Teacher teacher1 = Teacher.builder()
                .firstName("Aayush")
                .lastName("Bangroo")
                .build();

        Teacher teacher2 = Teacher.builder()
                .firstName("Aayush")
                .lastName("Bangroo")
                .build();

        underTest.save(teacher1);
        underTest.save(teacher2);

        List<Teacher> teacherList = underTest.findAll();

        assertThat(teacherList.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void findById() {

        Teacher teacher1 = Teacher.builder()
                .firstName("Aayush")
                .lastName("Bangroo")
                .build();

        underTest.save(teacher1);

        Teacher expected = underTest.findById(1L);

        assertThat(expected).isEqualTo(expected);
    }

    @Test
    @Transactional
    void deleteByIdTest() {

        underTest.deleteById(1L);

        assertThat(underTest.findById(1L)).isNull();
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