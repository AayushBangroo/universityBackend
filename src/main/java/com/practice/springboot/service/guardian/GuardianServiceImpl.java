package com.practice.springboot.service.guardian;

import com.practice.springboot.entity.Guardian;
import com.practice.springboot.entity.Student;
import com.practice.springboot.repository.GuardianRespository;
import com.practice.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GuardianServiceImpl implements GuardianService {
    @Autowired
    private GuardianRespository guardianRespository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Guardian findById(Long id) {
        Guardian guardian = guardianRespository.findById(id);

        if (guardian == null) {
            throw new RuntimeException("Guardian with id - " + id + " does not exist");
        }

        return guardian;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        Guardian guardian = guardianRespository.findById(id);

        if (guardian == null) {
            throw new RuntimeException("Guardian with id - " + id + " does not exist");
        }

        //set guardian of student to null; orphan will be removed
        Student student = studentRepository.findByGuardianId(id);
        student.setGuardian(null);

        studentRepository.save(student);
    }
}
