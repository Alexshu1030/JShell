package commands;

import java.util.ArrayList;
import filesystem.DirectoryStack;
import shell.JShellWindow;

public class Popd implements Command{
  private String commandName = "popd";
  private String helpText = "NAME:\n" +
      "  popd - Sets the current working directory "
      + "to the one at the top of the directory stack.\n" +
  "DESCRIPTION:\n" +
  "  Removes the directory at the top of the "
      + "directory stack and sets the current working directory to "
      + "that directory.\n" +
  "PARAMETERS:\n" +
  "  There are no parameters available for this"
      + " command.\n" +
  "RETURNS:\n" +
  "  Will return an error message if the "
      + "directory stack is empty.\n" +
  "EXAMPLE USAGE:\n" +
  "If the directory stack is not empty.\n" +
  "  /#: popd\n" +
  "    will change the current working directory to "
      + "the one at the top of the stack.\n" +
  "If the directory stack is empty.\n" +
  "  /#: popd\n" +
  "    Will return an error message.\n";

  /**
   * Returns true if execution of "popd" is successful. Execution sets current
   * working directory to the top of the directory stack
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be popped
   * @return result true if execution was successful
   */
  public String run(JShellWindow jShell, ArrayList<String> arguments) {
    // Returns "" if it succeeds
    DirectoryStack.stack.popd(jShell);
    String messages = "";
    return messages;
  }

  /**
   * Returns the command name, "popd"
   * 
   * @return commandName the command name
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
   * Gets the help text of the command
   * 
   * @return helpText the help text of command "popd"
   */
  public String getHelpText() {

    return helpText;
  }

}
