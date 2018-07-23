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
import commandsystem.Command;
import commandsystem.Result;
import exceptions.FileNotFoundException;
import filesystem.*;
import shell.JShellWindow;

public class Ls implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "ls";
  private String recurseiveArg = "-R";
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
      + "directories in the directory /PATH1/ and /PATH2/.";

  /**
   * Returns true if execution of the command is successful. Execution prints
   * all of the files and directories in the provided list of directories.
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be listed
   * @return result the output to the shell
   */
  public Result run(JShellWindow jShell,
      ArrayList<String> arguments) {
    
    Result result = new Result(arguments);
    
    if (!result.errorOccured()) {
      // Check if recursive argument is implemented
      try {
        if (arguments.get(0).equals(recurseiveArg)) {
          arguments.remove(0);
          if (arguments.isEmpty()) {
            arguments.add("/");
          }
          Directory wd = jShell.getFileExplorer().getWorkingDirectory();
          result = runRecursive(jShell, arguments);
          jShell.getFileExplorer().setWorkingDirectory(wd);
          return result;
        }
      } catch (IndexOutOfBoundsException x) {
        // There is no -R argument
      }
      // If no argument was given, list all files and directories
      if (arguments.isEmpty()) {
        // The file is a directory. We want to print the contents of the
        // directory.
        Directory dir = jShell.getFileExplorer().getWorkingDirectory();
        ArrayList<File> files = (ArrayList<File>)dir.getFileContents();

        for (int f = 0; f < files.size(); f++) {
          if (f == 0) 
            result.addMessage(files.get(f).getFileName(), "");
          else
            result.addMessage(files.get(f).getFileName(), "\t");
        }
        
        result.addMessage("");
      } 
      else {
        // Iterate over the paths & print contents of each
        for (int i = 0; i < arguments.size(); i++) {
          // Get the contents of the given path
          String path = arguments.get(i);
          File file = null;

          try {
            file = jShell.getFileExplorer().getFile(path);
          }
          catch (FileNotFoundException e) {
            result.logError(0, "The path does not exist.");
          }

          if (file != null) {
            if (file.isDirectory()) {
              // The file is a directory. We want to print the contents
              // of the directory.
              result.addMessage(file.getFullPath() + ":");
              ArrayList<File> files = (ArrayList<File>)file.getFileContents();
              for (int f = 0; f < files.size(); f++) {
                File curFile = files.get(f);
                if (f == 0)
                  result.addMessage(curFile.getFileName(), "");
                else
                  result.addMessage(curFile.getFileName(), "\t");
              }
              result.addMessage("");
            }
            else {
              // The file is just a file. We want to print it's name.
              result.addMessage(file.getFileName());
            }
          }
        }
      }
    }
    return result;
  }

  private Result runRecursive(JShellWindow jShell,
      ArrayList<String> arguments) {
    Result result = new Result(arguments);
    Result nextRecurseResult = new Result();
    if (!result.errorOccured()) {
      // If no argument was given, list all files and directories
      if (arguments.isEmpty()) {
        // The file is a directory. We want to print the contents of the
        // directory.
        Directory dir = jShell.getFileExplorer().getWorkingDirectory();
        ArrayList<File> files = (ArrayList<File>)dir.getFileContents();

        for (int f = 0; f < files.size(); f++) {
          if (f == 0) 
            result.addMessage(files.get(f).getFileName(), "");
          else
            result.addMessage(files.get(f).getFileName(), "\t");
        }
      } 
      else {
        // Iterate over the paths & subpaths & print contents of each
        for (int i = 0; i < arguments.size(); i++) {
          // Get the contents of the given path
          String path = arguments.get(i);
          File file = null;

          try {
            file = jShell.getFileExplorer().getFile(path);
          }
          catch (FileNotFoundException e) {
            result.logError(0, "The path does not exist.");
          }

          if (file != null) {
            if (file.isDirectory()) {
              // The file is a directory. We want to print the contents
              // of the directory and it's subdirectories
              result.addMessage(file.getFullPath() + ":");
              ArrayList<String> curFileContentsStr = new ArrayList<String>();
              ArrayList<File> files = (ArrayList<File>)file.getFileContents();

              for (int f = 0; f < files.size(); f++) {
                File curFile = files.get(f);
                if (f == 0)
                  result.addMessage(curFile.getFileName(), "");
                else
                  result.addMessage(curFile.getFileName(), "\t");
                curFileContentsStr.add(curFile.getFullPath());
              }
              result.addMessage("");
              jShell.getFileExplorer().setWorkingDirectory((Directory) file);
              nextRecurseResult.addMessage(runRecursive(jShell, curFileContentsStr).getMessage(), "");

            }
            else {
              // The file is just a file. We want to print it's name.
              result.addMessage(file.getFileName());
            }
          }

          if (i != arguments.size() - 1)
            result.addMessage("");
        }
      }
    }
    result.addMessage(nextRecurseResult.getMessage(), "");
    return result;
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
  public Result areValidArguments(ArrayList<String> arguments) {

    Result result = new Result(arguments);

    return result;
  }

  /**
   * Returns the help text for this command.
   * 
   * @return the help text for this command
   */
  public String getHelpText() {

    return helpText;
  }

  public boolean canBeRedirected() {

    return true;
  }
}
