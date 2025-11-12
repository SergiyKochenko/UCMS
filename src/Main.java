public class Main {
    public static void main(String[] args) {
        // Clear screen for better presentation
        UIHelper.clearScreen();
        
        // Display colorful banner
        UIHelper.printBanner();
        
        // Welcome message
        UIHelper.printWelcomeMessage();
        
        // Add some spacing
        System.out.println();
        
        // Start the role determination
        UCMS.determineRole();
        
        // Goodbye message when exiting
        UIHelper.printGoodbye();
    }
}

