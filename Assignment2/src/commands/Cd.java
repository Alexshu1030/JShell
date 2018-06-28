package commands;

import filesystem.*;
import shell.JShellWindow;
import java.util.ArrayList;
import java.util.Arrays;

public class Cd implements Command {
  
  private int numOfArguments = 0;
  private String commandName = "cd";
  
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    
    FileExplorer explorer = jShell.GetFileExplorer();
    Directory currentDirectory = explorer.getWorkingDirectory();
    
    String path = arguments.get(0);
    boolean succeeded = false;
    
    if (path == "..") {
      explorer.setWorkingDirectory(currentDirectory.getFileDirectory());
    }
    else {
      boolean valid = true;
      ArrayList<String> pathFolders = new ArrayList<String>();
      pathFolders = (ArrayList<String>) Arrays.asList(path.split("\\"));
      explorer.setWorkingDirectory(FileExplorer.getRootDirectory());
      for (int i = 0; i < pathFolders.size(); i++) {
        if (valid) {
          boolean found = false;
          ArrayList<File> currentContents = (ArrayList<File>) explorer.getWorkingDirectory().getFileContents();
          for (int j = 0; j < currentContents.size(); j++) {
            if (currentContents.get(j).getFileName() == pathFolders.get(i)) {
              explorer.setWorkingDirectory((Directory)currentContents.get(j));
              succeeded = true;
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
