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
import commandsystem.TextEditor;
import exceptions.FileNotFoundException;
import exceptions.InvalidPathException;
import exceptions.PathExistsException;
import shell.JShellWindow;
import filesystem.FileExplorer;
import filesystem.Path;
import filesystem.Directory;
import filesystem.File;

public class Echo implements Command {
  /**
   * commandName The command name of the current class
   */
  private String command = "echo";
  /**
   * helpText The help text for the current class
   */
  private String helpText =
      "NAME:\n" + "  echo STRING [> OUTFILE |>> OUTFILE] - "
          + "Print or add STRING to standard output\n" + "DESCRIPTION:\n"
          + "  Prints STRING to the JShell if STRING is the"
          + " only parameter provided. If OUTFILE is provided, STRING will"
          + " overwrite whatever is in OUTFILE if > is used, or append "
          + "to OUTFILE if >> is used. If OUTFILE does not exist, it "
          + "will be created.\n" + "PARAMETERS:\n"
          + "  STRING - The string that will be sent to"
          + " standard output. Must be surrounded by double quotes\n"
          + "  >  - Overwrite OUTFILE's contents with STRING. "
          + "Optional parameter.\n"
          + "  >>  - Append OUTFILE's contents with STRING. "
          + "Optional parameter.\n"
          + "  OUTFILE  - The file that STRING will output to"
          + "depending on whether > or >> is used. OUTFILE will be created"
          + " if it does not already exist. Optional parameter\n" 
          + "RETURNS:\n"
          + "  Will print STRING if STRING is the only " + "parameter.\n"
          + "EXAMPLE USAGE:\n" + "  /#: echo \"Hello\"\n"
          + "    will display Hello\n" + "If FILE1 contains 'hello'\n"
          + "  /#: echo \"Hello\" > FILE1\n" + "    FILE1 will contain Hello\n"
          + "If FILE1 contains 'hello'\n" + "  /#: echo \"Hello\" >> FILE1\n"
          + "    FILE1 will contain helloHello.";

  /**
   * Returns true if execution of command is successful. Execution print or add
   * string to standard output, or overwrites or appends a string to a file
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be listed
   * @return result the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = areValidArguments(arguments);

    // If there were no errors in the arguments then we can run the command
    if (!result.errorOccured()) {
      
      int numOfArgs = arguments.size();
  
      String text = arguments.get(0);
  
      // Remove the quotation marks from the argument
      text = text.substring(1, text.length() - 1);
  
      FileExplorer explorer = jShell.getFileExplorer();
      if (numOfArgs == 1) {
        
        result.addMessage(text);
      } 
      else if (numOfArgs == 3) {
        
        // Assign arguments from the arraylist to proper variables
        String operationType = arguments.get(1);
        String outFilePath = arguments.get(2);
        
        try {
          TextEditor.writeText(explorer, outFilePath, text,
              operationType.equals(">>"));
        }
        catch (InvalidPathException e) {
          result.logError(2, "Invalid outFile path.");
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
    
    boolean isValid = false;
    int numOfArgs = arguments.size();

    if (numOfArgs == 1) {
      
      String arg = arguments.get(0);
      int secArgLen = arg.length();
      // echo's STRING input must have quotations around it
      if (arg.substring(0, 1).equals("\"")
          && arg.substring(secArgLen - 1, secArgLen).equals("\"")) {
        isValid = true;
      }
      else {
        result.logError(0, "String must be enclosed in quotations.");
      }
    } else if (numOfArgs == 3) {

      String arg1 = arguments.get(0);
      String arg2 = arguments.get(1);
      // Otherwise the second argument must be the > or >>
      int secArgLen = arg1.length();
      if (arg2.equals(">>") || arg2.equals(">")) {
        // echo's STRING input must have quotations around it
        if (arg1.substring(0, 1).equals("\"")
            && arg1.substring(secArgLen - 1, secArgLen).equals("\"")) {
          isValid = true;
        }
        else {
          result.logError(0, "String must be enclosed in quotations.");
        }
      }
      else {
        result.logError(1, "Invalid argument.");
      }
    }
    else
      result.logError("Invalid number of arguments.");
    
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
