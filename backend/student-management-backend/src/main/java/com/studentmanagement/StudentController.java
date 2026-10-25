package com.studentmanagement;

import com.studentmanagement.model.*;
import com.studentmanagement.repository.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5501")
public class StudentController {
    private final StudentRepository students;
    private final AttendanceRepository attendance;
    private final ResultRepository results;

    public StudentController(StudentRepository s, AttendanceRepository a, ResultRepository r) {
        students=s; attendance=a; results=r;
    }

    @GetMapping("/students")
    public List<Student> allStudents() { return students.findAll(); }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student s) {
        s.id=null;
        return students.save(s);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student s) {
        Student old=students.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        old.name=s.name; old.course=s.course; old.semester=s.semester; old.email=s.email;
        return students.save(old);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        if (!students.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        students.deleteById(id);
    }

    @GetMapping("/attendance")
    public List<Attendance> allAttendance() { return attendance.findAll(); }

    @PostMapping("/attendance")
    public Attendance addAttendance(@RequestBody Attendance a) {
        a.id=null;
        return attendance.save(a);
    }

    @GetMapping("/results")
    public List<Result> allResults() { return results.findAll(); }

    @PostMapping("/results")
    public Result addResult(@RequestBody Result r) {
        if (r.marks < 0 || r.totalMarks <= 0 || r.marks > r.totalMarks)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid marks");
        r.id=null;
        return results.save(r);
    }
}
