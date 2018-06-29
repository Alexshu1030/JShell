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

package commands;

import filesystem.*;
import shell.JShellWindow;
import java.util.ArrayList;
import java.util.Arrays;

public class Cd implements Command {
  
  private int numOfArguments = 1;
  private String commandName = "cd";
  
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    
    FileExplorer explorer = jShell.GetFileExplorer();
    Directory currentDirectory = explorer.getWorkingDirectory();
    
    String path = arguments.get(0);
    boolean succeeded = false;
    
    if (path.equals("..")) {
      Directory workingDir = explorer.getWorkingDirectory();
      if (!workingDir.isRootDirectory()) {
        Directory newDir = workingDir.getFileDirectory();
        explorer.setWorkingDirectory(newDir);
      }
    }
    else if (path.equals(".")) {
      succeeded = true;
    }
    else {
      Directory newDir = (Directory)explorer.getFile(path);
      explorer.setWorkingDirectory(newDir);
    }
    
    return succeeded;
  }
  
  
  public String GetCommandName() {
    return commandName;
  }
  
  public boolean AreValidArguments(ArrayList<String> arguments) {
    
    boolean isValid = false;
    if (arguments.size() == numOfArguments) {
      isValid = true;
    }
    return isValid;
  }
  
  public String GetHelpText() {
    Man.manCd();
    
    return "";
  }
}
