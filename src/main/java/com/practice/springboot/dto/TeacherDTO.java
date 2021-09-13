package com.practice.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherDTO {
    private Long teacherId;

    private String firstName;

    private String lastName;

    @JsonProperty("courseList")
    List<CourseDTO> courseDTOList;

    public Teacher toEntity() {
        Teacher teacher = new Teacher();
        teacher.setFirstName(this.firstName);
        teacher.setLastName(this.lastName);

        return teacher;
    }

    public TeacherDTO(Teacher teacher) {
        this.teacherId = teacher.getTeacherId();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();

        if (teacher.getCourseList() != null) {
            this.courseDTOList = teacher.getCourseList().stream()
                    .map(CourseDTO::new).collect(Collectors.toList());
        }
    }
}
