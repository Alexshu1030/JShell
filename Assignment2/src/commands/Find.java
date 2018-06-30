package commands;

import java.util.ArrayList;
import shell.JShellWindow;
import filesystem.*;

public class Find implements Command {
  // set the command name
  String commandName = "find";
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    // set an empty string to be displayed at the end
    String result = "";
    FileExplorer explorer = jShell.GetFileExplorer();
    // get the # of arguments
    int size = arguments.size();
    // get the file name from the last element and substring it
    String fileName = arguments.get(size-1);
    fileName = fileName.substring(1, fileName.length()-1);
    // set up a folders string arraylist, add each of the folders specified
    // in the arguments to it
    ArrayList<String> folders = new ArrayList<String>();
    for (int i = 1; i < size-4; i++) {
      folders.add(arguments.get(i));
    }
    // for each folder in the arraylist
    for (int j = 0; j < folders.size(); j++) {
      // get the directory specified by the folder path
      Directory dir = (Directory) explorer.getFile(folders.get(j));
      // if the file is found within the folder
      if (dir.getFile(fileName) != null) {
        // if we are looking for a file
        if (arguments.get(size-3) == "f") {
          // if the file found is a file, add its path to result
          if (dir.getFile(fileName).isDirectory() == false) {
            result += (folders.get(j) + fileName + " ");
          }
       // if we are looking for a directory
        } else if (arguments.get(size-3) == "d") {
       // if the file found is a directory, add its path to result
          if (dir.getFile(fileName).isDirectory() == true) {
            result += (folders.get(j) + fileName + " ");
          }
        }
      }
    }
    // if the result is empty, output error message
    if (result == "") {
      System.out.println("File not found");
    // otherwise output the filepaths
    } else {
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
    // if the last arguments starts and ends with "
    if (arguments.get(size-1).startsWith("\"") &&
        arguments.get(size-1).endsWith("\"")) {
      // if the 2nd last argument is -name
      if (arguments.get(size-2) == "-name") {
        // if the 3rd last argument is either d or f
        if (arguments.get(size-3) == "d" || arguments.get(size-3) == "f") {
          // if the 4th last argument is -type
          if (arguments.get(size-4) == "-type") {
            // if arguments have >= 6 elements
            if (arguments.size() >= 6) {
              // the command is valid
              valid = true;
            }
          }
        }
      }
    }
    // return the valid bool
    return valid;
  }

  @Override
  public String GetHelpText() {
    Man.manFind();
    return "";
  }
  
}
