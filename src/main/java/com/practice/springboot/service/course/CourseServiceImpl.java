package com.practice.springboot.service.course;

import com.practice.springboot.dto.CourseDTO;
import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.Student;
import com.practice.springboot.repository.CourseRepository;
import com.practice.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

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

    @Override
    @Transactional
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        //get student
        Student student = studentRepository.findById(studentId);
        //get course
        Course course = courseRepository.findById(courseId);

        if (student == null)
            throw new RuntimeException("No student with id " + studentId + " exists");
        if (course == null)
            throw new RuntimeException("No course with id " + courseId + " exists");
        //enroll student
        student.addCourse(course);
        course.addStudent(student);

        studentRepository.save(student);
    }
}
