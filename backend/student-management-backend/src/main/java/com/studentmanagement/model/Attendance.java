package com.studentmanagement.model;

import jakarta.persistence.*;

@Entity
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long studentId;
    public String date;
    public String status;
    public Attendance() {}
}
