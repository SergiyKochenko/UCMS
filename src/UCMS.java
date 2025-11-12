import CourseManagement.CourseManagementModule;
import UserManagement.*;
import Utilities.Utility;

import java.util.Scanner;

/**
 * UCMS (University Course Management System)
 * ---------------------------------------------------------
 * This class represents the UCMS application.
 * It provides a command-line interface (CLI) for users to select their roles
 * (Admin, Lecturer, or Student) and access corresponding features.
 *
 * Responsibilities:
 *  - Display role-based menus
 *  - Handle login for Admins and other users
 *  - Delegate management operations to respective modules
 *  - Generate simple system reports
 *
 * Note: For demonstration purposes, the login process is simulated
 * without password validation or persistent storage.
 */
public class UCMS {

    /** Reference to the currently logged-in Admin object. */
    private static Admin admin;

    /**
     * Determines the user role (Admin, Lecturer, or Student)
     * and directs them to the appropriate menu interface.
     */
    public static void determineRole() {
        String[] roles = {"Admin", "Lecturer", "Student", "Exit"};
        boolean running = true;

        while (running) {
            int choice = Utility.printMenu("Pick Your Role", roles);
            switch (choice) {
                case 1:
                    showAdminOptions();
                    break;
                case 2:
                    showLecturerOptions();
                    break;
                case 3:
                    showStudentOptions();
                    break;
                case 4:
                    System.out.println("Exiting UCMS. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
                    break;
            }
        }
    }

    /**
     * Simulates admin login by prompting for first and last name.
     * In a real system, credentials would be validated against a database.
     *
     * @return true if login succeeds (always true in this demo)
     */
    public static boolean login() {
        Utility.printInputPromptMenu("Admin Login", 60);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String adminFirstname = sc.nextLine();
        //
        System.out.print("Enter Last Name: ");
        String adminLastname = sc.nextLine();

        System.out.print("Enter Email Address: ");
        String adminEmail = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String adminPhoneNumber = sc.nextLine();

        System.out.print("Enter Password: ");
        String adminPassword = sc.nextLine();

        // Create a new Admin object for the current session
        admin = new Admin(adminFirstname, adminLastname, adminEmail, adminPassword, "", adminPhoneNumber);

        return true;
    }

    /**
     * Displays the Admin main menu and allows the user to perform
     * administrative operations such as managing courses, students,
     * lecturers, or generating reports.
     */
    public static void showAdminOptions() {
        String[] menuOptions = {"Manage Courses", "Manage Students", "Manage Lecturers", "Reports", "Exit"};

        // Perform simulated login (replace with actual authentication if implemented)
        boolean loggedIn = false;
        while (!loggedIn) {
            loggedIn = login();
        }

        int choice = Utility.printMenu("UCMS Admin Main Menu", menuOptions);

        switch (choice) {
            case 1:
                CourseManagementModule.courseManagement(admin);
                break;
            case 2:
                StudentManagementModule.manageStudents(admin);
                break;
            case 3:
                LecturerManagementModule.manageLecturers(admin);
                break;
            case 4:
                showReportOptions();
                break;
            case 5:
                System.exit(0);
        }
    }

    /**
     * Displays the Student main menu options.
     * Placeholder method for future functionality (e.g., viewing enrolled modules).
     */
    public static void showStudentOptions() {
        String[] menuOptions = {"View Modules", "Manage Assessments", "Exit"};
        int choice = Utility.printMenu("UCMS Student Main Menu", menuOptions);
        // TODO: Implement actual student menu logic
    }

    /**
     * Displays the Lecturer main menu options.
     * Placeholder method for lecturer-specific functionality.
     */
    public static void showLecturerOptions() {
        Lecturer lecturer = new Lecturer();
        // TODO: Implement lecturer-specific menu logic
    }

    /**
     * Displays reporting options and generates a simple system summary.
     * Currently uses static counts for demonstration purposes.
     */
    public static void showReportOptions() {
        String[] menuOptions = {"Generate Report", "Exit"};
        int choice = Utility.printMenu("Reports Menu", menuOptions);

        switch (choice) {
            case 1:
                Utility.printInputPromptMenu("SIMPLE REPORT", 60);

                /* === Invoke Admin's report generation method ===
                 * getReportParameters() returns report data.
                 * */
                admin.generateReport(getReportParameters());

                System.out.println("Press Enter to continue...");
                new Scanner(System.in).nextLine();

                // Return to Admin menu after generating report
                showAdminOptions();
                break;

            case 2:
                System.exit(0);
        }
    }

    /**
     * Collects and prepares the necessary parameters for generating a system report.
     * <p>
     * This method gathers key statistics such as the number of students, courses,
     * and lecturers in the system, and includes the name of the currently logged-in
     * admin. The returned array of strings is formatted to be passed directly to
     * the {@link Admin#generateReport(String[])} method.
     * </p>
     *
     * @return an array of strings containing, in order:
     *         <ol>
     *           <li>Number of courses</li>
     *           <li>Number of students</li>
     *           <li>Number of lecturers</li>
     *           <li>Admin full name (first + last)</li>
     *         </ol>
     */
    private static String[] getReportParameters() {

        // Static sample data for demonstration purposes
        int numStudents = StudentManagementModule.studentsContainer.size();
        // Retrieve actual dynamic data from the respective modules
        int numCourses = CourseManagementModule.courseList.size();
        int numLecturers = LecturerManagementModule.lecturersList.size();

        // Construct and return a string array containing report parameters
        // This array matches the expected input for Admin's generateReport method inherited from the user class
        return new String[]{
                String.valueOf(numCourses),                 // Total number of courses
                String.valueOf(numStudents),                // Total number of students
                String.valueOf(numLecturers),               // Total number of lecturers
                admin.getFirstname() + " " + admin.getLastname()  // Admin full name
        };
    }

}
