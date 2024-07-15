import java.sql.*;
import java.util.Scanner;

public class StudentCourseRegistrationSystem {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/school";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Suchit@312";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Add Course");
                System.out.println("2. Add Student");
                System.out.println("3. List Courses");
                System.out.println("4. Register Student for Course");
                System.out.println("5. Remove Student from Course");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addCourse(connection, scanner);
                        break;
                    case 2:
                        addStudent(connection, scanner);
                        break;
                    case 3:
                        listCourses(connection);
                        break;
                    case 4:
                        registerStudent(connection, scanner);
                        break;
                    case 5:
                        removeStudent(connection, scanner);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addCourse(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine();
        System.out.print("Enter course capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter course schedule: ");
        String schedule = scanner.nextLine();

        String sql = "INSERT INTO courses (course_code, title, description, capacity, schedule) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setInt(4, capacity);
            statement.setString(5, schedule);
            statement.executeUpdate();
            System.out.println("Course added successfully.");
        }
    }

    private static void addStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        String sql = "INSERT INTO students (student_id, name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Student added successfully.");
        }
    }

    private static void listCourses(Connection connection) throws SQLException {
        String sql = "SELECT course_code, title, description, capacity, schedule FROM courses";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String code = resultSet.getString("course_code");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int capacity = resultSet.getInt("capacity");
                String schedule = resultSet.getString("schedule");
                System.out.printf("Code: %s, Title: %s, Description: %s, Capacity: %d, Schedule: %s%n", code, title, description, capacity, schedule);
            }
        }
    }

    private static void registerStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        String sql = "INSERT INTO registrations (student_id, course_code) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setString(2, courseCode);
            statement.executeUpdate();
            System.out.println("Student registered for course successfully.");
        }
    }

    private static void removeStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        String sql = "DELETE FROM registrations WHERE student_id = ? AND course_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setString(2, courseCode);
            statement.executeUpdate();
            System.out.println("Student removed from course successfully.");
        }
    }
}
