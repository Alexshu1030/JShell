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
    try {
      TextEditor.writeText(jShell.getFileExplorer(), "folder/file.txt", "three\ntwo", false);
    } catch (InvalidPathException e) {
      
    }
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
