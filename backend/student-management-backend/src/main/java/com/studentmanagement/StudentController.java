package com.studentmanagement;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final List<Map<String, Object>> students = new ArrayList<>();
    private int nextId = 1;

    @GetMapping
    public List<Map<String, Object>> getStudents() {
        return students;
    }

    @PostMapping
    public Map<String, Object> addStudent(@RequestBody Map<String, Object> student) {
        student.put("id", nextId++);
        students.add(new HashMap<>(student));
        return student;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getStudent(@PathVariable int id) {
        return students.stream()
                .filter(s -> s.get("id").equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteStudent(@PathVariable int id) {
        boolean removed = students.removeIf(s -> s.get("id").equals(id));

        if (!removed) {
            throw new NoSuchElementException("Student not found");
        }

        return Map.of("message", "Student deleted successfully");
    }
}

