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

public class CommandsTest {

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
    
    Result resultActual = Commands.run(jShell, "");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid input.";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testCommandDoesNotExist() {
    
    Result resultActual = Commands.run(jShell, "iamnotreal");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Command does not exist.";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testRedirectedMessageOverwrite() {
    
    Commands.run(jShell, "mkdir A B C");
    Commands.run(jShell, "ls > a");
    Result resultActual = Commands.run(jShell, "cat a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "A\tB\tC";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testRedirectedMessageAppend() {
    
    Commands.run(jShell, "mkdir A B C");
    Commands.run(jShell, "ls > a");
    Commands.run(jShell, "ls >> a");
    Result resultActual = Commands.run(jShell, "cat a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "A\tB\tCA\tB\tC\ta";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testRedirectedToInvalidOutFile() {
    
    Result resultActual = Commands.run(jShell, "ls > A/a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid outFile path.";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testRedirectEmptyString() {
    
    Result resultActual = Commands.run(jShell, "ls > a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
}