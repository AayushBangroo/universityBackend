package com.practice.springboot.controllers;

import com.practice.springboot.entity.Teacher;
import com.practice.springboot.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherRestController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/{teacherId}")
    public Teacher getTeacherById(@PathVariable Long teacherId) {
        return teacherService.findById(teacherId);
    }

    @GetMapping("/all")
    public List<Teacher> getAllTeachers() {
        return teacherService.findAll();
    }

    @PostMapping
    public void saveTeacher(@RequestBody Teacher teacher) {
        teacherService.saveTeacher(teacher);
    }

    @DeleteMapping("/{teacherId}")
    public String deleteTeacherById(@PathVariable Long teacherId) {
        teacherService.deleteTeacherById(teacherId);

        return "Teacher with id " + teacherId + " deleted";
    }
}
