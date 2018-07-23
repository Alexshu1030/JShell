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
import shell.JShellWindow;

public class Grep implements Command {

  /**
   * commandName The command name of the current class
   */
  private String commandName = "grep";
  /**
   * recursiveArg the argument that indicates recursively iterate thru folder
   */
  private String recursiveArg = "-R";
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n"
      + "grep [-R] REGEX PATH - print lines matching a pattern described by"
      + "REGEX in PATH. Prints lines from a file if no -R is supplied or from"
      + " a folder if -R is supplied and the PATH is a folder. \n"
      + "PARAMETERS:\n"
      + "-R - The option to search recursively through a folder (scanning "
      + "all files).\n"
      + "REGEX - The expression that we are looking for in the PATH.\n"
      + "PATH - The file or folder that we are searching from.\n" 
      + "RETURNS:\n"
      + "All lines containing REGEX inside PATH.\n" + "EXAMPLE USAGE:\n"
      + "grep -R a b\n"
      + "This will recursively return all lines containing a in all files "
      + "inside b\n"
      + "grep a b.txt\n" + "This will return all lines containing a in b.txt";

  /**
   * Returns true if successfully terminated the jShell
   * 
   * @param jShell this is the window that will be printed on
   * @param arguments this is a list containing null
   * @return true the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = areValidArguments(arguments);
    if (!result.errorOccured()) {
      if (arguments.get(0).equals("-R")) {

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
   * Returns true if arguments are valid, o/w false
   * 
   * @param arguments this is a list containing the arguments input
   * @return isValid this is true if arguments are valid, o/w false
   */
  public Result areValidArguments(ArrayList<String> arguments) {

    Result result = new Result(arguments);
    // if there are less than 2 arguments in the call log the error
    if (arguments.size() < 2) {
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
