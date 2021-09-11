package com.practice.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="guardian")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Guardian {
    @Id
    @Column(name = "guardian_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guardianId;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="mobile")
    private String mobile;
}
