package com.practice.springboot.controllers;

import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.service.guardian.GuardianService;
import com.practice.springboot.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    StudentService studentService;

    @Autowired
    GuardianService guardianService;

    @GetMapping("/all")
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable("id") Long id) {

        StudentDTO studentDTO = studentService.findById(id);

        if (studentDTO == null) {
            throw new RuntimeException("Student id not found - " + id);
        }

        return studentDTO;
    }

    @GetMapping("/guardian/{guardianId}")
    public StudentDTO getStudentByGuardianId(@PathVariable("guardianId") Long guardianId) {
        return studentService.findByGuardianId(guardianId);
    }

    @PostMapping
    public StudentDTO saveStudent(@RequestBody StudentDTO student) {
        //force save new student
        student.setStudentId(null);
        studentService.saveStudent(student);

        return student;
    }

    @PutMapping
    public StudentDTO updateStudent(@RequestBody StudentDTO student) {
        studentService.saveStudent(student);
        return student;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        StudentDTO studentDTO = studentService.findById(id);

        if (studentDTO == null) {
            throw new RuntimeException("Student id not found - " + id);
        }

        studentService.deleteStudentById(id);

        return "Student with id " + id + " deleted successfully.";
    }
}
