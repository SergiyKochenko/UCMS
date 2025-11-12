package UserManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Abstract User class
 * ---------------------------------------------------------
 * Represents a generic user in the UCMS system.
 * Serves as a base class for concrete user types such as Admin, Lecturer, and Student.
 *
 * Responsibilities:
 *  - Store basic user information (first name, last name, email, etc.)
 *  - Enforce validation on key attributes
 *  - Define an abstract method for generating reports
 */
public abstract class User {

    /** First name of the user */
    private String firstname;

    /** Last name of the user */
    private String lastname;

    /** Email address of the user */
    private String email;

    /** Password for authentication */
    private String password;

    /** Date of birth of the user*/
    private LocalDate dateOfBirth;

    /** Address of the user */
    private String address;

    /** Phone number of the user */
    private String phone;

    /** Default constructor */
    public User() { }

    /**
     * Parameterized constructor to initialize a user.
     * Invokes setters to ensure proper validation.
     *
     * @param firstname user's first name
     * @param lastname  user's last name
     * @param email     user's email address
     * @param password  user's password
     * @param address   user's address
     * @param phone     user's phone number
     */
    public User(String firstname, String lastname, String email,
                String password, String address, String phone) {
        // Uses setter methods. Good practice as these methods include input validation logic.
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        setAddress(address);
        setPhone(phone);
    }

    /**
     * Add your Abstract method for generating reports below this.
     * Must be implemented by concrete subclasses (e.g., Admin).
     *
     * @param reportParameters an array of strings containing data for the report
     */
    public abstract void generateReport(String[] reportParameters);


    // --------------------- GETTERS & SETTERS ---------------------

    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the user's first name.
     * Input validation is performed to ensure data integrity.
     * Using IllegalArgumentException is appropriate here because invalid names
     * represent programming errors that should be caught during development/testing.
     *
     * @param firstname user's first name
     * @throws IllegalArgumentException if firstname is null or contains non-letter characters
     */
    public void setFirstname(String firstname) {
        if (firstname == null || !firstname.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException(
                    "Firstname must contain only letters and cannot be null"
            );
        }
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the user's last name.
     * Input validation is performed to ensure data integrity.
     * Using IllegalArgumentException is appropriate here because invalid names
     * represent programming errors that should be caught during development/testing.
     *
     * @param lastname user's last name
     * @throws IllegalArgumentException if lastname is null or contains non-letter characters
     */
    public void setLastname(String lastname) {
        if (lastname == null || !lastname.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException(
                    "Lastname must contain only letters and cannot be null"
            );
        }
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * Simplified validation - only checks for null.
     * REFACTORED: Removed exception handling in favor of simple conditional check.
     * Exception handling is unnecessary here as null email can be handled gracefully
     * with a default value or user prompt rather than crashing the program.
     *
     * @param email user's email address
     */
    public void setEmail(String email) {
        // Refactored: Use conditional validation with meaningful feedback instead of exception
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Warning: Email cannot be null or empty. Using default value.");
            this.email = "no-email@ucms.system";
        } else {
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * REFACTORED: No exception needed here - password validation can be handled
     * at the authentication layer. Empty/null passwords are set to a default
     * requiring immediate change upon login.
     *
     * @param password user's password
     */
    public void setPassword(String password) {
        // Refactored: Use conditional check instead of exception
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Warning: Password is empty. Default password set - please change on first login.");
            this.password = "ChangeMe123!";
        } else {
            this.password = password;
        }
    }

    public String getAddress() {
        return address;
    }

    /**
     * Sets the user's address.
     * REFACTORED: Address is optional information, so no exception is needed.
     * Null or empty addresses are acceptable and handled gracefully.
     *
     * @param address user's address
     */
    public void setAddress(String address) {
        // Refactored: No exception needed - address is optional
        this.address = (address == null || address.trim().isEmpty()) ? "Not provided" : address;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user's phone number.
     * KEPT EXCEPTION: Phone validation is critical for contact purposes.
     * Using IllegalArgumentException is appropriate as it catches invalid
     * input that violates business rules.
     *
     * @param phone user's phone number
     * @throws IllegalArgumentException if phone is null or empty
     */
    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Phone number cannot be null or empty"
            );
        }
        this.phone = phone;
    }

    /**
     * Sets the user's date of birth from a string input.
     * KEPT EXCEPTION: Date validation requires exception handling because
     * invalid date formats represent exceptional conditions that cannot be
     * resolved without user intervention. IllegalArgumentException clearly
     * indicates the problem to the caller.
     *
     * @param dateInput date string in various accepted formats
     * @throws IllegalArgumentException if dateInput is null, empty, or in invalid format
     */
    public void setDateOfBirth(String dateInput) {
        if (dateInput == null || dateInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Date of birth cannot be null or empty.");
        }

        // Define multiple accepted date formats
        List<String> formats = Arrays.asList(
                "yyyy-MM-dd",     // e.g., 2025-11-07
                "dd/MM/yyyy",     // e.g., 07/11/2025
                "MM-dd-yyyy",     // e.g., 11-07-2025
                "dd-MM-yyyy"      // e.g., 07-11-2025
        );

        LocalDate parsedDate = null;
        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                parsedDate = LocalDate.parse(dateInput, formatter);
                break; // Stop at the first successful parse
            } catch (DateTimeParseException ignored) {
            }
        }

        if (parsedDate == null) {
            throw new IllegalArgumentException(
                    "Invalid date format. Please use one of the following: yyyy-MM-dd, dd/MM/yyyy, MM-dd-yyyy, or dd-MM-yyyy."
            );
        }

        this.dateOfBirth = parsedDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    // ---------------------- VALIDATION METHODS ----------------------

    /**
     * Validates email format using regex.
     * Example of accepted formats: user@example.com
     *
     * @param email email string to validate
     * @return true if email is valid, false otherwise
     */
    private boolean validateEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    /**
     * Validates phone number format using regex.
     * Accepts digits only, optional + prefix, 7–15 digits.
     *
     * @param phone phone string to validate
     * @return true if phone is valid, false otherwise
     */
    private boolean validatePhone(String phone) {
        if (phone == null) return false;
        String phoneRegex = "^\\+?[0-9]{7,15}$";
        return Pattern.matches(phoneRegex, phone);
    }
}
