package com.practice.springboot.controllers;

import com.practice.springboot.entity.Course;
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
    public List<Course> getAllCourses(){
        return courseService.findAll();
    }

    @GetMapping("/courseMaterial/all")
    public List<CourseMaterial> getAllCourseMaterials(){
        return courseMaterialService.findAll();
    }

    @PostMapping("/course")
    public void saveCourse(@RequestBody Course course){
        courseService.saveCourse(course);
    }

    @PostMapping("/courseMaterial")
    public void saveCourseMaterial(@RequestBody CourseMaterial courseMaterial){
        courseMaterialService.saveCourseMaterial(courseMaterial);
    }
}
