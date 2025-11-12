package UserManagement;

import CourseManagement.Course;
import java.util.ArrayList;
import java.util.Scanner;
import Utilities.Utility;

/**
 * StudentManagementModule
 * ---------------------------------------------------------
 * Provides methods to manage students within the UCMS system.
 * This includes adding new students, showing all students,
 * removing students, and updating student information.
 *
 * Responsibilities:
 *  - Maintain a list of all students in the system.
 *  - Provide a menu interface for Admins to manage students.
 *  - Validate student input and handle exceptions.
 */
public class StudentManagementModule {

    /** Container to store all student objects in the system. */
    public static ArrayList<Student> studentsContainer = new ArrayList<>();

    /**
     * Displays the main student management menu and routes the admin
     * to the selected student operation.
     *
     * @param admin the Admin object performing management operations
     */
    public static void manageStudents(Admin admin) {

        String[] menuOptions = {"Add Students", "Show Students", "Remove Students", "Previous"};

        int choice = Utility.printMenu("Student Management", menuOptions);

        switch (choice) {
            case 1:
                addStudents(admin);   // Add new student(s)
                break;
            case 2:
                showStudents();       // Display all students
                break;
            case 3:
                removeStudents();     // Remove a student by ID
                break;
            case 4:
                System.exit(0);       // Exit menu
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                manageStudents(admin);
        }
    }

    /**
     * Adds one or more students to the system.
     * Prompts the admin for student details, creates Student objects,
     * and stores them in the studentsContainer list.
     *
     * @param admin the Admin object responsible for adding students
     */
    public static void addStudents(Admin admin) {
        Utility.printInputPromptMenu("Add Students", 60);

        Scanner scanner = new Scanner(System.in);

        // Prompt for the number of students to add
        System.out.print("How many students would you like to add?: ");
        int studentCount = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline

        // Loop to collect details for each student
        for (int i = 1; i <= studentCount; i++) {

            // Generate a unique student ID
            String studentID = "S00" + Student.getStudentCount();

            System.out.print("Enter Firstname: ");
            String firstname = scanner.nextLine();

            System.out.print("Enter Lastname: ");
            String lastname = scanner.nextLine();

            System.out.print("Enter Date of Birth: ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            System.out.print("Enter Phone number: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Email Address: ");
            String email = scanner.nextLine();

            System.out.print("Enter Course Code: ");
            String course = scanner.nextLine();

            try {
                // Create new Student object
                Student newStudent = new Student(
                        firstname, lastname, email,
                        "", address, phone,
                        studentID, dateOfBirth
                );

                // Add student to container via admin's addUser method
                admin.addUser(newStudent, studentsContainer);

            } catch (IllegalArgumentException e) {
                // Handle invalid input and allow re-entry
                System.out.println(e.getMessage());
                addStudents(admin);
            }

            System.out.println("===================================================");
        }
    }

    /**
     * Displays all students currently stored in the system.
     * Iterates over the studentsContainer and prints each student object.
     */
    public static void showStudents() {

        System.out.println("=====================  Students in the System =====================");
        for (Student student : studentsContainer) {
            System.out.println(student); // Assumes Student.toString() is overridden
        }
    }

    /**
     * Removes a student from the system based on their unique student ID.
     * Currently a placeholder method; implementation should validate input
     * and remove the corresponding student from studentsContainer.
     */
    public static void removeStudents() {
        System.out.print("Enter Student ID: ");
        // TODO: Implement actual removal logic
    }

    /**
     * Updates student information in the system.
     * Currently a placeholder method; implementation should allow the admin
     * to select and update student attributes such as name, address, or course.
     */
    public static void updateStudent() {
        System.out.print("How many students would you like to remove?: ");
        // TODO: Implement update logic
    }
}
