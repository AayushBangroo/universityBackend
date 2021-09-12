package com.practice.springboot.repository;

import com.practice.springboot.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void findAll() {
        Course course1 = Course.builder()
                .credit(2)
                .title("DBMS")
                .build();
        courseRepository.save(course1);

        Course course2 = Course.builder()
                .credit(3)
                .title("COA")
                .build();
        courseRepository.save(course2);

        assertThat(courseRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void findById() {
        Course course = Course.builder()
                .courseId(1L)
                .credit(2)
                .title("DBMS")
                .build();
        courseRepository.save(course);

        Course expected = courseRepository.findById(1L);

        assertThat(expected).isNotNull();
    }

    @Test
    @Transactional
    void save() {
        Course course = Course.builder()
                .courseId(1L)
                .credit(2)
                .title("DBMS")
                .build();
        courseRepository.save(course);

        assertThat(courseRepository.findById(1L)).isNotNull();
    }
}