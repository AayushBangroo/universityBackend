package com.practice.springboot.repository;

import com.practice.springboot.entity.Guardian;
import com.practice.springboot.entity.Student;
import com.practice.springboot.service.guardian.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.security.Guard;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuardianRespositoryTest {

    @Autowired
    GuardianRespository guardianRespository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GuardianService guardianService;

    @BeforeEach
    public void saveDummyGuardian(){
        Guardian guardian = Guardian.builder()
                .name("aayush")
                .email("aayush@gmail.com")
                .mobile("123123")
                .build();

        Student student = Student.builder()
                .firstName("aman")
                .lastName("sharma")
                .emailId("aman@yahoo.com")
                .guardian(guardian)
                .build();

        //guardian id auto increments to 1; cascade save
        studentRepository.save(student);
    }

    @Test
    void findById() {

        Guardian expected = guardianRespository.findById(1L);

        assertThat(expected).isNotNull();
    }

    @Test
    void deleteById() {
        guardianService.deleteById(1L);

        Guardian guardian = guardianRespository.findById(1L);

        assertThat(guardian).isNull();
    }
}