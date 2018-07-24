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
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import commandsystem.Commands;
import commandsystem.Result;
import filesystem.FileExplorer;
import shell.JShellWindow;

public class ManTest {
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
  @Test
  public void testStandard() {
    Result resultActual = Commands.run(jShell, "man man");
    String actual = resultActual.getMessage();
    String expected = "NAME:\n"
        + "  man CMD - Displays documentation for CMD \n" + "DESCRIPTION:\n"
        + "  Displays name, description, possible parameters"
        + " possible return messages, and example usage of cmd\n"
        + "PARAMETERS:\n" + "  CMD - The command for which the documentation "
        + "will be displayed.\n" + "RETURNS:\n" + "  Documentation for CMD\n"
        + "EXAMPLE USAGE:\n" + "  /#: man man\n"
        + "    will display documentation for man.\n" + "  /#: man nam\n"
        + "    Will return an error message because nam"
        + " is not a valid command.\n";
    assertEquals(expected, actual);
  }
  @Test
  public void testWrongCommand() {
    Result resultActual = Commands.run(jShell, "man nam");
    String actual = resultActual.getErrorMessage();
    String expected = "nam is not a valid command.";
    String actualOutput = resultActual.getMessage();
    assertEquals(expected, actual);
    assertEquals("", actualOutput);
  }
  @Test
  public void testNoArguments() {
    Result resultActual = Commands.run(jShell, "man");
    String actual = resultActual.getErrorMessage();
    String expected = "Invalid number of arguments.";
    String actualOutput = resultActual.getMessage();
    assertEquals(expected, actual);
    assertEquals("", actualOutput);
  }
  @Test
  public void testTooManyArguments() {
    Result resultActual = Commands.run(jShell, "man man cat");
    String actual = resultActual.getErrorMessage();
    String expected = "Invalid number of arguments.";
    String actualOutput = resultActual.getMessage();
    assertEquals(expected, actual);
    assertEquals("", actualOutput);
  }
  @After
  public void tearDown() throws NoSuchFieldException, SecurityException,
  IllegalArgumentException, IllegalAccessException {
    // Reset the static self reference in the FileExplorer class
    Field field = (explorer.getClass()).getDeclaredField("rootDirectory");
    field.setAccessible(true);
    field.set(null, null);
  }

}
