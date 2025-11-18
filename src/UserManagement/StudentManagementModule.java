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
     * Prompts for student ID, searches for the student, and removes them if found.
     */
    public static void removeStudents() {
        Scanner scanner = new Scanner(System.in);
        Utility.printInputPromptMenu("Remove Student", 60);
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        Student studentToRemove = null;
        for (Student student : studentsContainer) {
            if (student.getStudentId().equals(studentId)) {
                studentToRemove = student;
                break;
            }
        }
        
        if (studentToRemove != null) {
            System.out.print("Are you sure you want to remove " + studentToRemove.getFirstname() + 
                           " " + studentToRemove.getLastname() + "? (Y/N): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("Y")) {
                studentsContainer.remove(studentToRemove);
                System.out.println("✓ Student removed successfully!");
            } else {
                System.out.println("Remove operation cancelled.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found!");
        }
    }

    /**
     * Updates student information in the system.
     * Prompts for student ID, finds the student, and allows updating their details.
     */
    public static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        Utility.printInputPromptMenu("Update Student", 60);
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        Student studentToUpdate = null;
        for (Student student : studentsContainer) {
            if (student.getStudentId().equals(studentId)) {
                studentToUpdate = student;
                break;
            }
        }
        
        if (studentToUpdate != null) {
            System.out.println("Current student: " + studentToUpdate.getFirstname() + " " + studentToUpdate.getLastname());
            
            String[] updateOptions = {"Update First Name", "Update Last Name", "Update Email", 
                                     "Update Phone", "Update Address", "Update GPA", "Back"};
            int choice = Utility.printMenu("Update Student Menu", updateOptions);
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new first name: ");
                    String newFirstName = scanner.nextLine();
                    studentToUpdate.setFirstname(newFirstName);
                    System.out.println("✓ First name updated successfully!");
                    break;
                case 2:
                    System.out.print("Enter new last name: ");
                    String newLastName = scanner.nextLine();
                    studentToUpdate.setLastname(newLastName);
                    System.out.println("✓ Last name updated successfully!");
                    break;
                case 3:
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    studentToUpdate.setEmail(newEmail);
                    System.out.println("✓ Email updated successfully!");
                    break;
                case 4:
                    System.out.print("Enter new phone: ");
                    String newPhone = scanner.nextLine();
                    studentToUpdate.setPhone(newPhone);
                    System.out.println("✓ Phone updated successfully!");
                    break;
                case 5:
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    studentToUpdate.setAddress(newAddress);
                    System.out.println("✓ Address updated successfully!");
                    break;
                case 6:
                    System.out.print("Enter new GPA: ");
                    int newGPA = scanner.nextInt();
                    studentToUpdate.updateGPA(newGPA);
                    break;
                case 7:
                    break;
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found!");
        }
    }
}
