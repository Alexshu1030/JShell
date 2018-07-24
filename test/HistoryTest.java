// **********************************************************
// Assignment2:
// Student1: Ciaran Hogan
// UTORID user_name: hogancia
// UT Student #: 1003796859
// Author: Ciaran Hogan
//
// Student2: Jeremy Tanuan
// UTORID user_name: tanuanje
// UT Student #: 1003939982
// Author: Jeremy Tanuan
//
// Student3: Aster Wu
// UTORID user_name: wuaster
// UT Student #: 1004175117
// Author: Aster Wu
//
// Student4: Yan Chen Huang
// UTORID user_name: huan1085
// UT Student #: 1004325857
// Author: Yan Chen Huang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.*;
import filesystem.*;
import commandsystem.*;
import shell.JShellWindow;

public class HistoryTest {

  private static JShellWindow jShell;
  private static FileExplorer explorer;
  
  @BeforeClass
  public static void setUpClass() {
    
    Commands.initializeCommandHashTable();
  }
  
  @Before
  public void setUp() {

    jShell = new JShellWindow();
    explorer = jShell.getFileExplorer();
  }
  
  @After
  public void tearDown() throws NoSuchFieldException, SecurityException,
  IllegalArgumentException, IllegalAccessException {
    // Reset the static self reference in the FileExplorer class
    Field field = (explorer.getClass()).getDeclaredField("rootDirectory");
    field.setAccessible(true);
    field.set(null, null);
  }
  
  @Test
  public void testNoArgumentsWithOneInLog() {
    // Simulate adding to log to allow history to work
    jShell.addInputtoLog("history");
    Result resultActual = Commands.run(jShell, "history");

    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "1. history\n";
    String expectedErrorMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
  @Test
  public void testNoArgumentsWithErrorInLog() {
    // Simulate adding to log to allow history to work
    jShell.addInputtoLog("hello");
    jShell.addInputtoLog("history");
    Result resultActual = Commands.run(jShell, "history");

    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "1. hello\n" + 
        "2. history\n";
    String expectedErrorMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
  @Test
  public void testStandardNoArgument() {
    // Simulate adding to log to allow history to work
    jShell.addInputtoLog("hello");
    jShell.addInputtoLog("cat A");
    jShell.addInputtoLog("history");
    Result resultActual = Commands.run(jShell, "history");

    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "1. hello\n2. cat A\n" + 
        "3. history\n";
    String expectedErrorMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
  @Test
  public void testStandardWithArgument() {
    // Simulate adding to log to allow history to work
    jShell.addInputtoLog("hello");
    jShell.addInputtoLog("cat A");
    jShell.addInputtoLog("history");
    Result resultActual = Commands.run(jShell, "history 2");

    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "2. cat A\n" + 
        "3. history\n";
    String expectedErrorMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
  @Test
  public void testTooManyArguments() {
    // Simulate adding to log to allow history to work
    jShell.addInputtoLog("hello");
    jShell.addInputtoLog("history");
    Result resultActual = Commands.run(jShell, "history 1 2");

    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid number of arguments.";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
}