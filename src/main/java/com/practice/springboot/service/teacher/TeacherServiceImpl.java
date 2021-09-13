package com.practice.springboot.service.teacher;

import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.Teacher;
import com.practice.springboot.repository.CourseRepository;
import com.practice.springboot.repository.TeacherRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    @Transactional
    public void assignCourse(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId);

        Course course = courseRepository.findById(courseId);

        if (teacher == null)
            throw new RuntimeException("Teacher with id " + teacherId + " does not exist");
        if (course == null)
            throw new RuntimeException("Course with id " + courseId + " does not exist");

        teacher.addCourse(course);
        course.setTeacher(teacher);

        teacherRepository.save(teacher);
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
