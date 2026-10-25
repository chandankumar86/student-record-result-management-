import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRecords {

    public static class Student {
        public int id;
        public String name;
        public String course;

        public Student(int id, String name, String course) {
            this.id = id;
            this.name = name;
            this.course = course;
        }

        @Override
        public String toString() {
            return "ID: " + id
                    + " | Name: " + name
                    + " | Course: " + course;
        }
    }

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Scanner sc) {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            if (findStudent(id) != null) {
                System.out.println("Student ID already exists.");
                return;
            }

            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter course: ");
            String course = sc.nextLine().trim();

            students.add(new Student(id, name, course));
            System.out.println("Student added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void searchStudent(Scanner sc) {
        try {
            System.out.print("Enter student ID to search: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            Student student = findStudent(id);

            if (student == null) {
                System.out.println("Student not found.");
            } else {
                System.out.println(student);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    public Student getStudent(int id) {
        return findStudent(id);
    }

    public boolean deleteStudent(int id) {
        Student student = findStudent(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        return true;
    }

    private Student findStudent(int id) {
        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }
}
