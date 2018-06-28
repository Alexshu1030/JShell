package commands;

import java.util.ArrayList;
import driver.JShell;
import shell.JShellWindow;

public interface Command {

  public boolean Run(JShellWindow jShell, ArrayList<String> arguments);
  
  public String GetCommandName();
  
  public boolean IsValidCommand(ArrayList<String> arguments);
  
  public String GetHelpText();
}
