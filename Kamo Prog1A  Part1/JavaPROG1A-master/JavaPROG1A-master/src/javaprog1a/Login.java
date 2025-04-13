/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaprog1a;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Class handling all authentication-related operations
 */
class Login {
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellNumber;
    private String firstName;
    private String lastName;
    
    /**
     * Validates username format
     * @param username The username to validate
     * @return true if username contains underscore and is ≤5 characters
     */
    public boolean checkUserName(String username) {
        try {
            return username != null && username.contains("_") && username.length() <= 5;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Validates password complexity
     * @param password The password to validate
     * @return true if password meets complexity requirements
     */
    public boolean checkPasswordComplexity(String password) {
        try {
            if (password == null) return false;
            String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
            return Pattern.compile(regex).matcher(password).matches();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Validates cellphone number format
     * @param cellNumber The number to validate
     * @return true if number starts with + and has 10-12 digits
     */
    public boolean checkCellPhoneNumber(String cellNumber) {
        try {
            if (cellNumber == null) return false;
            String regex = "^\\+\\d{10,12}$";
            return Pattern.compile(regex).matcher(cellNumber).matches();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Handles complete user registration process
     * @return Registration status message
     */
    public String registerUser() {
        try {
            StringBuilder result = new StringBuilder();
            
            // Get personal details
            firstName = getInput("Enter first name:");
            if (firstName == null) return "Registration cancelled";
            
            lastName = getInput("Enter last name:");
            if (lastName == null) return "Registration cancelled";
            
            // Validate username
            registeredUsername = getInput("Enter username (must contain _ and be ≤5 chars):");
            if (registeredUsername == null) return "Registration cancelled";
            
            if (!checkUserName(registeredUsername)) {
                result.append("Username is not correctly formatted\n");
            } else {
                result.append("Username successfully captured\n");
            }
            
            // Validate password
            registeredPassword = getInput("Enter password (8+ chars, capital, number, special char):");
            if (registeredPassword == null) return "Registration cancelled";
            
            if (!checkPasswordComplexity(registeredPassword)) {
                result.append("Password does not meet complexity requirements\n");
            } else {
                result.append("Password successfully captured\n");
            }
            
            // Validate cell number
            registeredCellNumber = getInput("Enter cell number (with country code, e.g., +27831234567):");
            if (registeredCellNumber == null) return "Registration cancelled";
            
            if (!checkCellPhoneNumber(registeredCellNumber)) {
                result.append("Cell number incorrectly formatted\n");
            } else {
                result.append("Cell number successfully added\n");
            }
            
            // Determine final registration status
            if (result.toString().contains("not") || result.toString().contains("does not") || result.toString().contains("incorrectly")) {
                result.append("\nRegistration incomplete - please correct errors");
            } else {
                result.append("\nRegistration successful!");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "Registration error: " + e.getMessage();
        }
    }
    
    /**
     * Handles user login attempt
     * @return true if credentials match registered user
     */
    public boolean loginUser() {
        try {
            String username = getInput("Enter username:");
            if (username == null) return false;
            
            String password = getInput("Enter password:");
            if (password == null) return false;
            
            return username.equals(registeredUsername) && password.equals(registeredPassword);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Generates login status message
     * @param isLoggedIn Whether login was successful
     * @return Appropriate welcome or error message
     */
    public String returnLoginStatus(boolean isLoggedIn) {
        try {
            return isLoggedIn 
                ? "Welcome " + firstName + ", " + lastName + "! Great to see you again."
                : "Username or password incorrect";
        } catch (Exception e) {
            return "Error generating login status";
        }
    }
    
    /**
     * Helper method to get user input with null checking
     * @param message The prompt to display
     * @return User input or null if cancelled
     */
    private String getInput(String message) {
        try {
            return JOptionPane.showInputDialog(message);
        } catch (Exception e) {
            return null;
        }
    }
    // Add these methods to your Login class
public void registerUserTestHelper(String username, String password, String cellNumber, 
                                 String firstName, String lastName) {
    this.registeredUsername = username;
    this.registeredPassword = password;
    this.registeredCellNumber = cellNumber;
    this.firstName = firstName;
    this.lastName = lastName;
}

public boolean loginUserTestHelper(String username, String password) {
    return username.equals(registeredUsername) && password.equals(registeredPassword);
}
}