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
import filesystem.FileExplorer;
import shell.JShellWindow;

public class Pwd implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "pwd";
  /**
   * numOfArguments The correct number of args in the current class
   */
  private int numOfArguments = 0;
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n"
      + "  pwd - Prints current working directory\n" + "DESCRIPTION:\n"
      + "  Prints the full path of the current working" + "directory.\n"
      + "PARAMETERS:\n" + "  There are no parameters available for"
      + " this command.\n" + "RETURNS:\n"
      + "  This command does not return anything.\n" + "EXAMPLE USAGE:\n"
      + "  /#: exit\n" + "    will successfully terminate JShell.\n"
      + "  /#: exit p\n" + "    will not terminate JShell because of the"
      + " additional parameter 'p'.\n" + "  /#:              exit\n"
      + "    will terminate JShell because"
      + " JShell will ignore excess spaces.";

  /**
   * Checks if the arguments given to the command is valid
   * 
   * @param arguments the list of str arguments passed to the command
   * @return isValid true if the command is valid and vice versa
   */
  public Result areValidArguments(ArrayList<String> arguments) {

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

  /**
   * Returns true if execution of command is successful. Execution prints the
   * current working directory path
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be listed
   * @return result the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    
    Result result = areValidArguments(arguments);
    
    // If there were no errors in the arguments then we can run the command
    if (!result.errorOccured()) {

      String messages = null;
      // Get the file explorer
      FileExplorer fileExplorer = jShell.getFileExplorer();
      // Get the path to the working directory
      String workingDirPath = fileExplorer.getWorkingDirectory().getFullPath();
      // Print this path
      messages = workingDirPath;
      result.addMessage(workingDirPath);
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
  
  public boolean canBeRedirected() {
    
    return true;
  }
}
