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
import exceptions.FileNotFoundException;
import filesystem.*;
import shell.JShellWindow;

public class Ls implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "ls";
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n" + "  ls [PATH ...]"
      + " - Lists files and directories\n" + "DESCRIPTION:\n"
      + " Recursively lists all files and directories"
      + " inside the given paths, or inside the current directory"
      + " if no directory is given.\n" + "PARAMETERS:\n"
      + "  PATH - The relative or full path that the user"
      + " wants to perform ls on.\n" + "RETURNS:\n"
      + "  This command returns all files and directories" + " inside.\n"
      + "EXAMPLE USAGE:\n" + "  /#: ls\n"
      + "    will recursively return all files and"
      + "directories in the current directory.\n" + "  /#: ls /PATH/\n"
      + "    will recursively return all files and"
      + "directories in the directory /PATH/.\n" + "  /#: ls /PATH1/ /PATH2/\n"
      + "    will recursively return all files and"
      + "directories in the directory /PATH1/ and /PATH2/.\n";

  /**
   * Returns true if execution of the command is successful. Execution prints
   * all of the files and directories in the provided list of directories.
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be listed
   * @return result the output to the shell
   */
  public String run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = new Result(arguments);
    String messages = "";
    // If no argument was given, list all files and directories
    if (arguments.size() == 0) {

      Directory dir = jShell.getFileExplorer().getWorkingDirectory();
      // Get the contents of the current working directory
      ArrayList<File> files = (ArrayList<File>) dir.getFileContents();
      String fileNames = "";

      for (int i = 0; i < files.size(); i++) {
        fileNames += files.get(i).getFileName() + "\n";
      }

      return fileNames;

    } else {
      // Get the contents of the given path
      String path = arguments.get(0);
      File file = null;
      
      try {
        file = jShell.getFileExplorer().getFile(path);
      }
      catch (FileNotFoundException e) {
        result.registerError(0, "The path does not exist.");
      }

      if (file != null) {
        // Check if the file is a directory
        if (file.isDirectory()) {
  
          Directory dir = (Directory) file;
  
          ArrayList<File> files = (ArrayList<File>) dir.getFileContents();
          String fileNames = "";
  
          // Iterate over the file contents and print the file names
          for (int i = 0; i < files.size(); i++) {
            fileNames += files.get(i).getFileName() + "\n";
            result.addMessage(files.get(i).getFileName());
          }
  
          messages = fileNames;
        }
        else {
          // If the path is not a directory, return the file instead.
          messages = file.getFileName();
          result.addMessage(file.getFileName());
        }
      }
      
      arguments.remove(0);
    }
    
    if (arguments.isEmpty()) {
      return messages;
    }
    
    return messages + "\n" + this.run(jShell, arguments);

  }

  /**
   * Returns the name of this command
   * 
   * @return commandName this is the command's name
   */
  public String getCommandName() {

    return commandName;
  }

  /**
   * Checks if the arguments given to the command is valid
   * 
   * @param arguments the list of str arguments passed to the command
   * @return isValid true if the command is valid and vice versa
   */
  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean isValid = false;
    // if there are 0 or more arguments return true
    int numOfArgs = arguments.size();
    if (numOfArgs >= 0) {
      isValid = true;
    }

    return isValid;
  }

  /**
   * Returns the help text for this command.
   * 
   * @return the help text for this command
   */
  public String getHelpText() {

    return helpText;
  }
}
