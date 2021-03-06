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
import commandsystem.Web;
import exceptions.InvalidPathException;
import filesystem.FileExplorer;
import shell.JShellWindow;

/**
 * Returns the content from the webpage if execution of the command is
 * successful.
 * 
 * @param jShell the window that will be printed on
 * @param arguments the url that we will be fetching from
 * @return result the output to the shell
 */
public class Curl implements Command {
  /**
   * numOfArguments The correct number of args in the current class
   */
  private String commandName = "curl";
  /**
   * commandName The command name of the current class
   */
  private int numOfArguments = 1;
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n"
      + "  curl URL - Adds file at URL to current working directory \n" +
      "DESCRIPTION:\n" + "  Retrieves the file at the URL and adds it's"
      + " contents to a file of the same name in the current working directory"
      + "\n"
      + "PARAMETERS:\n" + "  URL - The url containing the file to be retrieved"
      + "\n" + "RETURNS:\n" + "  This command does not return anything\n"
      + "EXAMPLE USAGE:\n" + "  /#: curl"
          + " http://www.cs.cmu.edu/~spok/grimmtmp/073.txt\n"
      + "    will get the contents of the file and put it into a file named"
      + " 073.txt in the current working directory.";
  
  /**
   * Returns true if execution of the command is successful.
   * 
   * @param jShell the window that will be printed on
   * @param arguments the url we will be fetching from
   * @return result the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    FileExplorer explorer = jShell.getFileExplorer();
    Result result = areValidArguments(arguments);
    
    // If there were no errors in the arguments then we can run the command
    if (!result.errorOccured()) {
    
      String url = arguments.get(0);
      String arr[] = url.split("/");
      String outfile = arr[arr.length - 1];
      Web web = new Web();
      // Get the contents of the web page
      String contents = web.grabContents(jShell, arguments);
      // Make sure it was a valid url first
      if (contents == "Invalid URL") {
        result.logError("Invalid URL");
      }
      else {
        // Write contents into the file
        try {
          TextEditor.writeText(explorer, outfile, contents, false);
        } catch (InvalidPathException e) {
          result.logError("Invalid Path Exception.");
        }
      }
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
   * Checks if the arguments given to the command is valid
   * 
   * @param arguments the list of str arguments passed to the command
   * @return isValid true if the command is valid and vice versa
   */
  public Result areValidArguments(ArrayList<String> arguments) {
    
    Result result = new Result(arguments);
    
    if (arguments.size() != numOfArguments) {
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
