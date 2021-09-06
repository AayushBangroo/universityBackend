package com.practice.springboot.service.student;

import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Student;

import java.util.List;

public interface StudentService {
    public List<StudentDTO> findAll();

    public StudentDTO findById(Long id);

    public void deleteStudentById(Long id);

    public void saveStudent(StudentDTO student);
}
