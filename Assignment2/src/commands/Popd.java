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
package commands;

import java.util.ArrayList;
import exceptions.EmptyStackException;
import filesystem.DirectoryStack;
import shell.JShellWindow;

public class Popd implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "popd";
  /**
   * helpText The help text for the current class
   */
  private String helpText =
      "NAME:\n" + "  popd - Sets the current working directory "
          + "to the one at the top of the directory stack.\n" 
          + "DESCRIPTION:\n"
          + "  Removes the directory at the top of the "
          + "directory stack and sets the current working directory to "
          + "that directory.\n" + "PARAMETERS:\n"
          + "  There are no parameters available for this" + " command.\n"
          + "RETURNS:\n" + "  Will return an error message if the "
          + "directory stack is empty.\n" + "EXAMPLE USAGE:\n"
          + "If the directory stack is not empty.\n" + "  /#: popd\n"
          + "    will change the current working directory to "
          + "the one at the top of the stack.\n"
          + "If the directory stack is empty.\n" + "  /#: popd\n"
          + "    Will return an error message.\n";

  /**
   * Returns true if execution of command is successful. Execution sets current
   * working directory to the top of the directory stack
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be popped
   * @return result the output to the shell
   */
  public String run(JShellWindow jShell, ArrayList<String> arguments) {
    
    Result result = new Result(arguments);
    
    try {
      DirectoryStack.stack.popd(jShell);
    }
    catch (EmptyStackException e) {
      result.registerError("The directory stack is empty");
    }
    
    String messages = "";
    return messages;
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
  public boolean areValidArguments(ArrayList<String> arguments) {
    // return true if there are 0 arguments
    boolean isValid = arguments.size() == 0;
    return isValid;
  }

  /**
   * Returns the help text for this command.
   * 
   * @return the help text for this command
   */
  public String getHelpText() {

    return helpText;
  }

}
