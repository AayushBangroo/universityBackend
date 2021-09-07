package com.practice.springboot.service.course;

import com.practice.springboot.dto.CourseDTO;
import com.practice.springboot.entity.Course;
import com.practice.springboot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    @Transactional
    public List<CourseDTO> findAll() {
        List<Course> courseList = courseRepository.findAll();
        return courseList.stream()
                .map(CourseDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CourseDTO findById(Long id) {
        Course course = courseRepository.findById(id);
        if (course == null) return null;

        return new CourseDTO(course);
    }

    @Override
    @Transactional
    public void saveCourse(CourseDTO courseDTO) {
        //convert dto to entity
        Course course = courseDTO.toEntity();
        //if course Material is available
        if (course.getCourseMaterial() != null) {
            course.getCourseMaterial().setCourse(course);
        }
        courseRepository.save(course);
    }
}
