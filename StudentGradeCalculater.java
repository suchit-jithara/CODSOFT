import java.util.Scanner;

public class StudentGradeCalculater {
  public static void main(String[] args) {
    System.out.println("Jay Bhagvan");

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter the total subject : ");
    int totalSubject = sc.nextInt();

    int[] marks = new int[totalSubject];
    int totalMarks = 0;

    for (int i = 0; i < totalSubject; i++) {
      System.out.print("Enter the " + (i + 1) + " subject marks (out of 100) : ");
      marks[i] = sc.nextInt();
      totalMarks += marks[i];
    }

    double averagePercentage = totalMarks / totalSubject;

    char grade;
    if (averagePercentage >= 90) {
      grade = 'A';
    } else if (averagePercentage >= 80) {
      grade = 'B';
    } else if (averagePercentage >= 70) {
      grade = 'C';
    } else if (averagePercentage >= 60) {
      grade = 'D';
    } else {
      grade = 'F';
    }

    System.out.println("Total Marks: " + totalMarks);
    System.out.println("Average Percentage: " + averagePercentage + "%");
    System.out.println("Grade: " + grade);
  }
}
