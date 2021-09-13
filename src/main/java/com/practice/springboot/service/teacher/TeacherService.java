package com.practice.springboot.service.teacher;

import com.practice.springboot.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> findAll();

    TeacherDTO findById(Long id);

    void assignCourse(Long teacherId, Long courseId);

    void deleteTeacherById(Long id);

    void saveTeacher(TeacherDTO teacher);
}
