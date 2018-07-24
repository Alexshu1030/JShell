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

public class MvTest {

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
  public void testWithInvalidArguments() {
    
    Result resultActual = Commands.run(jShell, "mv A B C");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid number of arguments.";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testWithNonExistantOldPath() {
    
    Commands.run(jShell, "mkdir B");
    Result resultActual = Commands.run(jShell, "mv A B");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "The old path does not exist. " + 
    "(Argument 0: A)";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testWithNonExistantNewPath() {
    
    Commands.run(jShell, "mkdir A");
    Result resultActual = Commands.run(jShell, "mv A B");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "A file of the same name already " + 
    "exists at the new path. (Argument 1: B)";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testMovingDirToDir() {
    
    Commands.run(jShell, "mkdir /A /A/A1 /B");
    Result resultActual = Commands.run(jShell, "mv /A/A1 B");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    
    try {
      assertEquals(explorer.getDirectory("/B/A1").getFileName(), "A1");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testMovingDirWithSubDirToAnotherDir() {
    
    Commands.run(jShell, "mkdir /A /A/A1 /B");
    Result resultActual = Commands.run(jShell, "mv /A /B");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    
    try {
      assertEquals(explorer.getDirectory("/B/A/A1").getFileName(), "A1");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testMovingFileToDir() {
    
    Commands.run(jShell, "mkdir /A /B");
    Commands.run(jShell, "echo \"test\" > /A/a");
    
    Result resultActual = Commands.run(jShell, "mv /A/a /B");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    
    try {
      assertEquals(explorer.getFile("/B/a").getFileName(), "a");
    }
    catch (FileNotFoundException e) {
      fail("File not moved.");
    }
  }
  
  @Test
  public void testMovingFromRelPath() {
    
    Commands.run(jShell, "mkdir /A /A/A1 /B");
    Commands.run(jShell, "cd A");
    
    Result resultActual = Commands.run(jShell, "mv A1 /B");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    
    try {
      assertEquals(explorer.getFile("/B/A1").getFileName(), "A1");
    }
    catch (FileNotFoundException e) {
      fail("File not moved.");
    }
  }
  
  @Test
  public void testChangeFileName() {
    
    Commands.run(jShell, "mkdir /A /B");
    Commands.run(jShell, "echo \"test\" > /A/a");
    
    Result resultActual = Commands.run(jShell, "mv /A/a /B/b");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    
    try {
      assertEquals(explorer.getFile("/B/b").getFileName(), "b");
    }
    catch (FileNotFoundException e) {
      fail("File not moved and renamed.");
    }
    
    try {
      assertEquals(explorer.getFile("/B/b").getFileContents(), "test\n");
    }
    catch (FileNotFoundException e) {
      fail("File content not transfered.");
    }
  }
}
