// **********************************************************
// Assignment2:
// Student1: Ciaran Hogan
// UTORID user_name: hogancia
// UT Student #: 1003796859
// Author: Ciaran Hogan
//
// Student2: Jeremy Tanuan
// UTORID user_name: tanuanje
// UT Student #: 1003939982
// Author: Jeremy Tanuan
//
// Student3: Aster Wu
// UTORID user_name: wuaster
// UT Student #: 1004175117
// Author: Aster Wu
//
// Student4: Yan Chen Huang
// UTORID user_name: huan1085
// UT Student #: 1004325857
// Author: Yan Chen Huang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

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
      String fileNames = "";
      
      for (int i = 0; i < files.size(); i++) {
        fileNames += files.get(i).getFileName();
        if (i != files.size() - 1)
          fileNames += " ";
      }
      
      System.out.println(fileNames);
      
      successful = true;
    }
    else {
      
      String path = arguments.get(0);
      
      if (Path.isDirectory(path)) {
        
        Directory dir = jShell.GetFileExplorer().getDirectory(path);
        
        if (dir != null) {
          
          ArrayList<File> files = (ArrayList<File>)dir.getFileContents();
          String fileNames = "";
          
          for (int i = 0; i < files.size(); i++) {
            fileNames += files.get(i).getFileName();
            if (i != files.size() - 1)
              fileNames += " ";
          }
          
          System.out.println(fileNames);
          
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
    Man.manLs();
    return "";
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