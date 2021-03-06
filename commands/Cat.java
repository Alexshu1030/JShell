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
import filesystem.File;
import filesystem.FileExplorer;
import shell.JShellWindow;

public class Cat implements Command {
  /**
   * command The command name of the current class
   */
  private String command = "cat";
  /**
   * helpText The help text for the current class
   */
  private String helpText =
      "NAME:\n" + "  cat FILE1 [FILE2 ...] - Displays the contents"
          + " in the given file(s)\n" + "DESCRIPTION:\n"
          + "  Displays the contents of the file and "
          + "concatenates the contents of subsequent files to the contents"
          + "with three lines inbetween." + " of the first.\n" 
          + "PARAMETERS:\n"
          + "  FILE1 - The file with the contents to be" + " displayed.\n"
          + "  FILE2 - A subsequent file with the contents "
          + "to be displayed by concatenating it to FILE1. An optional"
          + " parameter.\n" + "RETURNS:\n"
          + "  Will display the contents of the given files.\n"
          + "EXAMPLE USAGE:\n" + "If FILE1 contains 'hello'\n"
          + "  /#: cat FILE1\n" + "    will return 'hello'\n"
          + "If FILE1 contains 'hello' and FILE2 contains " + "'there'\n"
          + "  /#: cat FILE1 FILE2\n" + "    Will return 'hellothere'.";

  /**
   * Returns true if execution of command is successful. Execution returns the
   * contents of the file specified in arguments
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the file whose contents are to be
   *        returned
   * @return result the contents of the file in arguments
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = areValidArguments(arguments);
    // If there were no errors in the arguments then we can run the command
    if (!result.errorOccured()) {
      FileExplorer explorer = jShell.getFileExplorer();
  
      String message = "";
  
      for (int i = 0; i < arguments.size(); i++) {
        
        // We need to replace the argument at i with a File object
        String path = arguments.get(i);
        // Get the file at the given path and print it's contents
        File file = null;
        
        // Attempt to get the file at the given path
        try {
          file = explorer.getFile(path);
        }
        catch (FileNotFoundException e) {
          result.logError(i, "The path does not exist.");
        }
        
        // If the file exists, add it's contents onto the end of our message.
        
        if (file != null) {
          
          result.addMessage((String)file.getFileContents(), "\n\n\n\n");
        }
      }
    }
    
    return result;
  }

  /**
   * Returns the command name
   * 
   * @return commandName the command name
   */
  public String getCommandName() {
    
    return command;
  }

  /**
   * Checks if the arguments given to the command is valid
   * 
   * @param arguments the list of str arguments passed to the command
   * @return isValid true if the command is valid and vice versa
   */
  public Result areValidArguments(ArrayList<String> arguments) {

    Result result = new Result(arguments);
    
    if (arguments.size() < 1) {
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

  /**
   * Returns whether if the output can be redirected or not
   * 
   * @return whether if the output can be redirected or not
   */
  public boolean canBeRedirected() {
    
    return true;
  }
}
