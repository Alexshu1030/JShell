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
import filesystem.FileExplorer;
import shell.JShellWindow;
import commandsystem.Command;
import commandsystem.Commands;
import commandsystem.Result;

public class Number implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "!";
  private String helpText = "NAME:\n"
      + "  !n - Runs n-th command entered\n" + "DESCRIPTION:\n"
      + "  Executes the n-th command in this history of the current JShell"
      + "instance.\n"
      + "PARAMETERS:\n" + "  n - The n-th command in history to be executed.\n"
      + "RETURNS:\n" + "  Documentation for CMD\n"
      + "EXAMPLE USAGE:\n" + "  /#: echo \"hi\"\n  /#: !1 \n"
      + "  hi\n  hi";
  @Override
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    Result result = areValidArguments(arguments);
    try {
      int cmdnumber = Integer.parseInt(arguments.get(0));
      ArrayList<String> log = jShell.getLog();
      String history;
      // Make sure the cmdnumber is within the bounds of the log's size
      if (log.size()-1 >= cmdnumber) {
        history = jShell.getLog().get(cmdnumber-1);
        // Replace !number with the command run by !number
        log.remove(log.size()-1);
        log.add(history);
        result = Commands.run(jShell, history);
      }
      else {
        result.logError("Command "+ cmdnumber +" does not exist.");
      }
      
      
    }
    catch (Exception e) {
      result.logError("Invalid argument.");
    }
    return result;
  }

  @Override
  public String getCommandName() {
    return commandName;
  }

  @Override
  public Result areValidArguments(ArrayList<String> arguments) {
    Result result = new Result(arguments);
    if (arguments.size() != 1) {
      result.logError("Invalid number of arguments.");
    }
    
    return result;
  }

  @Override
  public String getHelpText() {
    // TODO Auto-generated method stub
    return helpText;
  }

  /**
   * Returns whether if the output can be redirected or not
   * 
   * @return whether if the output can be redirected or not
   */
  @Override
  public boolean canBeRedirected() {
    return true;
  }

  
 
}
