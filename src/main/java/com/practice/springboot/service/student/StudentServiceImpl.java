package com.practice.springboot.service.student;

import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Student;
import com.practice.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public List<StudentDTO> findAll() {

        List<Student> studentList= studentRepository.findAll();
        return studentList.stream().map(StudentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDTO findById(Long id) {
        return new StudentDTO(studentRepository.findById(id));
    }

    @Override
    @Transactional
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveStudent(StudentDTO studentDTO) {
        studentRepository.save(studentDTO.toEntity());
    }
}
