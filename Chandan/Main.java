import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentRecords records = new StudentRecords();
    private final StudentOperations operations = new StudentOperations();
    private final ResultCalculator calculator = new ResultCalculator();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n==================================");
            System.out.println(" STUDENT RECORD & RESULT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Calculate Student Result");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        records.addStudent(scanner);
                        break;
                    case "2":
                        records.listStudents();
                        break;
                    case "3":
                        records.searchStudent(scanner);
                        break;
                    case "4":
                        operations.updateStudent(records, scanner);
                        break;
                    case "5":
                        operations.deleteStudent(records, scanner);
                        break;
                    case "6":
                        calculateResult();
                        break;
                    case "7":
                        running = false;
                        System.out.println("Thank you for using the system.");
                        break;
                    default:
                        System.out.println("Invalid option. Choose 1 to 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void calculateResult() {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        StudentRecords.Student student = records.getStudent(id);

        if (student == null) {
            System.out.println("Student not found. Add the student first.");
            return;
        }

        double[] marks = new double[5];

        for (int i = 0; i < marks.length; i++) {
            while (true) {
                System.out.print("Enter marks for Subject " + (i + 1)
                        + " (0-100): ");
                try {
                    double mark = Double.parseDouble(scanner.nextLine().trim());

                    if (Double.isFinite(mark) && mark >= 0 && mark <= 100) {
                        marks[i] = mark;
                        break;
                    }

                    System.out.println("Marks must be between 0 and 100.");
                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid number.");
                }
            }
        }

        calculator.displayResult(
                student.name,
                marks[0], marks[1], marks[2], marks[3], marks[4]
        );
    }
}
