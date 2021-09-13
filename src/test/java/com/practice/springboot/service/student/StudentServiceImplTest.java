package com.practice.springboot.service.student;

import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Student;
import com.practice.springboot.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setup() {
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    void findAll() {
        //when
        studentService.findAll();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void findById() {
        studentService.findById(1L);

        verify(studentRepository).findById(1L);
    }

    @Test
    void deleteStudentByIdExceptionThrown() {

        assertThatThrownBy(() -> {
            studentService.deleteStudentById(1L);
        }).isInstanceOf(RuntimeException.class).hasMessage("No such student with id - 1 exists");

    }

    @Test
    void saveStudent() {
        StudentDTO student = StudentDTO.builder()
                .firstName("aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .build();
        studentService.saveStudent(student);

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student captured = studentArgumentCaptor.getValue();

        assertThat(captured).isEqualTo(student.toEntity());
    }

    @Test
    void findByGuardianId() {

        try {
            studentService.findByGuardianId(1L);
        } catch (Exception e) {
        }

        ArgumentCaptor<Long> guardianIdCaptor = ArgumentCaptor.forClass(Long.class);

        verify(studentRepository).findByGuardianId(guardianIdCaptor.capture());

        Long guardianIdExpected = guardianIdCaptor.getValue();

        assertThat(guardianIdExpected).isEqualTo(1L);
    }
}