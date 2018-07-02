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
import shell.JShellWindow;

public class Pwd implements Command {

  private String commandName = "pwd";
  private int numOfArguments = 0;
  private String helpText = "NAME:\n" +
      "  pwd - Prints current working directory\n" +
      "DESCRIPTION:\n" +
      "  Prints the full path of the current working"
          + "directory.\n" +
      "PARAMETERS:\n" +
      "  There are no parameters available for"
          + " this command.\n" +
      "RETURNS:\n" +
      "  This command does not return anything.\n" +
      "EXAMPLE USAGE:\n" +
      "  /#: exit\n" +
      "    will successfully terminate JShell.\n" +
      "  /#: exit p\n" +
      "    will not terminate JShell because of the"
          + " additional parameter 'p'.\n" +
      "  /#:              exit\n" +
      "    will terminate JShell because"
          + " JShell will ignore excess spaces.\n";
  
  /**
  * Checks if the arguments given to the command is valid
  * @param arguments the list of str arguments passed to the command
  * @return isValid true if the command is valid and vice versa
  */
  public boolean areValidArguments(ArrayList<String> arguments) {
    // if there are 0 arguments return true
    boolean isValid = arguments.size() == numOfArguments;
    return isValid;
  }

  /**
   * Gets the help text of the command
   * @return helpText the help text of command "pwd"
   */
  public String getHelpText() {

    return helpText;
  }

  /**
   * Returns true if execution of "pwd" is successful. Execution
   * prints the current working directory path
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be listed
   * @return result true if execution was successful
   */
  public String run(JShellWindow jShell, ArrayList<String> arguments) {
    String messages = null;
    // Get the file explorer
    FileExplorer fileExplorer = jShell.getFileExplorer();
    // Get the path to the working directory
    String workingDirPath = fileExplorer.getWorkingDirectory().getFullPath();
    // Print this path
    messages = workingDirPath;

    return messages;
  }

  /**
   * Returns the command name, "pwd"
   * @return commandName the command name
   */
  public String getCommandName() {

    return commandName;
  }
}