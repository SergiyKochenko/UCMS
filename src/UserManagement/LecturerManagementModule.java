package UserManagement;

import java.util.ArrayList;

/**
 * LecturerManagementModule
 * ---------------------------------------------------------
 * Provides methods to manage lecturers within the UCMS system.
 * Responsibilities include:
 *  - Maintaining a list of all lecturers.
 *  - Providing a menu interface for Admins to manage lecturers.
 *  - Placeholder for adding, updating, and removing lecturers.
 */
public class LecturerManagementModule {

    /** Container to store all Lecturer objects in the system */
    public static ArrayList<Lecturer> lecturersList = new ArrayList<>();

    /**
     * Entry point for managing lecturers.
     * Currently a placeholder method; future implementation should:
     *  - Display a menu for admin actions (add, update, remove, view lecturers)
     *  - Allow admin to select and perform lecturer management operations
     *
     * @param admin the Admin object performing lecturer management
     */
    public static void manageLecturers(Admin admin) {
        // Placeholder output for initial implementation
        System.out.println("Welcome to Lecturer Management Module");

        // TODO:
        // 1. Display a formatted menu for lecturer management
        // 2. Implement methods to add, update, remove, and view lecturers
        // 3. Integrate lecturer assignments to courses/modules
    }
}
