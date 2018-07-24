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
import exceptions.FileNotFoundException;
import exceptions.InvalidPathException;
import exceptions.PathExistsException;
import shell.JShellWindow;

public class CpTest {

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
  public void testPathRelativeToWD() {
    Commands.run(jShell, "echo \"contents of text\" > TextFile");
    Commands.run(jShell, "mkdir A");
    Result resultActual = Commands.run(jShell, "cp TextFile A");
    Result tree = Commands.run(jShell, "tree");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    String actualTree = tree.getMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    String expectedTree = "\\\n\tTextFile\n\tA\n\t\tTextFile\n\n";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    assertEquals(expectedTree, actualTree);
  }
  
  @Test
  public void testAtNonRootDirectory() {
    Commands.run(jShell, "cd A/A1");
    Commands.run(jShell, "mkdir A A/A1 A/A1/A11 B");
    Commands.run(jShell, "echo \"contents of text\" > TextFile");
    Result resultActual = Commands.run(jShell, "cp TextFile /B");
    Result lsActual = Commands.run(jShell, "ls /B");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    String actualLsMessage = lsActual.getMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    String expectedLsMessage = "/B:\nTextFile\n";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    assertEquals(expectedLsMessage, actualLsMessage);
  }
}
