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

public class CatTest {

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
    
    Result resultActual = Commands.run(jShell, "cat");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid number of arguments.";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testFileDoesNotExist() {
    
    Result resultActual = Commands.run(jShell, "cat a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "The path does not exist. (Argument 0: a)";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testEmptyFile() {
    
    Commands.run(jShell, "echo \"\" > a");
    Result resultActual = Commands.run(jShell, "cat a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "\n";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
  
  @Test
  public void testFileWithContent() {
    
    Commands.run(jShell, "echo \"This is a sentence.\" > a");
    Result resultActual = Commands.run(jShell, "cat a");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "This is a sentence.\n";
    String expectedErrorMessage = "";
    
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
}
