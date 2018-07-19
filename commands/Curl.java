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
import commandsystem.Web;
import filesystem.FileExplorer;
import shell.JShellWindow;

public class Curl implements Command {
  
  private String commandName = "curl";
  private int numOfArguments = 1;
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
  
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    
    Result result = areValidArguments(arguments);
    
    // If there were no errors in the arguments then we can run the command
    if (!result.errorOccured()) {
    
      String url = arguments.get(0);
      String arr[] = url.split("/");
      String outfile = arr[arr.length - 1];
      Web web = new Web();
      // Get the contents of the web page
      String contents = web.grabContents(jShell, arguments);
      // Echo the contents into the file
      Commands.run(jShell, "echo " + contents + " > " + outfile);
    }
    
    return result;
  }

  public String getCommandName() {

    return commandName;
  }

  public Result areValidArguments(ArrayList<String> arguments) {
    
    Result result = new Result(arguments);
    
    if (arguments.size() != numOfArguments) {
      result.logError("Invalid number of arguments.");
    }
    
    return result;
  }

  public String getHelpText() {

    return helpText;
  }

  public boolean canBeRedirected() {
    
    return true;
  }
}
