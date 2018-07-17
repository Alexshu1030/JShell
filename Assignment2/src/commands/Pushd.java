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
import commandsystem.Command;
import commandsystem.Result;
import exceptions.FileNotFoundException;
import filesystem.FileExplorer;
import filesystem.Directory;
import filesystem.DirectoryStack;
import shell.JShellWindow;

public class Pushd implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "pushd";
  /**
   * numOfArguments The correct number of args in the current class
   */
  private int numOfArguments = 1;
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n"
      + "  pushd DIR - Pushes the current working directory"
      + " into a stack, then change the current working directory"
      + " to DIR.\n" + "DESCRIPTION:\n"
      + "  Saves the current working directory into a "
      + "stack of directories, then changes the current working "
      + "directory to DIR so that the old working directory could be"
      + "returned. The directory stack is dynamic and changes "
      + "depending on pushd and popd commands.\n" + "PARAMETERS:\n"
      + "  DIR - The new current working directory that the"
      + " user may eventually want to return to through the directory"
      + " stack.\n" + "RETURNS:\n"
      + "  This command does not return anything.\n" + "EXAMPLE USAGE:\n"
      + "  /#: pushd Dir1\n" + "    will add the current working directory to"
      + " the stack and change the current working directory to Dir1.";

  /**
   * Returns true if execution of the command is successful. Execution pushes
   * current working directory into a stack and cds into a specified directory
   * given in arguments
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be pushed
   * @return result the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = areValidArguments(arguments);
    
    // If there were no errors in the arguments then we can run the command
    if (!result.errorOccured()) {
        
      FileExplorer explorer = jShell.getFileExplorer();
      String messages = null;
      // Get the path of the directory that we want to push
      String path = arguments.get(0);
      
      // Get the directory at the path
      Directory newDir = null;
      
      try {
        newDir = explorer.getDirectory(path);
      }
      catch (FileNotFoundException e) {
        result.logError(0, "The path does not exist.");
      }
      
      // If the directory exists then add it to the stack
      if (newDir != null)
        DirectoryStack.stack.pushd(jShell, newDir);
    }
    
    return result;
  }

  /**
   * Returns the name of this command
   * 
   * @return commandName this is the command's name
   */
  public String getCommandName() {
    // TODO Auto-generated method stub
    return commandName;
  }

  /**
   * Checks if the arguments given to the command is valid
   * 
   * @param arguments the list of str arguments passed to the command
   * @return isValid true if the command is valid and vice versa
   */
  public Result areValidArguments(ArrayList<String> arguments) {
    // return true if there are 1 arguments
    Result result = new Result(arguments);
    
    if (arguments.size() != numOfArguments) {
      result.logError("Invalid number of arguments.");
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
