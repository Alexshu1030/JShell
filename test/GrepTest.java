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
import commandsystem.TextEditor;
import exceptions.InvalidPathException;
import filesystem.File;
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
    try {
      TextEditor.writeText(jShell.getFileExplorer(), "file.txt", "three\ntwo", false);
    } catch (InvalidPathException e) {
      
    }
    Commands.run(jShell, "cd ..");
    Result test = Commands.run(jShell, "grep -R one folder");
    String actual = test.getMessage();
    String expected = ("three");
    assertEquals(expected, actual);
  }
  
  @Test
  public void normalCase() {
    JShellWindow jShell = new JShellWindow();
    try {
      TextEditor.writeText(jShell.getFileExplorer(), "file.txt", "a\nasd\ndd", false);
    } catch (InvalidPathException e) {
      
    }
    Result test = Commands.run(jShell, "grep a file.txt");
    String actual = test.getMessage();
    String expected = ("a\nasd\n");
    assertEquals(expected, actual);
  }
  
  @Test
  public void errorCase() {
    JShellWindow jShell = new JShellWindow();
    Result test = Commands.run(jShell, "grep a /a/b");
    String actual = test.getErrorMessage();
    String expected = ("The path does not exist. (Argument 0: a)");
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
  
  @Test
  public void noneCase() {
    JShellWindow jShell = new JShellWindow();
    try {
      TextEditor.writeText(jShell.getFileExplorer(), "file.txt", "sdf", false);
    } catch (InvalidPathException e) {
      
    }
    Result test = Commands.run(jShell, "grep a file.txt");
    String actual = test.getMessage();
    String expected = ("");
    assertEquals(expected, actual);
  }
  
  @Test
  public void oneCase() {
    JShellWindow jShell = new JShellWindow();
    try {
      TextEditor.writeText(jShell.getFileExplorer(), "file.txt", "asd", false);
    } catch (InvalidPathException e) {
      
    }
    Result test = Commands.run(jShell, "grep a file.txt");
    String actual = test.getMessage();
    String expected = ("asd\n");
    assertEquals(expected, actual);
  }
}
