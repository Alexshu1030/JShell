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

public class Exit implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "exit";
  /**
   * helpText The help text for the current class
   */
  private String helpText =
      "NAME:\n" + "  exit - Terminates the current JShell" + " process.\n"
          + "DESCRIPTION:\n" + "  Prevents any other code in JShell from"
          + " being run by reaching the exit condition in"
          + " the while loop. The exit condition is if command 'exit'"
          + " is entered by user\n" + "PARAMETERS:\n"
          + "  There are no parameters available for" + " this command.\n"
          + "RETURNS:\n" + "  This command does not return anything.\n"
          + "EXAMPLE USAGE:\n" + "  /#: exit\n"
          + "    will successfully terminate JShell.\n" + "  /#: exit p\n"
          + "    will not terminate JShell because of the"
          + " additional parameter 'p'.\n" + "  /#:              exit\n"
          + "    will terminate JShell because"
          + " JShell will ignore excess spaces.";

  /**
   * Returns true if successfully terminated the jShell
   * 
   * @param jShell this is the window that will be printed on
   * @param arguments this is a list containing null
   * @return true the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    
    Result result = new Result(arguments);
    // Terminate
    jShell.setTerminate(true);
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
  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean isValid = arguments.isEmpty();
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
  
  public boolean canBeRedirected() {
    
    return false;
  }
}
