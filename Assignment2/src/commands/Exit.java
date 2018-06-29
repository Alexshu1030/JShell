package commands;

import java.util.ArrayList;
import shell.JShellWindow;

public class Exit implements Command{

  private String commandName = "exit";

  @Override
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    jShell.setTerminate(true);
    return true;
  }

  @Override
  public String GetCommandName() {
    return commandName;
  }

  @Override
  public boolean IsValidCommand(String commandName,
      ArrayList<String> arguments) {
    if (commandName.equals(this.commandName) && arguments.isEmpty()) {
      return true;
    } else { 
      return false;
    }
  }

  @Override
  public String GetHelpText() {
    Man.manExit();
    return "";
  }

}
