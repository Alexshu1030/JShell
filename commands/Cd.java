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
  private String helpText = "NAME:" +
      "  cd DIR - Change the current directory\n" +
      "DESCRIPTION:\n" +
      "  Changes the current directory to DIR.\n" +
      "PARAMETERS:\n" +
      "  DIR - The directory to be changed to.\n"
      + " It may be a full path or a relative path.\n" +
      "RETURNS:\n" +
      "  This command does not return anything.\n" +
      "EXAMPLE USAGE:\n" +
      "  /#: cd /Dir1\n" +
      "    will change the current directory to Dir1"
      + " in the root directory.\n" +
      "  /#: cd ./Dir1/Dir2\n" +
      "    will change the current directory to Dir2, "
      + " which is located in Dir1 which is located in the current"
      + "directory.\n" +
      "  /#: cd ..\n" +
      "    will change the current directory to its"
      + "parent directory.\n";

  public String run(JShellWindow jShell, ArrayList<String> arguments) {

    FileExplorer explorer = jShell.getFileExplorer();
    Directory currentDirectory = explorer.getWorkingDirectory();

    String path = arguments.get(0);
    boolean succeeded = false;

    if (path.equals("..")) {
      succeeded = true;
      Directory workingDir = explorer.getWorkingDirectory();
      if (!workingDir.isRootDirectory()) {
        Directory newDir = workingDir.getFileDirectory();
        explorer.setWorkingDirectory(newDir);
      }
    } else if (path.equals(".")) {
      succeeded = true;
    } else {
      try {
        if (!explorer.getFile(path).equals(null)) {
          Directory newDir = (Directory)explorer.getFile(path);
          explorer.setWorkingDirectory(newDir);
          succeeded = true;
        }
      } catch (Exception NullPointerException) {
        succeeded = false;
      }
    }

    return "";
  }


  public String getCommandName() {
    return commandName;
  }

  public boolean areValidArguments(ArrayList<String> arguments) {

    boolean isValid = false;
    if (arguments.size() == numOfArguments) {
      isValid = true;
    }
    return isValid;
  }

  public String getHelpText() {

    return helpText;
  }
}
