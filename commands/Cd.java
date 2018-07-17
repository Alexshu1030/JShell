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
import exceptions.FileNotFoundException;

public class Cd implements Command {
  /**
   * numOfArguments The correct number of args in the current class
   */
  private int numOfArguments = 1;
  /**
   * commandName The command name of the current class
   */
  private String commandName = "cd";
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:"
      + "  cd DIR - Change the current directory\n" + "DESCRIPTION:\n"
      + "  Changes the current directory to DIR.\n" + "PARAMETERS:\n"
      + "  DIR - The directory to be changed to.\n"
      + " It may be a full path or a relative path.\n" + "RETURNS:\n"
      + "  This command does not return anything.\n" + "EXAMPLE USAGE:\n"
      + "  /#: cd /Dir1\n" + "    will change the current directory to Dir1"
      + " in the root directory.\n" + "  /#: cd ./Dir1/Dir2\n"
      + "    will change the current directory to Dir2, "
      + " which is located in Dir1 which is located in the current"
      + "directory.\n" + "  /#: cd ..\n"
      + "    will change the current directory to its" 
      + "parent directory.";

  /**
   * Returns true if execution of the command is successful. Execution changes
   * current directory to the one specified in arguments
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directory to be changed into
   * @return result the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = new Result(arguments);
    
    FileExplorer explorer = jShell.getFileExplorer();
    Directory currentDirectory = explorer.getWorkingDirectory();

    String path = arguments.get(0);
    boolean succeeded = false;
    
    if (path.equals(".")) {
      // If the user wants to change to the current directory
    }
    else if (path.equals("..")) {
      // If the user wants to change to the parent directory
      Directory workingDir = currentDirectory;
      if (!workingDir.isRootDirectory()) {
        // Get the parent directory of the working directory
        Directory newDir = workingDir.getFileDirectory();
        // Set the working directory to be the parent directory
        explorer.setWorkingDirectory(newDir);
      }
    } else {
      
        Directory newDir = null;
        
        try {
          newDir = explorer.getDirectory(path);
        }
        catch (FileNotFoundException e) {
          result.registerError(0, "The path does not exist.");
        }
        
        // Set the working directory to the directory given by the path
        if (newDir != null)
          explorer.setWorkingDirectory(newDir);
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
   * Checks if the arguments given to the command is valid
   * 
   * @param arguments the list of str arguments passed to the command
   * @return isValid true if the command is valid and vice versa
   */
  public Result areValidArguments(ArrayList<String> arguments) {

    Result result = new Result(arguments);
    
    if (arguments.size() != numOfArguments) {
      result.registerError("Invalid number of arguments.");
    }
    
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
