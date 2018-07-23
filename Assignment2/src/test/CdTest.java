package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commandsystem.Result;
import commandsystem.Commands;
import shell.JShellWindow;

public class CdTest {
  @Before
  public void setup() {
    Commands.initializeCommandHashTable();
  }
  
  @Test
  public void cdDown() {
    JShellWindow jShell = new JShellWindow();
    Commands.run(jShell, "mkdir folder");
    Commands.run(jShell, "cd folder");
    String expected = "folder";
    String actual = 
        jShell.getFileExplorer().getWorkingDirectory().getFileName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void cdUp() {
    JShellWindow jShell = new JShellWindow();
    Commands.run(jShell, "mkdir folder");
    Commands.run(jShell, "cd folder");
    Commands.run(jShell, "mkdir subfolder");
    Commands.run(jShell, "cd ..");
    String expected = "folder";
    String actual = 
        jShell.getFileExplorer().getWorkingDirectory().getFileName();
    assertEquals(expected, actual);
  }
  
  @Test
  public void tooFewArgs() {
    JShellWindow jShell = new JShellWindow();
    Result test = Commands.run(jShell, "cd");
    String actual = test.getErrorMessage();
    String expected = ("Invalid number of arguments.");
    assertEquals(actual, expected);
  }
  
  @Test
  public void tooManyArgs() {
    JShellWindow jShell = new JShellWindow();
    Result test = Commands.run(jShell, "cd a b c");
    String actual = test.getErrorMessage();
    String expected = ("Invalid number of arguments.");
    assertEquals(actual, expected);
  }
}
