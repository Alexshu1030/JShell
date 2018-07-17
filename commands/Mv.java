package commands;

import java.util.ArrayList;
import exceptions.*;
import filesystem.*;
import shell.JShellWindow;

public class Mv implements Command{
  
  private String commandName = "mv";
  private String helpText = "No implemented yet...";

  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    
    Result result = areValidArguments(arguments);
    
    if (!result.errorOccured()) {
      
      FileExplorer explorer = jShell.getFileExplorer();
      
      String oldPath = arguments.get(0);
      String newPath = arguments.get(1);
      
      File oldFile = null;
      
      try {
        oldFile = explorer.getFile(oldPath);
      }
      catch (FileNotFoundException e) {
        result.logError(0, "The old path does not exist.");
      }
      
      if (oldFile != null) {
        
        File newFile = null;
        
        try {
          newFile = explorer.getFile(newPath);
        }
        catch (FileNotFoundException e) {
          
        }
        
        if (newFile == null) {
          // We are moving oldFile to newPath and renaming it
          String newPathDir = Path.removeFileName(newPath);
          String newFileName = Path.getFileName(newPath);
          
          Directory dir = null;
          
          try {
            dir = explorer.getDirectory(newPathDir);
          }
          catch (FileNotFoundException e) {
            result.logError(1, "Invalid path.");
          }
          
          if (dir != null) {

            String prevName = oldFile.getFileName();
            oldFile.setFileName(newFileName);
            
            try {
              dir.addFile(oldFile);
            } 
            catch (PathExistsException e) {
              oldFile.setFileName(prevName);
              result.logError(1, "A file of the same name already exists at" +
                  " the new path.");
            }
          }
        }
        else {
          if (newFile.isDirectory()) {
            // The newFile exists and is a directory
            try {
              ((Directory)newFile).addFile(oldFile);
            }
            catch (PathExistsException e) {
              result.logError(1, "A file of the same name already exists at" +
                  " the new path.");
            }
          }
          else {
            // The newFile exists and is another file
            result.logError(1, "A file of the same name already exists at" +
            " the new path.");
          }
        }
      }
    }
    return result;
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
