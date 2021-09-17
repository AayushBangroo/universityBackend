package com.practice.springboot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.springboot.dto.GuardianDTO;
import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.service.guardian.GuardianService;
import com.practice.springboot.service.student.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentRestController.class)
class StudentRestControllerTest {

    @MockBean
    private StudentService studentService;

    @MockBean
    private GuardianService guardianService;

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllStudents() throws Exception {
        StudentDTO student1 = StudentDTO.builder()
                .firstName("aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .build();

        StudentDTO student2 = StudentDTO.builder()
                .firstName("aman")
                .lastName("sharma")
                .emailId("aman@gmail.com")
                .build();

        List<StudentDTO> studentDTOList = Arrays.asList(student1, student2);

        when(studentService.findAll()).thenReturn(studentDTOList);

        mvc.perform(get("/api/student/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getStudentById() throws Exception {
        StudentDTO student1 = StudentDTO.builder()
                .studentId(1L)
                .firstName("aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .build();

        when(studentService.findById(1L)).thenReturn(student1);

        mvc.perform(get("/api/student/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(student1.getStudentId().intValue())));
    }

    @Test
    void getStudentByGuardianId() throws Exception {
        GuardianDTO guardian = GuardianDTO.builder()
                .guardianId(1L)
                .name("sunny")
                .mobile("123123")
                .email("sunny@gmail.com")
                .build();

        StudentDTO student = StudentDTO.builder()
                .studentId(1L)
                .firstName("aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .guardianDTO(guardian)
                .build();

        when(studentService.findByGuardianId(1L)).thenReturn(student);

        mvc.perform(get("/api/student/guardian/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(student.getStudentId().intValue())))
                .andExpect(jsonPath("$.guardian.guardianId",
                        is(guardian.getGuardianId().intValue())));
    }

    @Test
    void saveStudent() throws Exception {
        StudentDTO student = StudentDTO.builder()
                .firstName("aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .build();

        mvc.perform(post("/api/student").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    void updateStudent() throws Exception {
        StudentDTO student = StudentDTO.builder()
                .firstName("aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .build();

        mvc.perform(put("/api/student").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void deleteStudentSuccess() throws Exception {
        StudentDTO student = StudentDTO.builder()
                .studentId(1L)
                .firstName("aayush")
                .lastName("bangroo")
                .emailId("aayush@gmail.com")
                .build();

        when(studentService.findById(1L)).thenReturn(student);

        mvc.perform(delete("/api/student/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Student with id 1 deleted successfully.")));
    }

    @Test
    void deleteStudentException() {

        when(studentService.findById(1L)).thenReturn(null);

        assertThatThrownBy(() ->
                mvc.perform(delete("/api/student/1")
                        .contentType(MediaType.APPLICATION_JSON))).isInstanceOf(NestedServletException.class)
                .hasCause(new RuntimeException("Student id not found - 1"));
    }
}