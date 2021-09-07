package com.practice.springboot.service.courseMaterial;

import com.practice.springboot.dto.CourseMaterialDTO;
import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.CourseMaterial;
import com.practice.springboot.repository.CourseMaterialRepository;
import com.practice.springboot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {
    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    @Transactional
    public List<CourseMaterialDTO> findAll() {
        List<CourseMaterial> courseMaterialList = courseMaterialRepository.findAll();
        return courseMaterialList.stream()
                .map(CourseMaterialDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CourseMaterialDTO findById(Long id) {
        CourseMaterial courseMaterial = courseMaterialRepository.findById(id);

        if (courseMaterial == null) return null;
        return new CourseMaterialDTO(courseMaterial);
    }

    @Override
    @Transactional
    public void saveCourseMaterialForCourse(CourseMaterialDTO courseMaterialDTO, Long courseId) {
        Course course = courseRepository.findById(courseId);

        if (course == null) return;

        CourseMaterial courseMaterial = courseMaterialDTO.toEntity();

        course.setCourseMaterial(courseMaterial);
        courseMaterial.setCourse(course);

        courseRepository.save(course);
        courseMaterialRepository.save(courseMaterial);
    }
}
