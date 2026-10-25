package com.studentmanagement.model;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String course;
    public String semester;
    public String email;
    public Student() {}
}
