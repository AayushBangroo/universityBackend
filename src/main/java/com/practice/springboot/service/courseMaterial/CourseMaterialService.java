package com.practice.springboot.service.courseMaterial;

import com.practice.springboot.entity.CourseMaterial;

import java.util.List;

public interface CourseMaterialService {
    public List<CourseMaterial> findAll();

    public CourseMaterial findById(Long id);

    public void saveCourseMaterial(CourseMaterial courseMaterial);
}
