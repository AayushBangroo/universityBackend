package com.practice.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.springboot.entity.Book;
import com.practice.springboot.entity.Guardian;
import com.practice.springboot.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long studentId;

    private String firstName;

    private String lastName;

    private String emailId;

    @JsonProperty("guardian")
    private GuardianDTO guardianDTO;


    public StudentDTO(Student student) {
        this.studentId = student.getStudentId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.emailId = student.getEmailId();
        if (student.getGuardian() != null)
            this.guardianDTO = new GuardianDTO(student.getGuardian());
    }

    public Student toEntity() {
        Student student = new Student();
        student.setStudentId(this.getStudentId());
        student.setFirstName(this.getFirstName());
        student.setLastName(this.getLastName());
        student.setEmailId(this.getLastName());

        if (this.getGuardianDTO() != null)
            student.setGuardian(this.getGuardianDTO().toEntity());

        return student;
    }
}
