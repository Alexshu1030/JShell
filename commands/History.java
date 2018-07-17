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

public class History implements Command {
  /**
   * singleArg the correct num of arguments (1)
   */
  private int singleArg = 1;
  /**
   * commandName The command name of the current class
   */
  private String commandName = "history";
  /**
   * helpText The help text for the current class
   */
  private String helpText =
      "NAME:\n" + "  history [number] - Prints recent commands" + " entered\n"
          + "DESCRIPTION:\n" + "  Prints recent commands with each command on "
          + "their own line. There will be two columns. The first column "
          + "numbers the commands where the most recent is the highest"
          + " number. The second column contains the commands.\n"
          + "PARAMETERS:\n" + "  number - This parameter lets the user return "
          + "the last 'number' commands entered rather than all commands."
          + " An optional parameter.\n" + "RETURNS:\n"
          + "  Will return a list of commands entered.\n" + "EXAMPLE USAGE:\n"
          + "If no other command was entered.\n" + "  /#: history\n"
          + "    1. history\n" + "If we were to enter the pwd command first.\n"
          + "  /#: history\n" + "    1. pwd\n" + "    2. history\n"
          + "If we were to enter the pwd command twice first.\n"
          + "  /#: history 2\n" + "    1. pwd\n" + "    2. history\n";

  /**
   * Returns true if execution of command terminates. With no parameter,
   * execution prints the whole list of the log of inputs. With parameter,
   * execution prints the truncated list according to the amount specified in
   * integer parameter.
   * 
   * @param jShell this is the window that will be printed on
   * @param arguments this is a list containing either null or an int
   * @return message the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = new Result(arguments);
    
    ArrayList<String> log = jShell.getLog();
    int totalInputs = log.size();
    int truncAmount = 0;

    // Set truncation amount based on input integer
    if (!arguments.isEmpty()) {
      truncAmount = totalInputs - Integer.parseInt((arguments.get(0)));
    }

    String message = "";

    // iterate through each input in log and print if within truncation
    for (int i = 1; i <= totalInputs; i++) {
      if (truncAmount < i) {
        message += i + ". " + log.get(i - 1) + "\n";
        result.addMessage(i + ". " + log.get(i - 1));
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
   * Returns true if arguments are valid for the command, o/w false
   * 
   * @param arguments this is a list containing the arguments input
   * @return isValid this is true if arguments are valid, o/w false
   */
  public boolean areValidArguments(ArrayList<String> arguments) {

    boolean isValid = false;
    // there is no argument parameter, it is valid
    if (arguments.isEmpty()) {
      isValid = true;
      // check if argument is a, single, integer
    } else if (arguments.size() == singleArg) {
      // try parsing argument parameter into integer, if success -> int
      try {
        Integer.parseInt(arguments.get(0));
        // otherwise, it is not an integer and is not valid
      } catch (Exception NumberFormatException) {
        return isValid;
      }
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
