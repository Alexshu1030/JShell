package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commandsystem.Result;
import commandsystem.Commands;
import shell.JShellWindow;

public class LsTest {
  public void setup() {
    Commands.initializeCommandHashTable();
  }
  
  @Test
  public void testNoArguments() {
    JShellWindow jShell = new JShellWindow();
    Result resultActual = Commands.run(jShell, "ls");
    String actual = resultActual.getMessage();
    String expected = "";
    assertEquals(expected, actual);
  }
}
