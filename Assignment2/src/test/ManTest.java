package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commandsystem.Commands;
import commandsystem.Result;
import shell.JShellWindow;

public class ManTest {
  @Before
  public void setup() {
    Commands.initializeCommandHashTable();
  }
  @Test
  public void testStandard() {
    JShellWindow jShell = new JShellWindow();
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
    JShellWindow jShell = new JShellWindow();
    Result resultActual = Commands.run(jShell, "man nam");
    String actual = resultActual.getErrorMessage();
    String expected = "nam is not a valid command.";
    assertEquals(expected, actual);
  }
  @Test
  public void testNoArguments() {
    JShellWindow jShell = new JShellWindow();
    Result resultActual = Commands.run(jShell, "man");
    String actual = resultActual.getErrorMessage();
    String expected = "Invalid number of arguments.";
    assertEquals(expected, actual);
  }
  @Test
  public void testTooManyArguments() {
    JShellWindow jShell = new JShellWindow();
    Result resultActual = Commands.run(jShell, "man man cat");
    String actual = resultActual.getErrorMessage();
    String expected = "Invalid number of arguments.";
    assertEquals(expected, actual);
  }

}
