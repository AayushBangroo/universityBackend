package com.practice.springboot.service.course;

import com.practice.springboot.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> findAll();

    public Course findById(Long id);

    public void saveCourse(Course course);
}
