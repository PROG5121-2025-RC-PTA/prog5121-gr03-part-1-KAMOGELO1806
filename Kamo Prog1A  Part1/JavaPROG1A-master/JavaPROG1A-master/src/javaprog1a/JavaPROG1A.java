/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaprog1a;
import javax.swing.JOptionPane;
/**
 * Main application class for user registration and login system
 */
public class JavaPROG1A {
    private Login loginSystem = new Login();
    
    public static void main(String[] args) {
        try {
            JavaPROG1A program = new JavaPROG1A();
            program.startApplication();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "A critical error occurred: " + e.getMessage(), 
                "System Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Starts the application and displays main menu
     */
    public void startApplication() {
        try {
            while (true) {
                String[] options = {"Register", "Login", "Exit"};
                int choice = JOptionPane.showOptionDialog(null, 
                    "Welcome to User Authentication System\nPlease choose an option:", 
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);
                
                switch (choice) {
                    case 0: // Register
                        performRegistration();
                        break;
                    case 1: // Login
                        performLogin();
                        break;
                    case 2: // Exit
                    case JOptionPane.CLOSED_OPTION: // User closed dialog
                        JOptionPane.showMessageDialog(null, "Thank you for using our system. Goodbye!");
                        return;
                }
            }
        } catch (Exception e) {
            showErrorMessage("Application error: " + e.getMessage());
        }
    }
    
    /**
     * Handles user registration process
     */
    private void performRegistration() {
        try {
            String registrationResult = loginSystem.registerUser();
            if (registrationResult.equals("Registration cancelled")) {
                showInformationMessage("Registration was cancelled by user");
            } else {
                showInformationMessage(registrationResult);
            }
        } catch (Exception e) {
            showErrorMessage("Registration failed: " + e.getMessage());
        }
    }
    
    /**
     * Handles user login process
     */
    private void performLogin() {
        try {
            if (loginSystem.loginUser()) {
                showInformationMessage(loginSystem.returnLoginStatus(true));
            } else {
                showInformationMessage(loginSystem.returnLoginStatus(false));
            }
        } catch (Exception e) {
            showErrorMessage("Login failed: " + e.getMessage());
        }
    }
    
    private void showInformationMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

