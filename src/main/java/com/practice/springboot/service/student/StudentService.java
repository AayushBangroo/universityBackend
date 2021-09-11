package com.practice.springboot.service.student;

import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();

    StudentDTO findById(Long id);

    void deleteStudentById(Long id);

    void saveStudent(StudentDTO student);

    StudentDTO findByGuardianId(Long guardianId);
}
