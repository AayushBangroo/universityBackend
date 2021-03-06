package com.practice.springboot.controllers;

import com.practice.springboot.dto.TeacherDTO;
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
    public TeacherDTO getTeacherById(@PathVariable Long teacherId) {
        return teacherService.findById(teacherId);
    }

    @GetMapping("/all")
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.findAll();
    }

    @PostMapping
    public void saveTeacher(@RequestBody TeacherDTO teacher) {
        teacherService.saveTeacher(teacher);
    }

    @PostMapping("/{teacherId}/{courseId}")
    public void assignCourseToTeacher(@PathVariable("teacherId") Long teacherId,
                                         @PathVariable("courseId") Long courseId) {
        teacherService.assignCourse(teacherId,courseId);
    }

    @DeleteMapping("/{teacherId}")
    public String deleteTeacherById(@PathVariable Long teacherId) {
        teacherService.deleteTeacherById(teacherId);

        return "Teacher with id " + teacherId + " deleted";
    }
}
