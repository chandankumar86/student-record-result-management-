
package com.studentmanagement;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final List<Map<String, Object>> students = new ArrayList<>();
    private int nextId = 1;

    // View all students
    @GetMapping
    public List<Map<String, Object>> getStudents() {
        return students;
    }

    // Add a student
    @PostMapping
    public Map<String, Object> addStudent(
            @RequestBody Map<String, Object> student) {

        Map<String, Object> newStudent = new HashMap<>(student);
        newStudent.put("id", nextId++);
        students.add(newStudent);

        return newStudent;
    }

    // Search student by ID
    @GetMapping("/{id}")
    public Map<String, Object> getStudent(@PathVariable int id) {
        return findStudent(id);
    }

    // Update a student
    @PutMapping("/{id}")
    public Map<String, Object> updateStudent(
            @PathVariable int id,
            @RequestBody Map<String, Object> updatedStudent) {

        Map<String, Object> student = findStudent(id);

        if (updatedStudent.containsKey("name")) {
            student.put("name", updatedStudent.get("name"));
        }

        if (updatedStudent.containsKey("course")) {
            student.put("course", updatedStudent.get("course"));
        }

        if (updatedStudent.containsKey("semester")) {
            student.put("semester", updatedStudent.get("semester"));
        }

        return student;
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public Map<String, String> deleteStudent(@PathVariable int id) {
        Map<String, Object> student = findStudent(id);
        students.remove(student);

        return Map.of("message", "Student deleted successfully");
    }

    // Find student helper
    private Map<String, Object> findStudent(int id) {
        return students.stream()
                .filter(student -> student.get("id").equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Student not found"));
    }
}
