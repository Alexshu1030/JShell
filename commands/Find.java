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
import shell.JShellWindow;
import filesystem.*;

public class Find implements Command {
  // set the command name
  private String commandName = "find";
  private String helpText = "NAME:" +
      "  find PATH ... -type [f|d] -name \"STRING\""
      + " - Displays all files or directories with name STRING in PATH." +
  "DESCRIPTION:" +
  "  If -type f then it will find all files with "
      + "name STRING in PATH and display it. If type -d then it will"
      + " find all directories with name STRING in PATH." +
  "PARAMETERS:" +
  "  PATH - The path to find STRING in." +
  "  STRING - The name of the file/directory "
      + "the user is looking for" +
  "  -type [f|d] - f will find files named STRING "
      + "in PATH, d will find directories named STRING in PATH" +
  " -name - parameter to specify STRING as the "
      + "name of the file tehey are looking for." +
  "RETURNS:" +
  "  A list of all files/directories in PATH named "
      + "STRING" +
  "EXAMPLE USAGE:" +
  "  /#: find /user/Desktop -type d -name \"a\"" +
  "    will display all directories in Desktop "
      + "named 'a'." +
  "  /#: find /user/Desktop -type f -name \"b\"" +
  "    will display all directories in Desktop \"\n" + 
      "named \"b\".";
  
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    // set an empty string to be displayed at the end
    String result = "";
    FileExplorer explorer = jShell.getFileExplorer();
    // get the # of arguments
    int size = arguments.size();
    // get the file name from the last element and substring it
    String fileName = arguments.get(size-1);
    fileName = fileName.substring(1, fileName.length()-1);
    // set up a folders string arraylist, add each of the folders specified
    // in the arguments to it
    ArrayList<String> folders = new ArrayList<String>();
    for (int i = 0; i < size-4; i++) {
      folders.add(arguments.get(i));
    }
    // for each folder in the arraylist
    for (int j = 0; j < folders.size(); j++) {
      // get the directory specified by the folder path
      Directory dir = (Directory) explorer.getFile(folders.get(j));
      // if the file is found within the folder
      if (dir.getFile(fileName) != null) {
        // if we are looking for a file
        if (arguments.get(size-3) == "f") {
          // if the file found is a file, add its path to result
          if (dir.getFile(fileName).isDirectory() == false) {
            result += (folders.get(j) + fileName + " ");
          }
       // if we are looking for a directory
        } else if (arguments.get(size-3) == "d") {
       // if the file found is a directory, add its path to result
          if (dir.getFile(fileName).isDirectory() == true) {
            result += (folders.get(j) + fileName + " ");
          }
        }
      }
    }
    // if the result is empty, output error message
    if (result == "") {
      System.out.println("File not found");
    // otherwise output the filepaths
    } else {
      System.out.println(result);
    }
    return true;
  }

  public String getCommandName() {
    return commandName;
  }

  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean valid = false;
    int size = arguments.size();
    // if the last arguments starts and ends with "
    if (arguments.get(size-1).startsWith("\"") &&
        arguments.get(size-1).endsWith("\"")) {
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
    }
    // return the valid bool
    return valid;
  }

  public String getHelpText() {

    return helpText;
  }
  
}
