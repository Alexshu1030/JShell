package commands;

import java.util.ArrayList;
import shell.JShellWindow;

public class Exit implements Command{

  private String commandName = "exit";

  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    jShell.setTerminate(true);
    return true;
  }

  public String GetCommandName() {
    return commandName;
  }

  public boolean AreValidArguments(ArrayList<String> arguments) {
    
    return arguments.isEmpty();
  }

  public String GetHelpText() {
    Man.manExit();
    return "";
  }

}
