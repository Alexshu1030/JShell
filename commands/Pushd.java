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

import java.util.ArrayList;
import filesystem.FileExplorer;
import filesystem.Directory;
import filesystem.DirectoryStack;
import shell.JShellWindow;

public class Pushd implements Command {
  
  private int numOfArguments = 1;
  private String helpText = "NAME:\n" +
      "  pushd DIR - Pushes the current working directory"
      + " into a stack, then change the current working directory"
      + " to DIR.\n" +
  "DESCRIPTION:\n" +
  "  Saves the current working directory into a "
      + "stack of directories, then changes the current working "
      + "directory to DIR so that the old working directory could be"
      + "returned. The directory stack is dynamic and changes "
      + "depending on pushd and popd commands.\n" +
  "PARAMETERS:\n" +
  "  DIR - The new current working directory that the"
      + " user may eventually want to return to through the directory"
      + " stack.\n" +
  "RETURNS:\n" +
  "  This command does not return anything.\n" +
  "EXAMPLE USAGE:\n" +
  "  /#: pushd Dir1\n" +
  "    will add the current working directory to"
      + " the stack and change the current working directory to Dir1.\n";
  
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    FileExplorer explorer = jShell.getFileExplorer();
    // Get the path of the directory that we want to push
    String path = arguments.get(0);
    // Get the directory at the path
    Directory newDir = (Directory)explorer.getFile(path);
    DirectoryStack.stack.pushd(jShell, newDir);
    return true;
  }

  public String getCommandName() {
    // TODO Auto-generated method stub
    return "pushd";
  }

  public boolean areValidArguments(ArrayList<String> arguments) {
    return arguments.size() == numOfArguments;
  }

  public String getHelpText() {
    
    return helpText;
  }

}