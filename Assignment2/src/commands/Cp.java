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
import exceptions.*;
import filesystem.*;
import shell.JShellWindow;

public class Cp implements Command {

  /**
   * commandName The command name of the current class
   */
  private String commandName = "cp";
  /**
   * helpText The help text for the current class
   */
  private String helpText = "";

  /**
   * Returns true if execution of the command is successful. Execution changes
   * current directory to the one specified in arguments
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directory to be changed into
   * @return result the output to the shell
   */
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
      } catch (FileNotFoundException e) {
        result.logError(0, "The old path does not exist.");
      }

      if (oldFile != null) {
        if (oldFile.isDirectory()) {
          // Get the files in the directory
          ArrayList<File> files =
              (ArrayList<File>) ((Directory) oldFile).getFileContents();
          // Iterate over the files in the directory and copy them
          for (int i = 0; i < files.size(); i++) {
            // Get the current file and create a copy of it at the new path
            File currentFile = files.get(i);
            copyFile(explorer, result, currentFile, newPath);
          }
        } else {
          // Get the old file and create a copy of it at the new path
          copyFile(explorer, result, oldFile, newPath);
        }
      }
    }
    return result;
  }

  /**
   * Copies a file to a new location indicated by path
   * 
   * @param explorer the window that we're working with
   * @param result result object used to log errors
   * @param originalFile the original file that we're copying
   * @param path the path that we're copying into
   */
  private void copyFile(FileExplorer explorer, Result result, File originalFile,
      String path) {

    // We have create a copy of the old file. This is a free floating
    // file and is not in any directory right now.
    File fileCopy = originalFile.createCopy();
    // Add the file into the directory at the new path
    try {
      explorer.addFile(path, fileCopy);
    } catch (PathExistsException e) {
      result.logError(1,
          "A file of the same name already exists at" + " the new path.");
    } catch (InvalidPathException e) {
      result.logError(1, "Invalid path.");
    }
  }

  /**
   * Checks if the arguments given to the command is valid
   * 
   * @param arguments the list of str arguments passed to the command
   * @return isValid true if the command is valid and vice versa
   */
  public Result areValidArguments(ArrayList<String> arguments) {
    // setup a new result object
    Result result = new Result(arguments);
    // log error if the # of arguments are >2
    if (arguments.size() != 2) {
      result.logError("Invalid number of arguments.");
    }

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
   * Returns the help text for this command.
   * 
   * @return the help text for this command
   */
  public String getHelpText() {

    return helpText;
  }

  /**
   * Returns whether if the output can be redirected or not
   * 
   * @return whether if the output can be redirected or not
   */
  public boolean canBeRedirected() {

    return true;
  }

}
