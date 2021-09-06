package com.practice.springboot.service.course;

import com.practice.springboot.entity.Course;
import com.practice.springboot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements  CourseService{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }
}
