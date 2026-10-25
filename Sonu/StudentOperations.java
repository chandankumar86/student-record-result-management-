import java.util.Scanner;

public class StudentOperations {

    public void updateStudent(
            StudentRecords records, Scanner sc) {

        System.out.print("Enter student ID to update: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        StudentRecords.Student student = records.getStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        student.name = sc.nextLine().trim();

        System.out.print("Enter new course: ");
        student.course = sc.nextLine().trim();

        System.out.println("Student updated successfully.");
    }

    public void deleteStudent(
            StudentRecords records, Scanner sc) {

        System.out.print("Enter student ID to delete: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        if (records.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
}
