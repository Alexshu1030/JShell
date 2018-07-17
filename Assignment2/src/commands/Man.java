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

public class Man implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "man";
  /**
   * command The command name of the current class
   */
  private int numOfArguments = 1;
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n"
      + "  man CMD - Displays documentation for CMD \n" + "DESCRIPTION:\n"
      + "  Displays name, description, possible parameters"
      + " possible return messages, and example usage of cmd\n"
      + "PARAMETERS:\n" + "  CMD - The command for which the documentation "
      + "will be displayed.\n" + "RETURNS:\n" + "  Documentation for CMD\n"
      + "EXAMPLE USAGE:\n" + "  /#: man man\n"
      + "    will display documentation for man.\n" + "  /#: man nam\n"
      + "    Will return an error message because nam"
      + " is not a valid command.\n";

  /**
   * Returns true if execution of command is successful. Execution prints the
   * manual of the command in arguments
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the command to have its manual printed
   * @return result the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    
    Result result = new Result(arguments);
    
    String messages = null;
    String cmdName = arguments.get(0);
    
    Command command = Commands.getCommand(cmdName);
    
    if (command != null) {
      messages = command.getHelpText();
      result.addMessage(command.getHelpText());
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
  public boolean areValidArguments(ArrayList<String> arguments) {

    boolean isValid = false;
    // return true if there is 1 argument
    if (arguments.size() == numOfArguments) {
      isValid = true;
    }
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
