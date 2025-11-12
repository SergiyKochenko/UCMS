package CourseManagement;

import UserManagement.Admin;
import UserManagement.User;
import Utilities.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ModuleManagement
 * ---------------------------------------------------------
 * Provides functionality to manage modules within a course in the UCMS system.
 * Responsibilities include:
 *  - Adding new modules
 *  - Displaying module information
 *  - Updating modules (placeholder for future implementation)
 *  - Archiving modules (future implementation)
 *
 * Modules are stored in a dynamic ArrayList and linked to their parent Course.
 */
public class ModuleManagement {

    /** Container to store all modules dynamically */
    private static ArrayList<Module> moduleList = new ArrayList<>();

    /**
     * Displays the module management menu and routes the admin
     * to the selected operation for the specified course.
     *
     * @param admin              the Admin object managing modules
     * @param courseTobeManaged  the Course object whose modules are being managed
     */
    public static void manageModules(Admin admin, Course courseTobeManaged) {
        String[] menuOptions = {"Add Module", "Show Modules", "Update Module", "Archive Module", "Back to Course Management"};

        int choice = Utility.printMenu("Module Management Menu", menuOptions);

        switch (choice) {
            case 1:
                addModule(admin, courseTobeManaged);  // Add new module
                break;
            case 2:
                showModules(admin, courseTobeManaged); // Display all modules
                break;
            case 3:
                updateModule(admin, courseTobeManaged); // Update module (placeholder)
                break;
            case 4:
                // TODO: Implement archive module functionality
                break;
            case 5:
                // TODO: Navigate back to course management menu
                break;
            default:
                System.out.println("Invalid selection. Try again.");
                manageModules(admin, courseTobeManaged);
        }
    }

    /**
     * Displays all modules for a specific course with detailed information.
     * If the module outline file exists, it prints its path (can be extended to read content).
     *
     * @param admin  the Admin object performing the operation
     * @param course the Course object whose modules are displayed
     */
    public static void showModules(Admin admin, Course course) {
        String title = "MODULES INFORMATION";
        int menuWidth = 60;
        Utility.printInputPromptMenu(title, menuWidth);

        for (Module module : moduleList) {
            System.out.println("Module Name: " + module.getModuleName());
            System.out.println("Module Code: " + module.getModuleCode());

            // Check if module outline file exists
            File outlineFile = module.getModuleOutline();
            if (outlineFile != null && outlineFile.exists()) {
                System.out.println("Module Spec: " + outlineFile.getPath());
            } else {
                System.out.println("Module File Not Found");
            }
        }

        // Wait for user to press Enter before returning to menu
        System.out.println("Press any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // Return to module management menu
        manageModules(admin, course);
    }

    /**
     * Adds one or more modules to a course.
     * Prompts admin to input module details such as code, title, level, semester, credits, and outline file.
     *
     * @param admin  the Admin object adding modules
     * @param course the Course object to which modules are being added
     */
    public static void addModule(Admin admin, Course course) {
        Scanner input = new Scanner(System.in);
        boolean hasModuleToAdd = true;
        String title = "ADD MODULE";
        int menuWidth = 60;

        do {
            // Display input prompt
            Utility.printInputPromptMenu(title, menuWidth);

            System.out.print("Enter module code: ");
            String moduleCode = input.nextLine();

            System.out.print("Enter module title: ");
            String moduleTitle = input.nextLine();

            System.out.print("Enter module level: ");
            int moduleLevel = input.nextInt();

            System.out.print("Enter module semester: ");
            int moduleSemester = input.nextInt();
            input.nextLine(); // Consume leftover newline

            System.out.print("Enter path to Module outline file: ");
            String moduleOutline = input.nextLine();

            System.out.print("Enter credits: ");
            int moduleCredits = input.nextInt();
            input.nextLine(); // Consume leftover newline

            // Create new Module object
            Module module = new Module(moduleCode, moduleTitle, course.getCourseCode(),
                    moduleLevel, moduleSemester, moduleCredits);

            module.setModuleOutline(moduleOutline);

            // Add module to data structure via Admin
            admin.addModule(module, course, moduleList);

            // Prompt if admin wants to add another module
            System.out.print("Do you want to add another module? Y/N: ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("N")) {
                hasModuleToAdd = false;
            }

        } while (hasModuleToAdd);

        // Return to module management menu
        manageModules(admin, course);
    }

    /**
     * Updates an existing module.
     * Currently a placeholder method; future implementation should allow
     * the admin to modify module details.
     *
     * @param admin  the User object performing the update (Admin or Lecturer)
     * @param course the Course object whose module is being updated
     */
    public static void updateModule(User admin, Course course) {
        // TODO: Implement update functionality for modules
    }
}
