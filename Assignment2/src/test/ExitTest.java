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

public class ExitTest {

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
  public void testWithArguments() {
    
    Result resultActual = Commands.run(jShell, "exit a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid number of arguments.";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testNoArguments() {
    
    Result resultActual = Commands.run(jShell, "exit");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
    
    assertTrue(jShell.getTerminate());
  }
  
  @Test
  public void testCanNotBeRedirected() {
    
    Commands.run(jShell, "echo \"\" > a");
    Result resultActual = Commands.run(jShell, "exit > a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Command can not be redirected.";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
}
