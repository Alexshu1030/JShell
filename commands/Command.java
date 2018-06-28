package commands;

import java.util.ArrayList;
import driver.JShell;

public interface Command {

  public void Run(JShell jShell, ArrayList<String> arguments);
  
  public boolean IsValidCommand(ArrayList<String> arguments);
  
  public String GetHelpText();
}
