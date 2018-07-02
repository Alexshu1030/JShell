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
import shell.JShellWindow;

public class Man implements Command {
  
  private String commandName = "man";
  private int numOfArguments = 1;
  private String helpText = "NAME:\n" +
      "  man CMD - Displays documentation for CMD \n" +
      "DESCRIPTION:\n" +
      "  Displays name, description, possible parameters"
          + " possible return messages, and example usage of cmd\n" +
      "PARAMETERS:\n" +
      "  CMD - The command for which the documentation "
          + "will be displayed.\n" +
      "RETURNS:\n" +
      "  Documentation for CMD\n" +
      "EXAMPLE USAGE:\n" +
      "  /#: man man\n" +
      "    will display documentation for man.\n" +
      "  /#: man nam\n" +
      "    Will return an error message because nam"
          + " is not a valid command.\n";
  
  public String run(JShellWindow jShell, ArrayList<String> arguments) {
    
    String cmdName = arguments.get(0);
    Command command = Commands.getCommand(cmdName);
    
    boolean successful = false;
    
    if (command != null) {
      System.out.println(command.getHelpText());
      successful = true;
    }
    return successful;
  }
  
  public String getCommandName() {
    
    return commandName;
  }

  public boolean areValidArguments(ArrayList<String> arguments) {
    
    boolean isValid = false;
    if (arguments.size() == numOfArguments) {
      isValid = true;
    }
    return isValid;
  }

  public String getHelpText() {
    
    return helpText;
  }
  
}
