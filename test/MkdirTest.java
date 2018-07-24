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

public class MkdirTest {

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
    
    Result resultActual = Commands.run(jShell, "mkdir");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid number of arguments.";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
  
  @Test
  public void testOneAbsolutePathFromRD() {
    
    Result resultActual = Commands.run(jShell, "mkdir /A");
    
    String actualMessage = resultActual.getMessage();
    String expectedMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertFalse(resultActual.errorOccured());
    
    try {
      assertEquals(explorer.getDirectory("/A").getFileName(), "A");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testAbsolutePathFromNonRootWD() {
    
    try {
      explorer.addDirectory("/A");
      explorer.setWorkingDirectory(explorer.getDirectory("/A"));
    }
    catch (InvalidPathException | PathExistsException |
        FileNotFoundException e1) {
      fail("Failed test setup");
    }

    Result resultActual = Commands.run(jShell, "mkdir /A/A1");
    
    String actualMessage = resultActual.getMessage();
    String expectedMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertFalse(resultActual.errorOccured());
    
    try {
      assertEquals(explorer.getDirectory("/A/A1").getFileName(), "A1");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testRelativePathFromNonRootWD() {
    
    try {
      explorer.addDirectory("/A");
      explorer.setWorkingDirectory(explorer.getDirectory("/A"));
    }
    catch (InvalidPathException | PathExistsException |
        FileNotFoundException e1) {
      fail("Failed test setup");
    }
    
    Result resultActual = Commands.run(jShell, "mkdir A1");
    
    String actualMessage = resultActual.getMessage();
    String expectedMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertFalse(resultActual.errorOccured());
    
    try {
      assertEquals(explorer.getDirectory("/A/A1").getFileName(), "A1");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testMultipleAbsolutePathsFromRD() {
    
    Result resultActual = Commands.run(jShell, "mkdir /A /A/A1 /A/A1/A11");
    
    String actualMessage = resultActual.getMessage();
    String expectedMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertFalse(resultActual.errorOccured());
    
    try {
      assertEquals(explorer.getDirectory("/A").getFileName(), "A");
      assertEquals(explorer.getDirectory("/A/A1").getFileName(), "A1");
      assertEquals(explorer.getDirectory("/A/A1/A11").getFileName(), "A11");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testOneRelativePathFromRD() {
    
    Result resultActual = Commands.run(jShell, "mkdir A");
    
    String actualMessage = resultActual.getMessage();
    String expectedMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertFalse(resultActual.errorOccured());
    
    try {
      assertEquals(explorer.getDirectory("/A").getFileName(), "A");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testMultipleRelativePathsFromRD() {
    
    Result resultActual = Commands.run(jShell, "mkdir A A/A1 A/A1/A11");
    
    String actualMessage = resultActual.getMessage();
    String expectedMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertFalse(resultActual.errorOccured());
    
    try {
      assertEquals(explorer.getDirectory("/A").getFileName(), "A");
      assertEquals(explorer.getDirectory("/A/A1").getFileName(), "A1");
      assertEquals(explorer.getDirectory("/A/A1/A11").getFileName(), "A11");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testComplexRelativePathFromRD() {
    
    Result resultActual = Commands.run(jShell,
        "mkdir /A /A/A1 /A/A1/A11 /A/../././A/A1/../A1/A12");
    
    String actualMessage = resultActual.getMessage();
    String expectedMessage = "";
    
    assertEquals(actualMessage, expectedMessage);
    assertFalse(resultActual.errorOccured());
    
    try {
      assertEquals(explorer.getDirectory("/A/A1/A12").getFileName(), "A12");
    }
    catch (FileNotFoundException e) {
      fail("Directory not created.");
    }
  }
  
  @Test
  public void testInvalidCharacterInName() {
    
    Result resultActual = Commands.run(jShell,
        "mkdir A#");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "Invalid characters in directory name." +
    " (Argument 0: A#)";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
  
  @Test
  public void testDirectoryExistsAtPath() {
    
    try {
      explorer.addDirectory("/A");
    }
    catch (InvalidPathException | PathExistsException e) {
      fail("Failed test setup");
    }
    
    Result resultActual = Commands.run(jShell, "mkdir A");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "A directory already exists at that path." +
    " (Argument 0: A)";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
  
  @Test
  public void testInvalidPath() {
    
    Result resultActual = Commands.run(jShell, "mkdir /A/A1");
    
    String actualMessage = resultActual.getMessage();
    String actualErrorMessage = resultActual.getErrorMessage();
    
    String expectedMessage = "";
    String expectedErrorMessage = "The path does not exist." + 
    " (Argument 0: /A/A1)";
    
    assertEquals(actualMessage, expectedMessage);
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }
}
