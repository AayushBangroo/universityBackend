package com.practice.springboot.controllers;

import com.practice.springboot.dto.CourseDTO;
import com.practice.springboot.dto.CourseMaterialDTO;
import com.practice.springboot.entity.CourseMaterial;
import com.practice.springboot.service.course.CourseService;
import com.practice.springboot.service.courseMaterial.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseRestController {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseMaterialService courseMaterialService;

    @GetMapping("/course/all")
    public List<CourseDTO> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/courseMaterial/all")
    public List<CourseMaterialDTO> getAllCourseMaterials() {
        return courseMaterialService.findAll();
    }

    @PostMapping("/course")
    public void saveCourse(@RequestBody CourseDTO course) {
        courseService.saveCourse(course);
    }

    @PostMapping("/courseMaterial/{courseId}")
    public void saveCourseMaterial(@RequestBody CourseMaterialDTO courseMaterial, @PathVariable Long courseId) {
        courseMaterialService.saveCourseMaterialForCourse(courseMaterial, courseId);
    }

    @PostMapping("/course/enroll/{studentId}/{courseId}")
    public String enrollStudentInCourse(@PathVariable("studentId") Long studentId,
                                        @PathVariable("courseId") Long courseId) {

        courseService.enrollStudentInCourse(studentId, courseId);

        return "Student with id - " + studentId + " is enrolled in course id - " + courseId;
    }
}
