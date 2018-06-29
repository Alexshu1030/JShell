package commands;
import java.util.ArrayList;
import shell.JShellWindow;

public class History implements Command{

  private String commandName = "history";

  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    ArrayList<String> log = jShell.getLog();
    int truncAmount;
    if (arguments.isEmpty()) {
      truncAmount = log.size();
    } else {
      truncAmount = (Integer.parseInt((arguments.get(0))));
    }
    for (int i = truncAmount; i >= 0; i--) {
      System.out.println(truncAmount - i + ". " + log.get(i));
    }
    return true;
  }

  public String GetCommandName() {

    return commandName;
  }

  public boolean AreValidArguments(ArrayList<String> arguments) {

    boolean isValid = false;

    if (arguments.isEmpty()) {
      isValid = true;
      // check if argument is an integer
    } else if (arguments.size() == 1) {
      try {
        Integer.parseInt(arguments.get(1));
      } catch (Exception NumberFormatException) {
        return isValid;
      }
      isValid = true;
    }
    return isValid;
  }

  public String GetHelpText() {

    Man.manHistory();
    return "";
  }
}