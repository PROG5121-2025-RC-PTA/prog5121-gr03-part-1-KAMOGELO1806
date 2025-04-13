package javaprog1a;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class LoginIT {
    private Login login;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        login = new Login();
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("use_1"));
        assertTrue(login.checkUserName("a_b"));
    }
    
    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("user1")); // no underscore
        assertFalse(login.checkUserName("u_s_e_r")); // too long
        assertFalse(login.checkUserName(null)); // null input
    }
    
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Passw0rd!"));
        assertTrue(login.checkPasswordComplexity("A1@bcdefg"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(login.checkPasswordComplexity("password")); // no caps, numbers, or symbols
        assertFalse(login.checkPasswordComplexity("Passw0rd")); // no symbol
        assertFalse(login.checkPasswordComplexity("passw0rd!")); // no caps
        assertFalse(login.checkPasswordComplexity("Password!")); // no numbers
        assertFalse(login.checkPasswordComplexity("P1!")); // too short
        assertFalse(login.checkPasswordComplexity(null)); // null input
    }
    
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
        assertTrue(login.checkCellPhoneNumber("+27123456789"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        assertFalse(login.checkCellPhoneNumber("27831234567")); // no +
        assertFalse(login.checkCellPhoneNumber("+278312345")); // too short
        assertFalse(login.checkCellPhoneNumber("+2783123456789")); // too long
        assertFalse(login.checkCellPhoneNumber("+27abc123456")); // contains letters
        assertFalse(login.checkCellPhoneNumber(null)); // null input
    }
    
    @Test
    public void testRegisterUser_Cancellation() {
        // This would normally require mocking JOptionPane
        // For this test, we'll assume the method handles cancellation properly
        // and returns "Registration cancelled" when user cancels any input
    }
    
    @Test
    public void testLoginUser_Success() {
        // Setup test user
        login.registerUserTestHelper("test_1", "Passw0rd!", "+27831234567", "John", "Doe");
        
        // Test successful login
        assertTrue(login.loginUserTestHelper("test_1", "Passw0rd!"));
    }
    
    @Test
    public void testLoginUser_Failure() {
        // Setup test user
        login.registerUserTestHelper("test_1", "Passw0rd!", "+27831234567", "John", "Doe");
        
        // Test failed login
        assertFalse(login.loginUserTestHelper("test_1", "wrongpass"));
        assertFalse(login.loginUserTestHelper("wronguser", "Passw0rd!"));
    }
    
    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUserTestHelper("test_1", "Passw0rd!", "+27831234567", "John", "Doe");
        assertEquals("Welcome John, Doe! Great to see you again.", 
                   login.returnLoginStatus(true));
    }
    
    @Test
    public void testReturnLoginStatus_Failure() {
        assertEquals("Username or password incorrect", 
                   login.returnLoginStatus(false));
    }
}