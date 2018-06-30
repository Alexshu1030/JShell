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

    // Set truncation amount based on input integer
    if (!arguments.isEmpty()) {
      truncAmount = totalInputs - Integer.parseInt((arguments.get(0)));
    }
    // iterate through each input in log and print if within truncation
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
   * Prints help text that includes documentation of History
   */
  public String GetHelpText() {

    Man.manHistory();
    return "";
  }
}