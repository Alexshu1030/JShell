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

import java.awt.List;
import java.util.ArrayList;
import shell.JShellWindow;
import filesystem.*;

public class Find implements Command {
  // set the command name
  private String commandName = "find";
  private String helpText = "NAME:\n" +
      "  find PATH ... -type [f|d] -name \"STRING\""
      + " - Displays all files or directories with name STRING in PATH.\n" +
  "DESCRIPTION:\n" +
  "  If -type f then it will find all files with "
      + "name STRING in PATH and display it. If type -d then it will"
      + " find all directories with name STRING in PATH.\n" +
  "PARAMETERS:\n" +
  "  PATH - The path to find STRING in.\n" +
  "  STRING - The name of the file/directory "
      + "the user is looking for\n" +
  "  -type [f|d] - f will find files named STRING "
      + "in PATH, d will find directories named STRING in PATH\n" +
  " -name - parameter to specify STRING as the "
      + "name of the file tehey are looking for.\n" +
  "RETURNS:\n" +
  "  A list of all files/directories in PATH named "
      + "STRING\n" +
  "EXAMPLE USAGE:\n" +
  "  /#: find /user/Desktop -type d -name \"a\"\n" +
  "    will display all directories in Desktop "
      + "named 'a'.\n" +
  "  /#: find /user/Desktop -type f -name \"b\"\n" +
  "    will display all directories in Desktop \"\n\n" + 
      "named \"b\".\n";
  
  public String run(JShellWindow jShell, ArrayList<String> arguments) {
    
    int numOfPaths = arguments.size() - 4;
    String[] paths = new String[numOfPaths];
    
    for (int i = 0; i < numOfPaths; i++) {
      paths[i] = arguments.get(i);
    }
    
    String type = arguments.get(arguments.size() - 3);
    String name = arguments.get(arguments.size() - 1);
    
    FileExplorer explorer = jShell.getFileExplorer();
    
    return "";
  }

  public String getCommandName() {
    return commandName;
  }

  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean valid = false;
    int size = arguments.size();
    // if the 2nd last argument is -name
    if (arguments.get(size-2) == "-name") {
      // if the 3rd last argument is either d or f
      if (arguments.get(size-3) == "d" || arguments.get(size-3) == "f") {
        // if the 4th last argument is -type
        if (arguments.get(size-4) == "-type") {
          // if arguments have >= 6 elements
          if (arguments.size() >= 5) {
            // the command is valid
            valid = true;
          }
        }
      }
    }
    // return the valid bool
    return valid;
  }

  public String getHelpText() {

    return helpText;
  }
  
}
