package com.practice.springboot.service.teacher;

import com.practice.springboot.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

    @Mock
    private TeacherRepository teacherRepository;
    private TeacherService teacherService;

    @BeforeEach
    void setup(){
        teacherService = new TeacherServiceImpl(teacherRepository);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void assignCourse() {
    }

    @Test
    void deleteTeacherById() {
    }

    @Test
    void saveTeacher() {
    }
}