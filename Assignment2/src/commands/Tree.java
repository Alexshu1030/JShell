package commands;

import java.util.ArrayList;
import filesystem.*;
import shell.JShellWindow;

public class Tree implements Command {

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
    
    Directory rootDir = FileExplorer.getRootDirectory();
    
    // The double back slashes are to add the backslash at the beginning
    // that represents the root directory
    String treeRep = "\\" + GetTreeRepresentation(rootDir, 0);
    
    return true;
  }
  
  private String GetTreeRepresentation(File file, int depth) {

    // Indent the line according to the depth and then added the file name
    // and go to the next line
    String text = Repeat("\t", depth) + file.getFileName() + "\n";
    
    // If the file is a directory then we need to print the sub files
    if (file.isDirectory()) {
      
      // The the sub files in the directory
      Directory dir = (Directory)file;
      ArrayList<File> files = (ArrayList<File>)dir.getFileContents();
      
      // Go through each of them and run this method on them with the
      // increased depth
      for (int i = 0; i < files.size(); i++) {
        
        text += GetTreeRepresentation(files.get(i), depth + 1);
      }
    }
    
    return text;
  }
  
  private String Repeat(String string, int times) {
    
    String newString = "";
    
    // Add the <string> to <newString>, <times> times;
    for (int i = 0; i < times; i++) {
      newString += string;
    }
    
    return newString;
  }

  public String GetCommandName() {
    
    return commandName;
  }

}
