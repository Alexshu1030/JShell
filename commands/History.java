package commands;
import java.util.ArrayList;
import shell.JShellWindow;

public class History implements Command{

  private String commandName = "history";

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
        Integer.parseInt(arguments.get(0));
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