package CourseManagement;

import java.util.ArrayList;
import java.util.Scanner;
import UserManagement.Admin;
import Utilities.Utility;

/**
 * CourseManagementModule
 * ---------------------------------------------------------
 * Provides methods to manage courses within the UCMS system.
 * Responsibilities include:
 *  - Adding new courses
 *  - Updating existing courses
 *  - Archiving courses
 *  - Displaying course information
 *  - Managing course modules
 *
 * This module relies on an Admin object to perform management operations,
 * ensuring that only authorized users can modify course data.
 */
public class CourseManagementModule {

    /** Container to hold all courses in the system. */
    public static ArrayList<Course> courseList = new ArrayList<>();

    /**
     * Displays the course management menu and routes the admin
     * to the selected operation.
     *
     * @param admin the Admin object performing course management
     */
    public static void courseManagement(Admin admin) {
        String[] menuOptions = {"Add Course", "Update Course", "Archive Course", "Back"};
        int choice = Utility.printMenu("Course Management Menu", menuOptions);

        switch (choice) {
            case 1:
                addNewCourse(admin);      // Add a new course
                break;
            case 2:
                updateCourse(admin);      // Update an existing course
                break;
            case 3:
                archiveCourse();          // Archive a course
                break;
            case 4:
                // Optionally return to previous menu
                break;
            default:
                System.out.println("Invalid selection. Try again.");
                courseManagement(admin);
        }
    }

    /**
     * Checks if there are any courses currently stored in the system.
     *
     * @return true if at least one course exists; false otherwise
     */
    public static boolean systemHasCourses() {
        return !courseList.isEmpty();
    }

    /**
     * Adds a module to a specific course.
     *
     * @param module the Module object to be added
     * @param course the Course object to which the module will be added
     */
    public static void addModuleToCourse(Module module, Course course) {
        course.addModule(module);
    }

    /**
     * Prompts the admin to add one or more new courses to the system.
     * Collects course name and course code for each entry.
     *
     * @param admin the Admin object responsible for adding courses
     */
    public static void addNewCourse(Admin admin) {
        Scanner input = new Scanner(System.in);
        boolean addAnotherCourse = true;
        String title = "ADD COURSE";
        int menuWidth = 60;

        do {
            // Display menu title using utility function
            Utility.printInputPromptMenu(title, menuWidth);

            System.out.print("Enter Course Name: ");
            String courseName = input.nextLine();

            System.out.print("Enter Course Code: ");
            String courseCode = input.nextLine();

            // Create a new Course object and add it via Admin's method
            admin.addCourse(new Course(courseCode, courseName), courseList);

            System.out.print("Do you want to add another course? Y/N: ");
            String answer = input.nextLine();

            // Continue adding courses if user enters 'Y'
            if (!answer.equalsIgnoreCase("Y")) {
                addAnotherCourse = false;
            }

            System.out.println(); // Add spacing between iterations
        } while (addAnotherCourse);

        // Return to the course management menu
        courseManagement(admin);
    }

    /**
     * Displays detailed information about a specific course.
     *
     * @param searchedCourse the Course object whose information is displayed
     */
    public static void displayCourseInfo(Course searchedCourse) {
        String title = " COURSE INFORMATION";
        int menuWidth = 60;

        // Print formatted course header
        System.out.println("*".repeat(menuWidth));
        int padding = (menuWidth - title.length()) / 2;
        System.out.printf("%" + padding + "s%s%n", "", title.toUpperCase());
        System.out.println("*".repeat(menuWidth));

        // Display key course details
        System.out.printf("%-20s : %s%n", "Course Code", searchedCourse.getCourseCode());
        System.out.printf("%-20s : %s%n", "Course Name", searchedCourse.getCourseName());
        System.out.printf("%-20s : %d%n", "Course Modules", searchedCourse.getModules().size());
        System.out.printf("%-20s : %d%n", "Enrollment", searchedCourse.getStudents().size());
        System.out.println("*".repeat(menuWidth));
    }

    /**
     * Updates information for an existing course.
     * Prompts admin to search for a course and choose which details to update.
     *
     * @param admin the Admin object performing the update
     */
    public static void updateCourse(Admin admin) {
        String title = "UPDATE COURSE";
        int menuWidth = 60;

        Utility.printInputPromptMenu(title, menuWidth);
        Scanner input = new Scanner(System.in);

        System.out.print("Enter course Code: ");
        String courseCode = input.nextLine();

        Course searchedCourse = searchCourse(courseCode);

        if (searchedCourse != null) {
            // Display course details before updating
            displayCourseInfo(searchedCourse);

            // Provide update options
            String[] menuOptions = {"Update Course Name", "Update Course Code", "Manage Modules", "Back"};
            int choice = Utility.printMenu("Course Update Menu", menuOptions);

            switch (choice) {
                case 1:
                    // TODO: Implement course name update
                    break;
                case 2:
                    // TODO: Implement course code update
                    break;
                case 3:
                    ModuleManagement.manageModules(admin, searchedCourse);
                    break;
                case 4:
                    courseManagement(admin); // Return to main course menu
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
                    updateCourse(admin);
            }
        } else {
            // Retry if course not found
            updateCourse(admin);
        }
    }

    /**
     * Searches for a course by its code.
     *
     * @param courseCode the code of the course to search for
     * @return the Course object if found; null otherwise
     */
    public static Course searchCourse(String courseCode) {
        Course courseFound = null;

        for (Course currentCourse : courseList) {
            if (courseCode.equalsIgnoreCase(currentCourse.getCourseCode())) {
                courseFound = currentCourse;
                break;
            }
        }

        if (courseFound == null) {
            System.out.println("Course " + courseCode + " not found. Try again!");
        }

        return courseFound;
    }

    /**
     * Archives a course in the system.
     * Placeholder for future implementation.
     */
    public static void archiveCourse() {
        // TODO: Implement archive functionality
    }
}
