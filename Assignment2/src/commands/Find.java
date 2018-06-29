package commands;

import java.util.ArrayList;
import shell.JShellWindow;
import filesystem.*;

public class Find implements Command {

  String commandName = "find";
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    String result = "";
    FileExplorer explorer = jShell.GetFileExplorer();
    int size = arguments.size();
    String fileName = arguments.get(size-1);
    fileName = fileName.substring(1, fileName.length()-1);
    ArrayList<String> folders = new ArrayList<String>();
    for (int i = 1; i < size-4; i++) {
      folders.add(arguments.get(i));
    }
    if (arguments.get(size-3) == "f") {
      for (int j = 0; j < folders.size(); j++) {
        Directory dir = explorer.getDirectory(folders.get(j));
        if (dir.getFile(fileName) != null && 
            !dir.getFile(fileName).isDirectory()) {
          result += (folders.get(j) + fileName + " ");
        }
      }
    }
    else if (arguments.get(size-3) == "d") {
      for (int k = 0; k < folders.size(); k++) {
        Directory dir = explorer.getDirectory(folders.get(k));
        if (dir.getFile(fileName) != null && 
            dir.getFile(fileName).isDirectory()) {
          result += (folders.get(k) + fileName + " ");
        }
      }
    }
    if (result == "") {
      System.out.println("File not found");
    }
    else {
      System.out.println(result);
    }
    return true;
  }

  public String GetCommandName() {
    return commandName;
  }

  public boolean AreValidArguments(ArrayList<String> arguments) {
    boolean valid = false;
    int size = arguments.size();
    if (arguments.get(size-1).startsWith("\"") &&
        arguments.get(size-1).endsWith("\"")) {
      if (arguments.get(size-2) == "-name") {
        if (arguments.get(size-3) == "d" || arguments.get(size-3) == "f") {
          if (arguments.get(size-4) == "-type") {
            if (arguments.size() >= 6) {
              valid = true;
            }
          }
        }
      }
    }
    
    return valid;
  }

  @Override
  public String GetHelpText() {
    Man.manFind();
    return "";
  }
  
}
