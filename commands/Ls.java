package commands;
import java.util.ArrayList;
import filesystem.*;
import shell.JShellWindow;
public class Ls implements Command{
  
  private String commandName = "ls";
  private String helpText = "Not finished...";

  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    
    boolean successful = false;
    
    if (arguments.size() == 0) {
      
      Directory dir = jShell.GetFileExplorer().getWorkingDirectory();
      
      ArrayList<File> files = (ArrayList<File>)dir.getFileContents();
      
      for (int i = 0; i < files.size(); i++) {
        System.out.println(files.get(i).getFileName());
      }
      
      successful = true;
    }
    else {
      
      String path = arguments.get(0);
      
      if (Path.isDirectory(path)) {
        
        Directory dir = jShell.GetFileExplorer().getDirectory(path);
        
        if (dir != null) {
          
          ArrayList<File> files = (ArrayList<File>)dir.getFileContents();
          
          for (int i = 0; i < files.size(); i++) {
            System.out.println(files.get(i).getFileName());
          }
          
          successful = true;
        }
      }
      else {
        
        File file = jShell.GetFileExplorer().getFile(path);
        
        if (file != null) {
          System.out.println(file.getFileName());          
          successful = true;
        }
      }
    }
    
    return successful;
  }

  public String GetCommandName() {
    
    return commandName;
  }
  
  public boolean IsValidCommand(String commandName, ArrayList<String> arguments) {
    
    boolean hasCorrectName = this.commandName.equals(commandName);
    // Get rid of magic numbers
    boolean hasCorrectNumOfArgs = arguments.size() == 0 || arguments.size() == 1;
    
    return hasCorrectName && hasCorrectNumOfArgs;
  }

  public String GetHelpText() {
    
    return helpText;
  }
  
  /*
  public Ls(ArrayList<filesystem.File> folders) {
    ArrayList<filesystem.File> currentFolderContents = pwd().getFileContents();
    for (int i = 0; i < folders.size(); i++) {
      boolean found = false;
      for (int h = 0; h < currentFolderContents.size(); h++) {
        if (!found) {
          if (folders.get(i) == currentFolderContents.get(h)) {
            found = true;
          }
        }
      }
      if (found && folders.get(i).isDirectory() == true) {
        ArrayList<filesystem.File> listOfFiles = 
            (ArrayList<File>) folders.get(i).getFileContents();
        System.out.print(folders.get(i).getFileName() + ": ");
        for (int j = 0; j < listOfFiles.size(); j++) {
          System.out.print(listOfFiles.get(j));
        }
        System.out.println("");
      }
      else if (found && folders.get(i).isDirectory() == false) {
        System.out.println(folders.get(i).getFileName());
      }
      else {
        System.out.println("File not found");
      }
    }
  }
  */
}