public class ResultCalculator {

    public double calculatePercentage(
            double subject1,
            double subject2,
            double subject3,
            double subject4,
            double subject5) {

        double total = subject1 + subject2 + subject3
                     + subject4 + subject5;

        return total / 5.0;
    }

    public String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 40) {
            return "D";
        } else {
            return "Fail";
        }
    }

    public void displayResult(
            String studentName,
            double s1, double s2, double s3,
            double s4, double s5) {

        double[] marks = {s1, s2, s3, s4, s5};

        for (double mark : marks) {
            if (mark < 0 || mark > 100) {
                System.out.println(
                    "Invalid marks! Enter marks between 0 and 100.");
                return;
            }
        }

        double percentage =
                calculatePercentage(s1, s2, s3, s4, s5);

        System.out.println("\n--- Student Result ---");
        System.out.println("Name: " + studentName);
        System.out.println("Total Marks: "
                + (s1 + s2 + s3 + s4 + s5) + "/500");
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Grade: " + calculateGrade(percentage));
    }
}
