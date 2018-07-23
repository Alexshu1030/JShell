package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commandsystem.Result;
import commandsystem.Commands;
import shell.JShellWindow;

public class LsTest {
  @Before
  public void setup() {
    Commands.initializeCommandHashTable();
  }

  @Test
  public void testMultiPathDir() {
    JShellWindow jShell = new JShellWindow();
    Commands.run(jShell, "mkdir top top/middle1 top/middle2 top/middle1/bottom");
    Result resultActual = Commands.run(jShell, "ls top");
    String actual = resultActual.getMessage();
    String expected = "/top:\nmiddle1\tmiddle2\n";
    assertEquals(expected, actual);
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
