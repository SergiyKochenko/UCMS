import java.util.Scanner;

public class UIHelper {
    // ANSI Color Codes
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // Bright colors
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_MAGENTA = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    
    // Background colors
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_CYAN = "\u001B[46m";
    
    // Text styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    
    private static final String SEPARATOR = "═══════════════════════════════════════════════════════════════";
    private static final String LINE = "───────────────────────────────────────────────────────────────";
    private static final String DOUBLE_LINE = "═══════════════════════════════════════════════════════════════";
    
    public static void printBanner() {
        System.out.println(BRIGHT_CYAN + BOLD);
        System.out.println("  ╔══════════════════════════════════════════════════════════════╗");
        System.out.println("  ║                                                              ║");
        System.out.println("  ║            " + BRIGHT_YELLOW + "██╗   ██╗ ██████╗███╗   ███╗███████╗" + BRIGHT_CYAN + "              ║");
        System.out.println("  ║            " + BRIGHT_YELLOW + "██║   ██║██╔════╝████╗ ████║██╔════╝" + BRIGHT_CYAN + "              ║");
        System.out.println("  ║            " + BRIGHT_YELLOW + "██║   ██║██║     ██╔████╔██║███████╗" + BRIGHT_CYAN + "              ║");
        System.out.println("  ║            " + BRIGHT_YELLOW + "██║   ██║██║     ██║╚██╔╝██║╚════██║" + BRIGHT_CYAN + "              ║");
        System.out.println("  ║            " + BRIGHT_YELLOW + "╚██████╔╝╚██████╗██║ ╚═╝ ██║███████║" + BRIGHT_CYAN + "              ║");
        System.out.println("  ║            " + BRIGHT_YELLOW + " ╚═════╝  ╚═════╝╚═╝     ╚═╝╚══════╝" + BRIGHT_CYAN + "              ║");
        System.out.println("  ║                                                              ║");
        System.out.println("  ║             " + BRIGHT_WHITE + "University Course Management System" + BRIGHT_CYAN + "              ║");
        System.out.println("  ║                                                              ║");
        System.out.println("  ╚══════════════════════════════════════════════════════════════╝");
        System.out.println(RESET);
    }
    
    public static void printHeader(String title) {
        System.out.println("\n" + BRIGHT_CYAN + BOLD + SEPARATOR + RESET);
        System.out.println(BRIGHT_YELLOW + BOLD + "  📚 " + title + RESET);
        System.out.println(BRIGHT_CYAN + SEPARATOR + RESET);
    }
    
    public static void printSubHeader(String subtitle) {
        System.out.println(BRIGHT_MAGENTA + "\n  ➤ " + subtitle + RESET);
        System.out.println(BRIGHT_BLACK + LINE + RESET);
    }
    
    public static void printMenu(String... options) {
        System.out.println();
        for (int i = 0; i < options.length; i++) {
            String icon = getMenuIcon(i);
            System.out.println(BRIGHT_WHITE + "  " + BRIGHT_CYAN + BOLD + "[" + (i + 1) + "]" + RESET + 
                             BRIGHT_WHITE + " " + icon + " " + options[i] + RESET);
        }
        System.out.println(BRIGHT_BLACK + LINE + RESET);
    }
    
    private static String getMenuIcon(int index) {
        String[] icons = {"🎓", "👨‍🏫", "📖", "✏️", "📊", "🔍", "⚙️", "🚪", "💾", "🗑️", "📝", "👤"};
        return index < icons.length ? icons[index] : "•";
    }
    
    public static int getMenuChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_YELLOW + "  ▶ Enter your choice (" + min + "-" + max + "): " + RESET);
            try {
                String input = scanner.nextLine().trim();
                
                // Check for empty input
                if (input.isEmpty()) {
                    printError("Input cannot be empty!");
                    continue;
                }
                
                // Try to parse the input
                choice = Integer.parseInt(input);
                
                // Validate range
                if (choice < min || choice > max) {
                    printError("Invalid choice! Please select between " + min + " and " + max + ".");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                printError("Please enter a valid number!");
            } catch (Exception e) {
                printError("Error reading input. Please try again.");
            }
        }
        return choice;
    }
    
    public static String getInput(Scanner scanner, String prompt) {
        String input = "";
        while (input.isEmpty()) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                printError("Input cannot be empty! Please try again.");
            }
        }
        return input;
    }
    
    public static String getInputAllowEmpty(Scanner scanner, String prompt) {
        System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
        return scanner.nextLine().trim();
    }
    
    public static int getIntInput(Scanner scanner, String prompt) {
        int value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            try {
                String input = scanner.nextLine().trim();
                
                // Check for empty input
                if (input.isEmpty()) {
                    printError("Input cannot be empty!");
                    continue;
                }
                
                value = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                printError("Please enter a valid integer number!");
            } catch (Exception e) {
                printError("Error reading input. Please try again.");
            }
        }
        return value;
    }
    
    public static int getPositiveIntInput(Scanner scanner, String prompt) {
        int value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            try {
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    printError("Input cannot be empty!");
                    continue;
                }
                
                value = Integer.parseInt(input);
                
                if (value <= 0) {
                    printError("Please enter a positive number!");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                printError("Please enter a valid integer number!");
            } catch (Exception e) {
                printError("Error reading input. Please try again.");
            }
        }
        return value;
    }
    
    public static int getIntInputInRange(Scanner scanner, String prompt, int min, int max) {
        int value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + " (" + min + "-" + max + "): " + RESET);
            try {
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    printError("Input cannot be empty!");
                    continue;
                }
                
                value = Integer.parseInt(input);
                
                if (value < min || value > max) {
                    printError("Please enter a number between " + min + " and " + max + "!");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                printError("Please enter a valid integer number!");
            } catch (Exception e) {
                printError("Error reading input. Please try again.");
            }
        }
        return value;
    }
    
    public static double getDoubleInput(Scanner scanner, String prompt) {
        double value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            try {
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    printError("Input cannot be empty!");
                    continue;
                }
                
                value = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                printError("Please enter a valid number!");
            } catch (Exception e) {
                printError("Error reading input. Please try again.");
            }
        }
        return value;
    }
    
    public static double getDoubleInputInRange(Scanner scanner, String prompt, double min, double max) {
        double value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + " (" + min + "-" + max + "): " + RESET);
            try {
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    printError("Input cannot be empty!");
                    continue;
                }
                
                value = Double.parseDouble(input);
                
                if (value < min || value > max) {
                    printError("Please enter a number between " + min + " and " + max + "!");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                printError("Please enter a valid number!");
            } catch (Exception e) {
                printError("Error reading input. Please try again.");
            }
        }
        return value;
    }
    
    public static String getValidEmail(Scanner scanner, String prompt) {
        String email = "";
        boolean validInput = false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-ZaZ0-9.-]+\\.[A-Za-z]{2,}$";
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            email = scanner.nextLine().trim();
            
            if (email.isEmpty()) {
                printError("Email cannot be empty!");
            } else if (!email.matches(emailRegex)) {
                printError("Invalid email format! Please use format: example@domain.com");
            } else {
                validInput = true;
            }
        }
        return email;
    }
    
    public static String getAlphabeticInput(Scanner scanner, String prompt) {
        String input = "";
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                printError("Input cannot be empty!");
            } else if (!input.matches("^[a-zA-Z\\s]+$")) {
                printError("Please enter only alphabetic characters!");
            } else {
                validInput = true;
            }
        }
        return input;
    }
    
    public static String getAlphanumericInput(Scanner scanner, String prompt) {
        String input = "";
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                printError("Input cannot be empty!");
            } else if (!input.matches("^[a-zA-Z0-9\\s]+$")) {
                printError("Please enter only letters and numbers!");
            } else {
                validInput = true;
            }
        }
        return input;
    }
    
    public static boolean getYesNoInput(Scanner scanner, String prompt) {
        String input = "";
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + " (Y/N): " + RESET);
            input = scanner.nextLine().trim().toUpperCase();
            
            if (input.isEmpty()) {
                printError("Please enter Y or N!");
            } else if (!input.equals("Y") && !input.equals("N") && 
                      !input.equals("YES") && !input.equals("NO")) {
                printError("Invalid input! Please enter Y or N.");
            } else {
                validInput = true;
            }
        }
        return input.equals("Y") || input.equals("YES");
    }
    
    public static String getPhoneNumber(Scanner scanner, String prompt) {
        String phone = "";
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            phone = scanner.nextLine().trim();
            
            if (phone.isEmpty()) {
                printError("Phone number cannot be empty!");
            } else if (!phone.matches("^[0-9+\\-\\s()]{7,20}$")) {
                printError("Invalid phone number format!");
            } else {
                validInput = true;
            }
        }
        return phone;
    }
    
    public static String getStudentID(Scanner scanner, String prompt) {
        String id = "";
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(BRIGHT_CYAN + "  ▶ " + prompt + ": " + RESET);
            id = scanner.nextLine().trim();
            
            if (id.isEmpty()) {
                printError("Student ID cannot be empty!");
            } else if (!id.matches("^[A-Z0-9]{5,15}$")) {
                printError("Invalid Student ID format! Use 5-15 uppercase letters/numbers.");
            } else {
                validInput = true;
            }
        }
        return id;
    }
    
    public static void printSuccess(String message) {
        System.out.println(BRIGHT_GREEN + "  ✓ " + message + RESET);
    }
    
    public static void printError(String message) {
        System.out.println(BRIGHT_RED + "  ✗ " + message + RESET);
    }
    
    public static void printWarning(String message) {
        System.out.println(BRIGHT_YELLOW + "  ⚠ " + message + RESET);
    }
    
    public static void printInfo(String message) {
        System.out.println(BRIGHT_BLUE + "  ℹ " + message + RESET);
    }
    
    public static void printHighlight(String message) {
        System.out.println(BRIGHT_MAGENTA + "  ★ " + message + RESET);
    }
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void pause(Scanner scanner) {
        System.out.print(BRIGHT_BLACK + "\n  ⏸  Press Enter to continue..." + RESET);
        scanner.nextLine();
    }
    
    public static void printDivider() {
        System.out.println(BRIGHT_BLACK + LINE + RESET);
    }
    
    public static void printThickDivider() {
        System.out.println(BRIGHT_CYAN + DOUBLE_LINE + RESET);
    }
    
    public static void printWelcomeMessage() {
        System.out.println(CYAN + "============================================================" + RESET);
        System.out.println(YELLOW + "   *** Welcome to Your Academic Management Solution ***" + RESET);
        System.out.println(CYAN + "============================================================" + RESET);
    }
    
    public static void printGoodbye() {
        System.out.println(BRIGHT_MAGENTA + "\n  ╔════════════════════════════════════════════════════════╗");
        System.out.println("  ║                                                        ║");
        System.out.println("  ║          " + BRIGHT_CYAN + "Thank you for using UCMS!" + BRIGHT_MAGENTA + "                     ║");
        System.out.println("  ║          " + BRIGHT_YELLOW + "Goodbye and have a great day!" + BRIGHT_MAGENTA + "                 ║");
        System.out.println("  ║                                                        ║");
        System.out.println("  ╚════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }
    
    public static void printTableHeader(String... headers) {
        System.out.println(BRIGHT_CYAN + "\n  " + DOUBLE_LINE + RESET);
        System.out.print(BRIGHT_YELLOW + BOLD + "  ");
        for (String header : headers) {
            System.out.print(String.format("%-20s", header));
        }
        System.out.println(RESET);
        System.out.println(BRIGHT_CYAN + "  " + DOUBLE_LINE + RESET);
    }
    
    public static void printTableRow(String... columns) {
        System.out.print(BRIGHT_WHITE + "  ");
        for (String column : columns) {
            System.out.print(String.format("%-20s", column));
        }
        System.out.println(RESET);
    }
}
