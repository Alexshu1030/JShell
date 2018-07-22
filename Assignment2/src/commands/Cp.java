package commands;

import java.util.ArrayList;
import commandsystem.Command;
import commandsystem.Result;
import exceptions.*;
import filesystem.*;
import shell.JShellWindow;

public class Cp implements Command{
  
  private String commandName = "mv";
  private String helpText = "";

  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    
    Result result = areValidArguments(arguments);
    
    // Ensure that the arguments are valid
    if (!result.errorOccured()) {
      
      FileExplorer explorer = jShell.getFileExplorer();
      
      // Get the arguments
      String oldPath = arguments.get(0);
      String newPath = arguments.get(1);
      
      File oldFile = null;
      
      // Try to get the file we are copying
      try {
        oldFile = explorer.getFile(oldPath);
      }
      catch (FileNotFoundException e) {
        result.logError(0, "The old path does not exist.");
      }
      
      if (oldFile != null) {
        if (oldFile.isDirectory()) {
          // Get the files in the directory
          ArrayList<File> files = (ArrayList<File>)((Directory)
              oldFile).getFileContents();
          // Iterate over the files in the directory and copy them
          for (int i = 0; i < files.size(); i++) {
            // Get the current file and create a copy of it at the new path
            File currentFile = files.get(i);
            copyFile(explorer, result, currentFile, newPath);
          }
        }
        else {
          // Get the old file and create a copy of it at the new path
          copyFile(explorer, result, oldFile, newPath);
        }
      }
    }
    return result;
  }
  
  private void copyFile(FileExplorer explorer, Result result, 
      File originalFile, String path) {
    
    // We have create a copy of the old file. This is a free floating
    // file and is not in any directory right now.
    File fileCopy = originalFile.createCopy();
    // Add the file into the directory at the new path
    try {
    explorer.addFile(path, fileCopy);
    }
    catch (PathExistsException e) {
      result.logError(1, "A file of the same name already exists at" +
          " the new path.");
    }
    catch (InvalidPathException e) {
      result.logError(1, "Invalid path.");
    }
  }

  public Result areValidArguments(ArrayList<String> arguments) {
    
    Result result = new Result(arguments);
    
    if (arguments.size() != 2) {
      result.logError("Invalid number of arguments.");
    }
    
    return result;
  }
  
  public String getCommandName() {
    
    return commandName;
  }

  public String getHelpText() {
    
    return helpText;
  }

  public boolean canBeRedirected() {
    
    return true;
  }

}
