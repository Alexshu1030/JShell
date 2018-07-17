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
import exceptions.FileNotFoundException;
import shell.JShellWindow;
import filesystem.*;

public class Find implements Command {

  /**
   * commandName The command name of the current class
   */
  private String commandName = "find";
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n"
      + "  find PATH ... -type [f|d] -name \"STRING\""
      + " - Displays all files or directories with name STRING in PATH.\n"
      + "DESCRIPTION:\n" + "  If -type f then it will find all files with "
      + "name STRING in PATH and display it. If type -d then it will"
      + " find all directories with name STRING in PATH.\n" + "PARAMETERS:\n"
      + "  PATH - The path to find STRING in.\n"
      + "  STRING - The name of the file/directory "
      + "the user is looking for\n"
      + "  -type [f|d] - f will find files named STRING "
      + "in PATH, d will find directories named STRING in PATH\n"
      + " -name - parameter to specify STRING as the "
      + "name of the file tehey are looking for.\n" + "RETURNS:\n"
      + "  A list of all files/directories in PATH named " + "STRING\n"
      + "EXAMPLE USAGE:\n" + "  /#: find /user/Desktop -type d -name \"a\"\n"
      + "    will display all directories in Desktop " + "named 'a'.\n"
      + "  /#: find /user/Desktop -type f -name \"b\"\n"
      + "    will display all directories in Desktop \"\n\n" 
      + "named \"b\".";

  /**
   * Returns the text in the arguments if there is not outfile is specified.
   * Otherwise returns an empty string.
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list of arguments used to change the behavior of the
   *        command
   * @return the result to be printed to the JShell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = areValidArguments(arguments);
    
    // If there were no errors in the arguments then we can run the command
    if (!result.errorOccured()) {
      
      FileExplorer explorer = jShell.getFileExplorer();
  
      int numOfPaths = arguments.size() - 4;
      ArrayList<Directory> directories = new ArrayList<Directory>();
  
      for (int i = 0; i < numOfPaths; i++) {
        
        Directory dir = null;
        
        try {
          dir = explorer.getDirectory(arguments.get(i));
        }
        catch (FileNotFoundException e) {
          result.logError(i, "The file does not exist.");
        }
  
        if (dir != null)
          directories.add(dir);
      }
  
      String type = arguments.get(arguments.size() - 3);
      String name = arguments.get(arguments.size() - 1);
      // We need to remove the quotations around the name
      name = name.substring(1, name.length() - 1);
  
      boolean isDir = type.equals("d");
  
      String message = "";
  
      for (int i = 0; i < directories.size(); i++) {
  
        Directory dir = directories.get(i);
        ArrayList<File> files = (ArrayList<File>) dir.getFileContents();
  
        // Iterate over the files in each directory and check if the meet our
        // requirements
        for (int f = 0; f < files.size(); f++) {
          File file = files.get(f);
          if (file.isDirectory() && isDir || !file.isDirectory() && !isDir) {
            // The file matches the type we want
            if (file.getFileName().equals(name)) {
              // The file has the same name.
              message += file.getFullPath() + "\n";
              result.addMessage(file.getFullPath());
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * Returns the name of this command
   * 
   * @return commandName this is the command's name
   */
  public String getCommandName() {

    return commandName;
  }

  /**
   * Returns whether the arguments meet the base requirements for the command.
   * 
   * @param arguments the list of arguments for the command
   * @return is true if the args are valid. Otherwise false.
   */
  public Result areValidArguments(ArrayList<String> arguments) {

    Result result = new Result(arguments);
    
    boolean valid = false;
    int size = arguments.size();

    if (arguments.size() == 5) {
      
      // The second last arg must be -name
      if (!arguments.get(size - 2).equals("-name"))
        result.logError(size - 2, "Invalid argument.");
      
      // The third last arg must be either d or f
      if (!arguments.get(size - 3).equals("d") &&
          !arguments.get(size - 3).equals("f"))
          result.logError(size - 3, "Invalid argument.");
        
      // The forth last arg must be -type
      if (arguments.get(size - 4).equals("-type"))
        result.logError(size - 4, "Invalid argument.");
    }
    else
      result.logError("Invalid number of arguments.");
    
    return result;
  }

  /**
   * Returns the help text for this command.
   * 
   * @return the help text for this command
   */
  public String getHelpText() {

    return helpText;
  }

  public boolean canBeRedirected() {
    
    return true;
  }
}
