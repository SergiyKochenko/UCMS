package UserManagement;

import CourseManagement.Course;
import Interfaces.Authenticatable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Student
 * ---------------------------------------------------------
 * Represents a student user in the UCMS system.
 * Responsibilities include:
 *  - Storing personal and academic information
 *  - Viewing and updating profile
 *  - Managing GPA and enrolled courses
 *  - Authenticating login/logout
 *  - Generating student reports
 *
 * Each Student object is assigned a unique student ID and optionally linked to a Course.
 */
public class Student extends User {

    /** Unique ID assigned to the student */
    private String studentId;

    /** Course the student is enrolled in */
    private Course course;

    /** Student's Grade Point Average (GPA) */
    private int gpa;

    /** Tracks the total number of Student objects created */
    private static int studentCount;

    // ---------------------- CONSTRUCTORS ----------------------

    /** Default constructor */
    public Student() {}

    /**
     * Creates a new Student object with personal and system attributes.
     *
     * @param firstname student's first name
     * @param lastname student's last name
     * @param email student's email
     * @param password student's password
     * @param address student's address
     * @param phone student's phone number
     * @param studentId unique student ID
     */
    public Student(String firstname, String lastname, String email, String password,
                   String address, String phone, String studentId, String DoB) {
        super(firstname, lastname, email, password, address, phone);
        this.studentId = studentId;
        this.setDateOfBirth(DoB);
        this.course = null;   // Initially not enrolled in a course
        this.gpa = 4;         // Default GPA

        studentCount++;       // Increment student count on object creation
    }

    // ---------------------- STATIC METHODS ----------------------

    /** Returns the number of Student objects created */
    public static int getStudentCount() {
        return studentCount;
    }

    // ---------------------- STUDENT-SPECIFIC METHODS ----------------------

    /**
     * Generates a simple report about the student.
     */

    /**
     * Updates student information.
     * @param user User object containing updated information
     */
    public void updateStudent(User user) {
        if (user != null) {
            this.setFirstname(user.getFirstname());
            this.setLastname(user.getLastname());
            this.setEmail(user.getEmail());
            this.setPhone(user.getPhone());
            this.setAddress(user.getAddress());
            System.out.println("✓ Student information updated successfully!");
        }
    }

    /**
     * Updates GPA of the student with validation.
     * @param gpa new GPA value
     * @throws ArithmeticException if GPA is less than 0
     */
    public void updateGPA(int gpa) throws ArithmeticException {
        if (gpa < 0) throw new ArithmeticException("GPA cannot be negative");
        this.gpa = gpa;
        System.out.println("Updated GPA: " + this.gpa);
    }

    /**
     * Enrolls the student in a course.
     * @param courseName name of the course
     */
    public void enrollCourse(String courseName) {
        System.out.println(getFirstname() + " has enrolled in " + courseName);
    }

    /**
     * Displays student's profile including profile picture.
     * @throws IOException if profile picture file is not found
     */
    public void displayProfile() throws IOException {
        System.out.println("Student ID: " + this.getStudentId());
        System.out.println("Name: " + this.getFirstname() + " " + this.getLastname());

        String picturePath = "C://Uploads/lusungu.jpg";
        File pictureFile = new File(picturePath);

        if (!pictureFile.exists()) {
            throw new IOException("Profile picture not found: " + picturePath);
        }

        // Simulate loading picture
        FileInputStream fis = new FileInputStream(pictureFile);
        System.out.println("Profile picture loaded successfully from " + picturePath);
        fis.close();
    }

    /**
     * Displays the current GPA of the student.
     */
    public void viewResults() {
        System.out.println(getFirstname() + " has a GPA of " + gpa);
    }

    // ---------------------- GETTERS & SETTERS ----------------------

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public int getGpa() { return gpa; }
    public void setGpa(int gpa) { this.gpa = gpa; }

    // ---------------------- AUTHENTICATABLE METHODS ----------------------

//    @Override
//    public boolean login(String username, String password) {
//        System.out.println("Logging in with Username: " + username + ", Password: " + password);
//        return true;
//    }

//    @Override
//    public boolean logout() {
//        System.out.println(getFirstname() + " has logged out successfully.");
//        return true;
//    }

    // ---------------------- OVERRIDE ABSTRACT METHOD ----------------------

    /**
     * Generates a system report for the student.
     * @param reportParameters additional parameters for the report (currently unused)
     */
    @Override
    public void generateReport(String[] reportParameters) {
        // Empty implementation - Student does not generate reports
    }

    // ---------------------- TO STRING ----------------------

    @Override
    public String toString() {
        return "Student: " + getFirstname() + " " + getLastname() + " (" + studentId + ")";
    }
}
