package com.practice.springboot.service.courseMaterial;

import com.practice.springboot.entity.CourseMaterial;
import com.practice.springboot.repository.CourseMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMaterialServiceImpl implements  CourseMaterialService{
    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    @Override
    public List<CourseMaterial> findAll() {
        return courseMaterialRepository.findAll();
    }

    @Override
    public CourseMaterial findById(Long id) {
        return courseMaterialRepository.findById(id);
    }

    @Override
    public void saveCourseMaterial(CourseMaterial courseMaterial) {
        courseMaterialRepository.save(courseMaterial);
    }
}
