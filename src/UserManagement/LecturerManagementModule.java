package UserManagement;

import CourseManagement.Course;
import CourseManagement.CourseManagementModule;
import Utilities.Utility;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * LecturerManagementModule
 * ---------------------------------------------------------
 * Provides methods to manage lecturers within the UCMS system.
 * Responsibilities include:
 *  - Maintaining a list of all lecturers.
 *  - Providing a menu interface for Admins to manage lecturers.
 *  - Adding, updating, viewing, and removing lecturers.
 */
public class LecturerManagementModule {

    /** Container to store all Lecturer objects in the system */
    public static ArrayList<Lecturer> lecturersList = new ArrayList<>();

    /**
     * Entry point for managing lecturers.
     * Displays a menu for admin actions and routes to appropriate operations.
     *
     * @param admin the Admin object performing lecturer management
     */
    public static void manageLecturers(Admin admin) {
        String[] menuOptions = {"Add Lecturer", "View Lecturers", "Assign Lecturer to Course", 
                               "Remove Lecturer", "Back"};
        
        int choice = Utility.printMenu("Lecturer Management", menuOptions);
        
        switch (choice) {
            case 1:
                addLecturer(admin);
                break;
            case 2:
                viewLecturers(admin);
                break;
            case 3:
                assignLecturerToCourse(admin);
                break;
            case 4:
                removeLecturer(admin);
                break;
            case 5:
                // Return to previous menu
                break;
            default:
                System.out.println("Invalid selection. Try again.");
                manageLecturers(admin);
        }
    }
    
    /**
     * Adds one or more lecturers to the system.
     *
     * @param admin the Admin object adding lecturers
     */
    private static void addLecturer(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        Utility.printInputPromptMenu("Add Lecturer", 60);
        
        System.out.print("How many lecturers would you like to add?: ");
        int lecturerCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        for (int i = 1; i <= lecturerCount; i++) {
            String lecturerId = "L" + String.format("%03d", lecturersList.size() + 1);
            
            System.out.print("Enter First Name: ");
            String firstname = scanner.nextLine();
            
            System.out.print("Enter Last Name: ");
            String lastname = scanner.nextLine();
            
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();
            
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();
            
            Lecturer newLecturer = new Lecturer(firstname, lastname, email, "", 
                                               address, phone, lecturerId, department);
            admin.addUser(newLecturer, lecturersList);
            
            System.out.println("✓ Lecturer " + firstname + " " + lastname + " added successfully!");
            System.out.println("=".repeat(60));
        }
        
        manageLecturers(admin);
    }
    
    /**
     * Displays all lecturers in the system.
     *
     * @param admin the Admin object viewing lecturers
     */
    private static void viewLecturers(Admin admin) {
        Utility.printInputPromptMenu("View Lecturers", 60);
        
        if (lecturersList.isEmpty()) {
            System.out.println("No lecturers in the system.");
        } else {
            System.out.println("=".repeat(60));
            for (Lecturer lecturer : lecturersList) {
                System.out.println(lecturer);
                System.out.println("Department: " + lecturer.getDepartment());
                if (lecturer.isAssigned()) {
                    System.out.println("Assigned Course: " + lecturer.getAssignedCourse().getCourseName());
                } else {
                    System.out.println("Not assigned to any course");
                }
                System.out.println("-".repeat(60));
            }
        }
        
        System.out.println("\nPress Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        
        manageLecturers(admin);
    }
    
    /**
     * Assigns a lecturer to a course.
     *
     * @param admin the Admin object performing the assignment
     */
    private static void assignLecturerToCourse(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        Utility.printInputPromptMenu("Assign Lecturer to Course", 60);
        
        System.out.print("Enter Lecturer ID: ");
        String lecturerId = scanner.nextLine();
        
        Lecturer lecturer = findLecturerById(lecturerId);
        
        if (lecturer != null) {
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            
            Course course = CourseManagementModule.searchCourse(courseCode);
            
            if (course != null) {
                lecturer.assignTo(course);
                System.out.println("✓ " + lecturer.getFirstname() + " " + lecturer.getLastname() + 
                                 " assigned to " + course.getCourseName());
            } else {
                System.out.println("Course not found!");
            }
        } else {
            System.out.println("Lecturer not found!");
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
        
        manageLecturers(admin);
    }
    
    /**
     * Removes a lecturer from the system.
     *
     * @param admin the Admin object removing the lecturer
     */
    private static void removeLecturer(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        Utility.printInputPromptMenu("Remove Lecturer", 60);
        
        System.out.print("Enter Lecturer ID: ");
        String lecturerId = scanner.nextLine();
        
        Lecturer lecturer = findLecturerById(lecturerId);
        
        if (lecturer != null) {
            System.out.print("Are you sure you want to remove " + lecturer.getFirstname() + 
                           " " + lecturer.getLastname() + "? (Y/N): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("Y")) {
                lecturersList.remove(lecturer);
                System.out.println("✓ Lecturer removed successfully!");
            } else {
                System.out.println("Remove operation cancelled.");
            }
        } else {
            System.out.println("Lecturer not found!");
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
        
        manageLecturers(admin);
    }
    
    /**
     * Finds a lecturer by their ID.
     *
     * @param lecturerId the ID to search for
     * @return the Lecturer object if found, null otherwise
     */
    private static Lecturer findLecturerById(String lecturerId) {
        for (Lecturer lecturer : lecturersList) {
            if (lecturer.getLecturerId().equals(lecturerId)) {
                return lecturer;
            }
        }
        return null;
    }
}
