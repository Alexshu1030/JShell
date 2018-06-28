package commands;

import filesystem.*;
import shell.JShellWindow;
import java.util.ArrayList;
import java.util.Arrays;

public class Cd implements Command {
  
  private int numOfArguments = 1;
  private String commandName = "cd";
  
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    
    FileExplorer explorer = jShell.GetFileExplorer();
    Directory currentDirectory = explorer.getWorkingDirectory();
    
    String path = arguments.get(0);
    boolean succeeded = false;
    
    if (path.equals("..")) {
      Directory workingDir = explorer.getWorkingDirectory();
      if (!workingDir.isRootDirectory()) {
        Directory newDir = workingDir.getFileDirectory();
        explorer.setWorkingDirectory(newDir);
      }
    }
    else if (path.equals(".")) {
      succeeded = true;
    }
    else {
      Directory newDir = (Directory)explorer.getFile(path);
      explorer.setWorkingDirectory(newDir);
    }
    
    return succeeded;
  }
  
  
  public String GetCommandName() {
    return commandName;
  }
  
  public boolean IsValidCommand(String commandName, 
      ArrayList<String> arguments) {
    boolean isValid = false;
    if (arguments.size() == numOfArguments) {
      isValid = true;
    }
    return isValid;
  }
  
  public String GetHelpText() {
    Man.manCd();
    
    return "";
  }
}
