package com.practice.springboot.service.teacher;

import com.practice.springboot.entity.Teacher;
import com.practice.springboot.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    @Transactional
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public Teacher findById(Long id) {
        return teacherRepository.findById(id);
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
