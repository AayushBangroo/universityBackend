package com.practice.springboot.service.teacher;

import com.practice.springboot.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAll();

    Teacher findById(Long id);

    void deleteTeacherById(Long id);

    void saveTeacher(Teacher teacher);
}
