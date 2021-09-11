package com.practice.springboot.dao;

import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Student;

public interface StudentDAO extends GenericDAO<Student> {
    Student findByGuardianId(Long guardianId);
}
