package com.practice.springboot.service.course;

import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.CourseMaterial;
import com.practice.springboot.repository.CourseMaterialRepository;
import com.practice.springboot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    @Override
    @Transactional
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Course findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        //if course Material is available
        if (course.getCourseMaterial() != null) {
            course.getCourseMaterial().setCourse(course);
        }
        courseRepository.save(course);
    }
}
