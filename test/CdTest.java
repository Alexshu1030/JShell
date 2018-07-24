// **********************************************************
// Assignment2:
// Student1: Ciaran Hogan
// UTORID user_name: hogancia
// UT Student #: 1003796859
// Author: Ciaran Hogan
//
// Student2: Jeremy Tanuan
// UTORID user_name: tanuanje
// UT Student #: 1003939982
// Author: Jeremy Tanuan
//
// Student3: Aster Wu
// UTORID user_name: wuaster
// UT Student #: 1004175117
// Author: Aster Wu
//
// Student4: Yan Chen Huang
// UTORID user_name: huan1085
// UT Student #: 1004325857
// Author: Yan Chen Huang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
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
    Commands.run(jShell, "mkdir folder/subfolder");
    Commands.run(jShell, "cd folder/subfolder");
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
    assertEquals(expected, actual);
  }
  
  @Test
  public void tooManyArgs() {
    JShellWindow jShell = new JShellWindow();
    Result test = Commands.run(jShell, "cd a b c");
    String actual = test.getErrorMessage();
    String expected = ("Invalid number of arguments.");
    assertEquals(expected, actual);
  }
}
