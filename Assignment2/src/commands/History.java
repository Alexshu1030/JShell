package commands;
import java.util.ArrayList;
import shell.JShellWindow;

public class History implements Command{

  private int singleArg = 1;
  private String commandName = "history";

  /**
   * Returns true if execution of command terminates. 
   * With no parameter, execution prints the whole list of the log of
   * inputs.
   * With parameter, execution prints the truncated list according to
   * the amount specified in integer parameter.
   * @param jShell this is the window that will be printed on
   * @param arguments this is a list containing either null or an int
   * @return true this is true if execution was successful, o/w false
   */
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    ArrayList<String> log = jShell.getLog();
    int totalInputs = log.size();
    int truncAmount = 0;


    if (!arguments.isEmpty()) {
      truncAmount = totalInputs - Integer.parseInt((arguments.get(0)));
    }

    for (int i = 1; i <= totalInputs; i++) {
      if (truncAmount < i) {
        System.out.println(i + ". " + log.get(i - 1));
      }
    } 
    return true;
  }

  
  /**
   * Returns the name of this command, "history"
   * @return commandName this is the command's name
   */
  public String GetCommandName() {

    return commandName;
  }

  
  /**
   * Returns true if arguments are valid for "history", o/w false
   * @param arguments this is a list containing the arguments input
   * @return isValid this is true if arguments are valid, o/w false
   */
  public boolean AreValidArguments(ArrayList<String> arguments) {

    boolean isValid = false;

    if (arguments.isEmpty()) {
      isValid = true;
      // check if argument is an integer
    } else if (arguments.size() == singleArg) {
      try {
        Integer.parseInt(arguments.get(0));
      } catch (Exception NumberFormatException) {
        return isValid;
      }
      isValid = true;
    }
    return isValid;
  }

  
  /**
   * Prints help text that includes documentation of History
   */
  public String GetHelpText() {

    Man.manHistory();
    return "";
  }
}