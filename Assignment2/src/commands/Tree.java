package commands;

import java.util.ArrayList;
import filesystem.*;
import shell.JShellWindow;

public class Tree implements Command {

  private String commandName = "tree";
  private int numOfArguments = 0;
  private String helpText = "No done yet...";
  
  public boolean areValidArguments(ArrayList<String> arguments) {
    
    return arguments.size() == numOfArguments;
  }

  public String getHelpText() {
    
    return helpText;
  }

  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    
    Directory rootDir = FileExplorer.getRootDirectory();
    // The double back slashes are to add the backslash at the beginning
    // that represents the root directory
    String treeRep = "\\" + getTreeRepresentation(rootDir, 0);
    System.out.println(treeRep);
    return true;
  }
  
  private String getTreeRepresentation(File file, int depth) {

    // Indent the line according to the depth and then added the file name
    // and go to the next line
    String text = repeat("\t", depth) + file.getFileName() + "\n";
    
    // If the file is a directory then we need to print the sub files
    if (file.isDirectory()) {
      
      // The the sub files in the directory
      Directory dir = (Directory)file;
      ArrayList<File> files = (ArrayList<File>)dir.getFileContents();
      
      // Go through each of them and run this method on them with the
      // increased depth
      for (int i = 0; i < files.size(); i++) {
        
        text += getTreeRepresentation(files.get(i), depth + 1);
      }
    }
    
    return text;
  }
  
  private String repeat(String string, int times) {
    
    String newString = "";
    
    // Add the <string> to <newString>, <times> times;
    for (int i = 0; i < times; i++) {
      newString += string;
    }
    
    return newString;
  }

  public String getCommandName() {
    
    return commandName;
  }

}
