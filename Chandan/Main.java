import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String[] SUBJECTS = {"Subject 1", "Subject 2", "Subject 3"};

    private final List<Student> students = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ", 1, 6);
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    listStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    enterMarks();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\nStudent Record & Result Management");
        System.out.println("1. Add student");
        System.out.println("2. List students");
        System.out.println("3. Search student");
        System.out.println("4. Enter or update marks");
        System.out.println("5. Delete student");
        System.out.println("6. Exit");
    }

    private void addStudent() {
        int id = readInt("Student ID: ", 1, Integer.MAX_VALUE);
        if (findStudent(id) != null) {
            System.out.println("A student with that ID already exists.");
            return;
        }

        String name = readRequiredText("Student name: ");
        students.add(new Student(id, name));
        System.out.println("Student added.");
    }

    private void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            printStudent(student);
        }
    }

    private void searchStudent() {
        int id = readInt("Student ID: ", 1, Integer.MAX_VALUE);
        Student student = findStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        printStudent(student);
    }

    private void enterMarks() {
        int id = readInt("Student ID: ", 1, Integer.MAX_VALUE);
        Student student = findStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        int[] marks = new int[SUBJECTS.length];
        for (int index = 0; index < SUBJECTS.length; index++) {
            marks[index] = readInt(SUBJECTS[index] + " marks (0-100): ", 0, 100);
        }
        student.setMarks(marks);
        System.out.println("Marks saved.");
    }

    private void deleteStudent() {
        int id = readInt("Student ID: ", 1, Integer.MAX_VALUE);
        Student student = findStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted.");
    }

    private Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private void printStudent(Student student) {
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Result: " + student.resultSummary());
        System.out.println();
    }

    private int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= minimum && value <= maximum) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid whole number.");
                continue;
            }
            System.out.println("Enter a number from " + minimum + " to " + maximum + ".");
        }
    }

    private String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This value cannot be empty.");
        }
    }

    private static final class Student {
        private final int id;
        private final String name;
        private int[] marks = {-1, -1, -1};

        private Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        private int getId() {
            return id;
        }

        private String getName() {
            return name;
        }

        private void setMarks(int[] marks) {
            this.marks = marks.clone();
        }

        private String resultSummary() {
            int total = 0;
            boolean passed = true;
            for (int mark : marks) {
                if (mark < 0) {
                    return "Pending (marks not entered)";
                }
                total += mark;
                if (mark < 40) {
                    passed = false;
                }
            }

            double average = (double) total / marks.length;
            String grade = gradeFor(average, passed);
            return String.format("%s, total %d/%d, average %.2f%%", grade, total, marks.length * 100, average);
        }

        private String gradeFor(double average, boolean passed) {
            if (!passed) {
                return "Fail";
            }
            if (average >= 90) {
                return "A+";
            }
            if (average >= 80) {
                return "A";
            }
            if (average >= 70) {
                return "B";
            }
            if (average >= 60) {
                return "C";
            }
            if (average >= 50) {
                return "D";
            }
            return "Pass";
        }
    }
}