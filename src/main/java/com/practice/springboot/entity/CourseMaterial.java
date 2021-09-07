package com.practice.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course_material")
public class CourseMaterial {
    @Id
    @Column(name = "course_material_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseMaterialId;

    @Column(name = "url")
    private String url;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("courseMaterial")
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseMaterial(CourseMaterial courseMaterial, Course course) {
       this.url = courseMaterial.getUrl();
       this.course = course;
    }
}
