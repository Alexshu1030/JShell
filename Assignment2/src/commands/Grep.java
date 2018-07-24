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
import commandsystem.Commands;
import commandsystem.Result;
import filesystem.File;
import exceptions.FileNotFoundException;
import shell.JShellWindow;

public class Grep implements Command {

  /**
   * commandName The command name of the current class
   */
  private String commandName = "grep";
  /**
   * recursiveArg the argument that indicates recursively iterate thru folder
   */
  private String recursiveArg = "-R";
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n"
      + "grep [-R] REGEX PATH - print lines matching a pattern described by"
      + "REGEX in PATH. Prints lines from a file if no -R is supplied or from"
      + " a folder if -R is supplied and the PATH is a folder. \n"
      + "PARAMETERS:\n"
      + "-R - The option to search recursively through a folder (scanning "
      + "all files).\n"
      + "REGEX - The expression that we are looking for in the PATH.\n"
      + "PATH - The file or folder that we are searching from.\n" + "RETURNS:\n"
      + "All lines containing REGEX inside PATH.\n" + "EXAMPLE USAGE:\n"
      + "grep -R a b\n"
      + "This will recursively return all lines containing a in all files "
      + "inside b\n" + "grep a b.txt\n"
      + "This will return all lines containing a in b.txt";

  /**
   * Returns true if successfully terminated the jShell
   * 
   * @param jShell this is the window that will be printed on
   * @param arguments this is a list containing null
   * @return true the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    // instantiate a new cat object
    Cat checkFile = new Cat();
    // check if arguments are valid
    Result result = areValidArguments(arguments);
    // set a list of files to the arguments
    ArrayList<String> files = arguments;
    // if the arguments are valid
    if (!result.errorOccured()) {
      // if the recursive arg is provided (recursively go thru folder
      if (arguments.get(0).equals("-R")) {
        // get the regex we're looking for
        String regex = arguments.get(1);

        // for each folder in the list
        for (int i = 2; i < files.size(); i++) {
          // try to go thru each file and add their matching lines to the msg
          try {
            // if each of the args is an directory
            if (jShell.getFileExplorer().getFile(files.get(i)).isDirectory()) {
              // set the current folder to be used with cat
              String currFolder = files.get(i);
              // make a new ls object
              // get all the file names in the folder in eachFile
              // cd into current folder
              Commands.run(jShell, "cd " + currFolder);
              String eachFile = Commands.run(jShell, "ls").getMessage();
              // split the file names into an array
              String filesInDir[] = eachFile.split("\\s");
              // for each file name in the array
              for (int k = 0; k < filesInDir.length; k++) {
                // get the current file we're working with
                File currFile = jShell.getFileExplorer().getFile(filesInDir[k]);
                // if the current file is a file not directory
                if (!currFile.isDirectory()) {
                  // split its contents into separate lines
                  String fileCont[] =
                      ((String) currFile.getFileContents()).split("\\n");
                  // if each line contains the regex add it to the message
                  for (int a = 0; a < fileCont.length; a++) {
                    if (fileCont[a].contains(regex)) {
                      result.addMessage(fileCont[a]);
                    }
                  }
                } else {
                  // if the currfile is a folder, call the method itself again
                  String recursiveFolder = currFile.getFileName();
                  Commands.run(jShell, "grep " + recursiveFolder);
                }
              }
              Commands.run(jShell, "cd ..");
            }
          }
          // catch exception and log error if the invalid path
          catch (FileNotFoundException e) {
            result.logError(0, "The path does not exist.");
          }
        }
        // if no -R is provided
      } else {
        // set regex to the first word in the argument
        String regex = arguments.get(0);
        // for each file in files
        for (int j = 1; j < files.size(); j++) {
          // try to get each file
          try {
            File fileObj = jShell.getFileExplorer().getFile(arguments.get(j));
            if (!fileObj.isDirectory()) {
              // get the content of the file by running cat
              String fileCont = (String) fileObj.getFileContents();
              // split the content into separate lines
              String[] fileLines = fileCont.split("\\n");
              // if a line contains the regex add it to the result
              for (int h = 0; h < fileLines.length; h++) {
                if (fileLines[h].contains(regex)) {
                  result.addMessage(fileLines[h]);
                }
              }
            }
            // catch error and log error if invalid path
          } catch (FileNotFoundException e) {
            result.logError(0, "The path does not exist.");
          }
        }
      }
    }
    // return the result
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
   * Returns true if arguments are valid, o/w false
   * 
   * @param arguments this is a list containing the arguments input
   * @return isValid this is true if arguments are valid, o/w false
   */
  public Result areValidArguments(ArrayList<String> arguments) {

    Result result = new Result(arguments);
    // if there are less than 2 arguments in the call log the error
    if (arguments.size() < 2) {
      result.logError("Invalid number of arguments.");
    }

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
