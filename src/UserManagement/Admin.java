package UserManagement;

import CourseManagement.Course;
import CourseManagement.Module;
import Utilities.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Admin
 * ---------------------------------------------------------
 * Represents an administrator in the UCMS system.
 * Responsibilities include:
 *  - Managing users (students, lecturers, other admins)
 *  - Managing courses and modules
 *  - Generating system-wide reports
 */
public class Admin extends User {

    // ---------------------- CONSTRUCTORS ----------------------

    /** Default constructor */
    public Admin() {}


    /**
     * Constructs an {@code Admin} object by initializing all inherited user attributes.
     * <p>
     * This constructor delegates initialization of shared user details
     * (such as name, contact information, and credentials)
     * to the superclass {@link User}.
     * </p>
     *
     * @param firstname the administrator’s first name
     * @param lastname  the administrator’s last name
     * @param email     the administrator’s email address (used for login or contact)
     * @param password  the administrator’s account password
     * @param address   the administrator’s residential or work address
     * @param phone     the administrator’s contact phone number
     */
    public Admin(String firstname, String lastname, String email, String password, String address, String phone) {
        // Call the parent class (User) constructor to initialize common user fields
        super(firstname, lastname, email, password, address, phone);
    }

    // ---------------------- USER MANAGEMENT ----------------------

    /**
     * Adds a user to a given user list.
     * Demonstrates polymorphism using generics.
     *
     * @param user            the user to add
     * @param arrayListObject the list where the user should be added
     * @param <T>             type of user (Student, Lecturer, etc.)
     */
    public <T extends User> void addUser(T user, ArrayList<T> arrayListObject) {
        arrayListObject.add(user);
    }

    /**
     * Archives a user by marking them as inactive or removing from active list.
     * Demonstrates polymorphism using generics.
     *
     * @param user the user to archive
     */
    public void archiveUser(User user) {
        System.out.println("User " + user.getFirstname() + " " + user.getLastname() + " has been archived.");
        // In a real system, this would mark the user as inactive in a database
        // or move them to an archived users list
    }

    /**
     * Updates a user's information.
     * Demonstrates polymorphism - can update any User subclass.
     *
     * @param user the user to update
     */
    public void updateUser(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Updating user: " + user.getFirstname() + " " + user.getLastname());
        
        String[] updateOptions = {"Update Email", "Update Phone", "Update Address", "Back"};
        int choice = Utility.printMenu("Update User Menu", updateOptions);
        
        switch (choice) {
            case 1:
                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();
                user.setEmail(newEmail);
                System.out.println("✓ Email updated successfully!");
                break;
            case 2:
                System.out.print("Enter new phone: ");
                String newPhone = scanner.nextLine();
                user.setPhone(newPhone);
                System.out.println("✓ Phone updated successfully!");
                break;
            case 3:
                System.out.print("Enter new address: ");
                String newAddress = scanner.nextLine();
                user.setAddress(newAddress);
                System.out.println("✓ Address updated successfully!");
                break;
            case 4:
                break;
        }
    }

    // ---------------------- COURSE MANAGEMENT ----------------------

    /**
     * Adds a course to the system.
     *
     * @param course     the course to add
     * @param courseList the list of courses
     */
    public void addCourse(Course course, ArrayList<Course> courseList) {
        courseList.add(course);
    }

    /**
     * Updates a course in the system.
     *
     * @param course     the course to update
     * @param courseList the list of courses
     */
    public void updateCourse(Course course, ArrayList<Course> courseList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Updating course: " + course.getCourseName());
        
        String[] updateOptions = {"Update Course Name", "Update Course Code", "Back"};
        int choice = Utility.printMenu("Update Course Menu", updateOptions);
        
        switch (choice) {
            case 1:
                System.out.print("Enter new course name: ");
                String newName = scanner.nextLine();
                course.setCourseName(newName);
                System.out.println("✓ Course name updated successfully!");
                break;
            case 2:
                System.out.print("Enter new course code: ");
                String newCode = scanner.nextLine();
                course.setCourseCode(newCode);
                System.out.println("✓ Course code updated successfully!");
                break;
            case 3:
                break;
        }
    }

    /**
     * Retrieves all courses.
     * Currently a placeholder method.
     */
    public void getCourses() {
        // TODO: implement retrieval of courses
    }

    // ---------------------- MODULE MANAGEMENT ----------------------

    /**
     * Adds a module to a course and system-wide module list.
     *
     * @param module      the module to add
     * @param course      the course to which the module belongs
     * @param modulesList the list of all modules
     */
    public void addModule(Module module, Course course, ArrayList<Module> modulesList) {
        modulesList.add(module);
        course.addModule(module);
    }

    // ---------------------- REPORTS ----------------------
    /**
     * Generates a formatted system report.
     *
     * reportParameters array of report parameters:
     *                         [0] = total courses
     *                         [1] = total students
     *                         [2] = total lecturers
     *                         [3] = report generated by
     */
    @Override
    public void generateReport(String[] reportParameters) {
        // Print header
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Center the title
        String title = "UCMS SYSTEM REPORT";
        int padding = (60 - title.length()) / 2;
        System.out.printf("%" + padding + "s%s%n", "", title);
        System.out.println();
        System.out.println("=".repeat(60));
        
        // Display report data
        System.out.printf("Total Number of Courses           : %s%n", reportParameters[0]);
        System.out.printf("Total Number of Students Enrolled : %s%n", reportParameters[1]);
        System.out.printf("Total Number of Lecturers         : %s%n", reportParameters[2]);
        
        System.out.println("-".repeat(60));
        
        // Display admin name who generated the report
        System.out.printf("Report generated by: %s%n", reportParameters[3]);
        
        // Display current date
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        System.out.printf("Date: %s%n", now.format(formatter));
        
        System.out.println("=".repeat(60));
        System.out.println();
        System.out.println("Press any key to continue...");
    }
}
