package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commandsystem.Result;
import commandsystem.Commands;
import shell.JShellWindow;

public class GrepTest {
  @Before
  public void setup() {
    Commands.initializeCommandHashTable();
  }
  
  @Test
  public void recursiveCase() {
    JShellWindow jShell = new JShellWindow();
    Commands.run(jShell, "mkdir folder");
    Commands.run(jShell, "cd folder");
    Commands.run(jShell, "echo ne\nonetwo\ntwo\nonethree > test.txt");
    Result test = Commands.run(jShell, "grep -R one folder");
    String actual = test.getMessage();
    String expected = ("onetwo\nonethree\n");
    assertEquals(expected, actual);
  }
  
  @Test
  public void normalCase() {
    JShellWindow jShell = new JShellWindow();
    Commands.run(jShell, "echo a\nasd\ndd > file.txt");
    Result test = Commands.run(jShell, "grep a file.txt");
    String actual = test.getMessage();
    String expected = ("a\nasd\n");
    assertEquals(expected, actual);
  }
  
  @Test
  public void errorCase() {
    JShellWindow jShell = new JShellWindow();
    Result test = Commands.run(jShell, "grep a file.txt");
    String actual = test.getErrorMessage();
    String expected = ("The path does not exist.");
    assertEquals(expected, actual);
  }
  
  @Test
  public void tooFewArgs() {
    JShellWindow jShell = new JShellWindow();
    Result test = Commands.run(jShell, "grep file.txt");
    String actual = test.getErrorMessage();
    String expected = ("Invalid number of arguments.");
    assertEquals(expected, actual);
  }
  
  @Test
  public void noArgs() {
    JShellWindow jShell = new JShellWindow();
    Result test = Commands.run(jShell, "grep");
    String actual = test.getErrorMessage();
    String expected = ("Invalid number of arguments.");
    assertEquals(expected, actual);
  }
}
