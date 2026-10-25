package com.studentmanagement.model;

import jakarta.persistence.*;

@Entity
public class Result {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long studentId;
    public String subject;
    public double marks;
    public double totalMarks;
    public Result() {}
}
