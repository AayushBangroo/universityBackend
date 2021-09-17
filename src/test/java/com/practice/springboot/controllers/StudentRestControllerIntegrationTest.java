package com.practice.springboot.controllers;

import com.practice.springboot.DemoApplication;
import com.practice.springboot.dto.StudentDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("classpath:data-h2.sql")
class StudentRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Disabled
    void getAllStudentsTest() {

    }

    @Test
    void getStudentById() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/student/1",
                StudentDTO.class)).extracting("studentId", "firstName", "lastName", "emailId")
                .containsExactly(1L, "aayush", "bangroo", "aayush@gmail.com");
    }

    @Test
    void getStudentByGuardianId() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/student/guardian/1",
                StudentDTO.class)).extracting("studentId", "firstName", "lastName", "emailId")
                .containsExactly(1L, "aayush", "bangroo", "aayush@gmail.com");
    }

    @Test
    void saveStudentTest() {
        StudentDTO studentDTO = StudentDTO.builder()
                .firstName("aman")
                .lastName("sharman")
                .emailId("aman@yahoo.com")
                .build();

        ResponseEntity<StudentDTO> expected = restTemplate.postForEntity("http://localhost:" + port + "/api/student"
                , studentDTO, StudentDTO.class);

        assertThat(expected.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @Disabled
    void updateStudentTest() {
    }

    @Test
    @Disabled
    void deleteStudentTest() {
    }
}