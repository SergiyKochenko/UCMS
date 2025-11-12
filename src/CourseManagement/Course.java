package CourseManagement;

import UserManagement.Student;
import java.io.File;
import java.util.ArrayList;

/**
 * Course
 * ---------------------------------------------------------
 * Represents a university course in the UCMS system.
 * A course consists of:
 *  - Multiple modules
 *  - Enrolled students
 *  - Optional course descriptor file
 */
public class Course {

    // ---------------------- FIELDS ----------------------

    /** Unique course code (e.g., CS) */
    private String courseCode;

    /** Name of the course (e.g., Computer Science) */
    private String courseName;

    /** List of modules in this course */
    private ArrayList<Module> modules;

    /** List of students enrolled in this course */
    private ArrayList<Student> students;

    /** Optional file path to module descriptor */
    private File moduleDescriptor;

    // ---------------------- CONSTRUCTORS ----------------------

    /**
     * Constructs a Course object with specified code and name.
     *
     * @param courseCode unique code for the course
     * @param courseName name of the course
     */
    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.modules = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // ---------------------- GETTERS & SETTERS ----------------------

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public ArrayList<Module> getModules() { return modules; }
    public ArrayList<Student> getStudents() { return students; }

    public File getModuleDescriptor() { return moduleDescriptor; }
    public void setModuleDescriptor(File moduleDescriptor) { this.moduleDescriptor = moduleDescriptor; }

    // ---------------------- COURSE OPERATIONS ----------------------

    /**
     * Adds a module to the course.
     *
     * @param module the module to add
     */
    public void addModule(Module module) {
        modules.add(module);
        System.out.println(module.getModuleName() + " has been added to " + courseName);
    }

    /**
     * Enrolls a student in the course.
     *
     * @param student the student to enroll
     */
    public void enrollStudent(Student student) {
        students.add(student);
        System.out.println(student.getFirstname() + " " + student.getLastname() +
                " has been enrolled in " + courseName);
    }

    /**
     * Displays all modules in the course.
     */
    public void viewModules() {
        System.out.println("Modules for " + courseName + ":");
        for (Module module : modules) {
            System.out.println(module);
        }
    }

    /**
     * Displays all students enrolled in the course.
     */
    public void viewStudents() {
        System.out.println("Students enrolled in " + courseName + ":");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * Displays basic course information including course code, name,
     * number of modules, and number of enrolled students.
     */
    public void showCourseInfo() {
        System.out.println(this);
    }

    // ---------------------- TO STRING ----------------------

    @Override
    public String toString() {
        return String.format("Course Code: %s | Course Name: %s | Modules: %d | Students: %d",
                courseCode, courseName, modules.size(), students.size());
    }
}
