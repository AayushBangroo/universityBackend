package com.practice.springboot.service.teacher;

import com.practice.springboot.dto.TeacherDTO;
import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.Teacher;
import com.practice.springboot.repository.CourseRepository;
import com.practice.springboot.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private CourseRepository courseRepository;
    private TeacherService teacherService;

    @BeforeEach
    void setup() {
        teacherService = new TeacherServiceImpl(teacherRepository, courseRepository);
    }

    @Test
    void findAll() {
        teacherService.findAll();

        verify(teacherRepository).findAll();
    }

    @Test
    void findById() {
        teacherService.findById(1L);

        verify(teacherRepository).findById(1L);
    }

    @Test
    void assignCourse() {
        Teacher teacher = Teacher.builder()
                .firstName("aayush")
                .lastName("bangroo")
                .build();

        Course course = Course.builder()
                .credit(3)
                .title("DBMS")
                .build();

        when(teacherRepository.findById(1L)).thenReturn(teacher);
        when(courseRepository.findById(1L)).thenReturn(course);

        teacherService.assignCourse(1L, 1L);

        //verify id
        verify(teacherRepository).findById(1L);
        verify(courseRepository).findById(1L);

        //verify save method
        verify(teacherRepository).save(teacher);
        verify(courseRepository).save(course);
    }

    @Test
    void deleteTeacherByIdExceptionThrown() {
        assertThatThrownBy(() -> {
            teacherService.deleteTeacherById(12L);
        }).isInstanceOf(RuntimeException.class).hasMessage("No such teacher with id - 12 exists");
    }

    @Test
    void saveTeacher() {
        TeacherDTO teacherDTO = TeacherDTO.builder()
                .firstName("aayush")
                .lastName("bangroo")
                .build();

        teacherService.saveTeacher(teacherDTO);

        ArgumentCaptor<Teacher> teacherCaptor = ArgumentCaptor.forClass(Teacher.class);

        verify(teacherRepository).save(teacherCaptor.capture());
        Teacher teacherExpected = teacherCaptor.getValue();

        assertThat(teacherExpected).usingRecursiveComparison().isEqualTo(teacherDTO.toEntity());
    }
}