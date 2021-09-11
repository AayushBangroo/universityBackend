package com.practice.springboot.service.student;

import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Guardian;
import com.practice.springboot.entity.Student;
import com.practice.springboot.repository.GuardianRespository;
import com.practice.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GuardianRespository guardianRespository;

    @Override
    public List<StudentDTO> findAll() {

        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(StudentDTO::new).collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {

        Student student = studentRepository.findById(id);

        if (student == null) return null;

        return new StudentDTO(studentRepository.findById(id));
    }

    @Override
    @Transactional
    public void deleteStudentById(Long id) {

        //get student's Guardian
        Guardian guardian = studentRepository.findById(id).getGuardian();
        studentRepository.deleteById(id);
        //delete student's guardian
        if (guardian != null)
            guardianRespository.deleteById(guardian.getGuardianId());
    }

    @Override
    @Transactional
    public void saveStudent(StudentDTO studentDTO) {
        studentRepository.save(studentDTO.toEntity());
    }

    @Override
    public StudentDTO findByGuardianId(Long guardianId) {

        Student student;
        try {
            student = studentRepository.findByGuardianId(guardianId);
        } catch (Exception e) {
            throw new RuntimeException("No such guardian with id - " + guardianId + " exists for a student");
        }

        return new StudentDTO(student);
    }
}
