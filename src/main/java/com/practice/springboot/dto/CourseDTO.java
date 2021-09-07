package com.practice.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.CourseMaterial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {

    private Long courseId;

    private String title;

    private Integer credit;

    @JsonProperty("courseMaterial")
    private CourseMaterialDTO courseMaterialDTO;

    public Course toEntity() {
        Course course = new Course();
        course.setTitle(this.title);
        course.setCredit(this.credit);
        if (this.courseMaterialDTO != null)
            course.setCourseMaterial(this.courseMaterialDTO.toEntity());

        return course;
    }

    public CourseDTO(Course course) {
        this.title = course.getTitle();
        this.courseId = course.getCourseId();
        this.credit = course.getCredit();
        if (course.getCourseMaterial() != null)
            this.courseMaterialDTO = new CourseMaterialDTO(course.getCourseMaterial());
    }
}
