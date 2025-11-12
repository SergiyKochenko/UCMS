package UserManagement;

import CourseManagement.Course;
import Interfaces.Assignable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lecturer
 * ---------------------------------------------------------
 * Represents a lecturer user in the UCMS system.
 * Responsibilities include:
 *  - Storing personal and departmental information
 *  - Maintaining a list of modules assigned to the lecturer
 *  - Assigning modules to the lecturer
 *  - Generating reports (placeholder implementation)
 */
public class Lecturer extends User implements Assignable<Course> {

    // ---------------------- FIELDS ----------------------

    /** Unique ID assigned to the lecturer */
    private String lecturerId;

    /** Department the lecturer belongs to */
    private String department;

    /** List of module names currently assigned to the lecturer */
    private List<String> modulesTaught;

    /** Course assigned to the lecturer */
    private Course assignedCourse;

    // ---------------------- CONSTRUCTORS ----------------------

    /** Default constructor */
    public Lecturer() {
        this.modulesTaught = new ArrayList<>();
    }

    /**
     * Constructs a Lecturer object with specified attributes.
     *
     * @param firstname  first name of the lecturer
     * @param lastname   last name of the lecturer
     * @param email      email address of the lecturer
     * @param password   login password
     * @param address    home or office address
     * @param phone      phone number
     * @param lecturerId unique lecturer ID
     * @param department department the lecturer belongs to
     */
    public Lecturer(String firstname, String lastname, String email, String password,
                    String address, String phone, String lecturerId, String department) {
        super(firstname, lastname, email, password, address, phone);
        this.lecturerId = lecturerId;
        this.department = department;
        this.modulesTaught = new ArrayList<>();
    }

    // ---------------------- GETTERS & SETTERS ----------------------

    public String getLecturerId() { return lecturerId; }
    public void setLecturerId(String lecturerId) { this.lecturerId = lecturerId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<String> getModulesTaught() { return modulesTaught; }

    public Course getAssignedCourse() { return assignedCourse; }
    public void setAssignedCourse(Course assignedCourse) { this.assignedCourse = assignedCourse; }

    // ---------------------- MODULE MANAGEMENT ----------------------

    /**
     * Assigns a module to this lecturer.
     *
     * @param moduleName name of the module to assign
     */
    public void assignModule(String moduleName) {
        modulesTaught.add(moduleName);
        System.out.println(getFirstname() + " is now assigned to teach " + moduleName);
    }

    /**
     * Displays all modules currently taught by this lecturer.
     */
    public void viewModules() {
        System.out.println("Modules taught by " + getFirstname() + ": " + modulesTaught);
    }

    // ---------------------- REPORTS ----------------------

    /**
     * Generates a report for the lecturer.
     * Currently a placeholder method; extend to generate meaningful reports.
     *
     * @param reportParameters array of parameters defining the report
     */
    @Override
    public void generateReport(String[] reportParameters) {
        // Empty implementation - Lecturer does not generate reports
    }

    // ---------------------- ASSIGNABLE INTERFACE METHODS ----------------------

    /**
     * Assigns this lecturer to a Course.
     *
     * @param course the course to assign this lecturer to
     */
    @Override
    public void assignTo(Course course) {
        if (course != null) {
            this.assignedCourse = course;
        } else {
            System.out.println("Cannot assign null course to lecturer.");
        }
    }

    /**
     * Returns the course assigned to this lecturer.
     *
     * @return assigned Course or null if unassigned
     */
    @Override
    public Course getAssignee() {
        return assignedCourse;
    }

    /**
     * Checks if this lecturer is currently assigned to a course.
     *
     * @return true if assigned, false otherwise
     */
    @Override
    public boolean isAssigned() {
        return assignedCourse != null;
    }

    /**
     * Removes the current course assignment from this lecturer.
     */
    @Override
    public void unassign() {
        if (isAssigned()) {
            this.assignedCourse = null;
        } else {
            System.out.println("Lecturer is not currently assigned to any course.");
        }
    }


    // ---------------------- TO STRING ----------------------

    @Override
    public String toString() {
        return "Lecturer: " + getFirstname() + " " + getLastname() + " (" + lecturerId + ")";
    }
}
