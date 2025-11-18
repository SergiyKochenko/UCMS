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
     * @param admin              the Admin or User object managing modules
     * @param courseTobeManaged  the Course object whose modules are being managed
     */
    public static void manageModules(User admin, Course courseTobeManaged) {
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
                updateModule(admin, courseTobeManaged); // Update module
                break;
            case 4:
                archiveModule(admin, courseTobeManaged); // Archive module
                break;
            case 5:
                if (admin instanceof Admin) {
                    CourseManagementModule.courseManagement((Admin) admin); // Navigate back to course management
                }
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
     * @param admin  the Admin or User object performing the operation
     * @param course the Course object whose modules are displayed
     */
    public static void showModules(User admin, Course course) {
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
     * @param admin  the Admin or User object adding modules
     * @param course the Course object to which modules are being added
     */
    public static void addModule(User admin, Course course) {
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
            if (admin instanceof Admin) {
                ((Admin) admin).addModule(module, course, moduleList);
            } else {
                moduleList.add(module);
                course.addModule(module);
            }

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
     * Allows the admin to modify module details such as name, code, level, semester, and credits.
     *
     * @param admin  the User object performing the update (Admin or Lecturer)
     * @param course the Course object whose module is being updated
     */
    public static void updateModule(User admin, Course course) {
        Scanner input = new Scanner(System.in);
        String title = "UPDATE MODULE";
        int menuWidth = 60;
        
        Utility.printInputPromptMenu(title, menuWidth);
        
        System.out.print("Enter module code to update: ");
        String moduleCode = input.nextLine();
        
        Module moduleToUpdate = null;
        for (Module module : moduleList) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                moduleToUpdate = module;
                break;
            }
        }
        
        if (moduleToUpdate != null) {
            System.out.println("Current Module: " + moduleToUpdate.getModuleName());
            
            String[] updateOptions = {"Update Module Name", "Update Module Code", "Update Level", 
                                     "Update Semester", "Update Credits", "Back"};
            int choice = Utility.printMenu("Update Module Menu", updateOptions);
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new module name: ");
                    String newName = input.nextLine();
                    moduleToUpdate.setModuleName(newName);
                    System.out.println("✓ Module name updated successfully!");
                    break;
                case 2:
                    System.out.print("Enter new module code: ");
                    String newCode = input.nextLine();
                    moduleToUpdate.setModuleCode(newCode);
                    System.out.println("✓ Module code updated successfully!");
                    break;
                case 3:
                    System.out.print("Enter new level: ");
                    int newLevel = input.nextInt();
                    moduleToUpdate.setLevel(newLevel);
                    System.out.println("✓ Module level updated successfully!");
                    break;
                case 4:
                    System.out.print("Enter new semester: ");
                    int newSemester = input.nextInt();
                    moduleToUpdate.setSemester(newSemester);
                    System.out.println("✓ Module semester updated successfully!");
                    break;
                case 5:
                    System.out.print("Enter new credits: ");
                    int newCredits = input.nextInt();
                    moduleToUpdate.setCredits(newCredits);
                    System.out.println("✓ Module credits updated successfully!");
                    break;
                case 6:
                    break;
            }
        } else {
            System.out.println("Module not found!");
        }
        
        manageModules(admin, course);
    }
    
    /**
     * Archives a module by removing it from the course and module list.
     *
     * @param admin  the Admin object performing the archive
     * @param course the Course object from which the module is being archived
     */
    public static void archiveModule(User admin, Course course) {
        Scanner input = new Scanner(System.in);
        String title = "ARCHIVE MODULE";
        int menuWidth = 60;
        
        Utility.printInputPromptMenu(title, menuWidth);
        
        System.out.print("Enter module code to archive: ");
        String moduleCode = input.nextLine();
        
        Module moduleToArchive = null;
        for (Module module : moduleList) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                moduleToArchive = module;
                break;
            }
        }
        
        if (moduleToArchive != null) {
            System.out.print("Are you sure you want to archive module " + moduleToArchive.getModuleName() + "? (Y/N): ");
            String confirmation = input.nextLine();
            
            if (confirmation.equalsIgnoreCase("Y")) {
                moduleList.remove(moduleToArchive);
                course.getModules().remove(moduleToArchive);
                System.out.println("✓ Module archived successfully!");
            } else {
                System.out.println("Archive cancelled.");
            }
        } else {
            System.out.println("Module not found!");
        }
        
        manageModules(admin, course);
    }
}
