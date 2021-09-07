package com.practice.springboot.service.course;

import com.practice.springboot.dto.CourseDTO;
import com.practice.springboot.entity.Course;

import java.util.List;

public interface CourseService {
    public List<CourseDTO> findAll();

    public CourseDTO findById(Long id);

    public void saveCourse(CourseDTO course);
}
