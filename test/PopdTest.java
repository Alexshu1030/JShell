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

public class PopdTest {

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
  public void testWithInvalidNumberOfArguments() {
    
    Result resultActual = Commands.run(jShell, "popd A");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid number of arguments.";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testWithEmptyStack() {
    
    Result resultActual = Commands.run(jShell, "popd");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "The directory stack is empty";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testStackFollowsLIFO() {
    
    Commands.run(jShell, "mkdir /A /A/A1 /A/A2 /A/A1/A11 /B /B/B1");
    Commands.run(jShell, "cd /A/A1/A11");
    Commands.run(jShell, "pushd /B/B1");
    Commands.run(jShell, "pushd /A/A2");
    
    Commands.run(jShell, "popd");
       
    String workingDir = Commands.run(jShell, "pwd").getMessage();
    assertEquals("/B/B1\n", workingDir);
    
    Commands.run(jShell, "popd");
    
    workingDir = Commands.run(jShell, "pwd").getMessage();
    assertEquals("/A/A1/A11\n", workingDir);
  }
 }
