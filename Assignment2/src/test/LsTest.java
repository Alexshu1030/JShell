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
import commandsystem.*;
import filesystem.*;
import shell.JShellWindow;

public class LsTest {
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
  public void testNoArguments() {
    explorer.setWorkingDirectory(explorer.getRootDirectory());
    Result resultActual = Commands.run(jShell, "ls");

    String actual = resultActual.getMessage();
    
    String expected = "";
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testRecursiveNoArguments() {
    explorer.setWorkingDirectory(explorer.getRootDirectory());
    Result resultActual = Commands.run(jShell, "ls -R");

    String actual = resultActual.getMessage();
    
    String expected = "/:\n\n";
    
    assertEquals(expected, actual);
  }

  @Test
  public void testMultiPathDir() {
    Commands.run(jShell, "mkdir top top/middle1 top/middle2 top/middle1/bottom");
    
    Result resultActual = Commands.run(jShell, "ls top");

    String actual = resultActual.getMessage();

    String expected = "/top:\nmiddle1\tmiddle2\n";

    assertEquals(expected, actual);
  }
  
  @Test
  public void testRecursiveMultiPathDir() {
    Commands.run(jShell, "mkdir t t/1 t/2 t/1/b");
    
    Result resultActual = Commands.run(jShell, "ls -R t");

    String actual = resultActual.getMessage();

    String expected = "/t:\n1\t2\n/t/1:\nb\n\n/t/2:\n\n/t/1/b:\n\n";

    assertEquals(expected, actual);
  }
}
