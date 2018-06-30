package commands;

import java.util.ArrayList;
import filesystem.FileExplorer;
import shell.JShellWindow;

public class Tree {

  private String commandName = "tree";
  private int numOfArguments = 0;
  private String helpText = "No done yet...";
  
  public boolean AreValidArguments(ArrayList<String> arguments) {
    
    return arguments.size() == numOfArguments;
  }

  public String GetHelpText() {
    
    return helpText;
  }

  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    
    
    
    return true;
  }

  public String GetCommandName() {
    
    return commandName;
  }

}
