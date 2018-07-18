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

  @Override
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {
    Result result = areValidArguments(arguments);
    // Get history of JShell using history command
    String history = Commands.run(jShell, "history").getMessage();
    // Put history and all commands into an array
    String[] cmds = history.split("\n");
    try {
      int cmdnumber = Integer.parseInt(arguments.get(0));
      if (cmds.length >= cmdnumber) {
        // Remove the number indicating the command's order
        String[] cmd = cmds[cmdnumber-1].split(" ", 2);
        // Run the command
        Commands.run(jShell, cmd[1]);
      }
    }
    catch (Exception e) {
      result.logError("Invalid argument.");
    }
     
    
    return null;
  }

  @Override
  public String getCommandName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Result areValidArguments(ArrayList<String> arguments) {
    Result result = new Result(arguments);
    if (arguments.size() != 0) {
      result.logError("Invalid number of arguments.");
    }
    
    return result;
  }

  @Override
  public String getHelpText() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean canBeRedirected() {
    // TODO Auto-generated method stub
    return false;
  }

  
 
}
