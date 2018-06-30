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

public class Exit implements Command{

  private String commandName = "exit";


  /**
   * Returns true if successfully terminated the jShell
   * @param jShell this is the window that will be printed on
   * @param arguments this is a list containing null
   * @return true this is true if execution was successful, o/w false
   */
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    jShell.setTerminate(true);
    return true;
  }


  /**
   * Returns the name of this command, "exit"
   * @return commandName this is the command's name
   */
  public String getCommandName() {
    return commandName;
  }


  /**
   * Returns true if arguments are valid for "exit", o/w false
   * @param arguments this is a list containing the arguments input
   * @return isValid this is true if arguments are valid, o/w false
   */
  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean isValid = arguments.isEmpty();
    return isValid;
  }

  /**
   * Prints help text that includes documentation of Exit
   */
  public String getHelpText() {
    // Create a Man object to access the man commands
    Man man = new Man();
    // Call the appropriate command
    man.manExit();
    return "";
  }
}
