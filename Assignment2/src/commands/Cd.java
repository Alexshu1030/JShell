package commands;

import filesystem.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Cd implements Command {
  private int numOfArguments = 0;
  private String commandName = "cd";
  public Cd (String path) {
   
  }
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    Directory currentDirectory = FileExplorer.getWorkingDirectory();
    if (path == "..") {
      FileExplorer.setWorkingDirectory(currentDirectory.getFileDirectory());
    }
    else {
      boolean valid = true;
      ArrayList<String> pathFolders = new ArrayList<String>();
      pathFolders = (ArrayList<String>) Arrays.asList(path.split("\\"));
      FileExplorer.setWorkingDirectory(FileExplorer.getRootDirectory());
      for (int i = 0; i < pathFolders.size(); i++) {
        if (valid) {
          boolean found = false;
          filesystem.File[] currentContents = (File[]) FileExplorer.getWorkingDirectory().getFileContents();
          for (int j = 0; j < currentContents.size(); j++) {
            if (currentContents[j].getFileName() == pathFolders.get(i)) {
              FileExplorer.setWorkingDirectory(currentContents[j]);
              found = true;
            }
            if (!found) {
              i = pathFolders.size();
              System.out.println("Folder not found");
            }
          }
        }
      }
    }
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
  }
}
