package commands;

import java.util.ArrayList;
import shell.JShellWindow;

public class Exit implements Command{

  private String commandName = "exit";


  /**
   * Returns true if successfully terminated the jShell
   * @param jShell this is the window that will be printed on
   * @param arguments this is a list containing null
   * @return true this is true if execution was successful, o/w false
   */
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    jShell.setTerminate(true);
    return true;
  }


  /**
   * Returns the name of this command, "exit"
   * @return commandName this is the command's name
   */
  public String getCommandName() {
    return commandName;
  }


  /**
   * Returns true if arguments are valid for "exit", o/w false
   * @param arguments this is a list containing the arguments input
   * @return isValid this is true if arguments are valid, o/w false
   */
  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean isValid = arguments.isEmpty();
    return isValid;
  }

  /**
   * Prints help text that includes documentation of Exit
   */
  public String getHelpText() {
    Man.manExit();
    return "";
  }
}
