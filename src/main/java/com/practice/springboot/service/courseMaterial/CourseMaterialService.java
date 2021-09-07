package com.practice.springboot.service.courseMaterial;

import com.practice.springboot.dto.CourseMaterialDTO;

import java.util.List;

public interface CourseMaterialService {
    public List<CourseMaterialDTO> findAll();

    public CourseMaterialDTO findById(Long id);

    public void saveCourseMaterialForCourse(CourseMaterialDTO courseMaterial, Long courseId);
}
