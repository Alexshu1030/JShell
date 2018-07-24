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
