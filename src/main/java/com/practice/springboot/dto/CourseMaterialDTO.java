package com.practice.springboot.dto;

import com.practice.springboot.entity.CourseMaterial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterialDTO {

    private Long courseMaterialId;

    private String url;

    private String courseName;

    private Long courseId;

    public CourseMaterial toEntity() {
        CourseMaterial courseMaterial = new CourseMaterial();
        courseMaterial.setUrl(this.url);
        return courseMaterial;
    }

    public CourseMaterialDTO(CourseMaterial courseMaterial) {
        url = courseMaterial.getUrl();
        courseMaterialId = courseMaterial.getCourseMaterialId();
        courseName = courseMaterial.getCourse().getTitle();
        courseId = courseMaterial.getCourse().getCourseId();
    }
}
