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
import driver.JShell;
import shell.JShellWindow;

public interface Command {

  /**
   * Runs a command using the given arguments and the shell window to
   * returns the output of the command.
   * 
   * @param jShell the window that called the command
   * @param arguments a list of arguments for the command
   * @return the output of the command
   */
  public String run(JShellWindow jShell, ArrayList<String> arguments);

  /**
   * Gets the name of the command.
   * 
   * @return the name of the command.
   */
  public String getCommandName();

  /**
   * Returns whether the specified arguments are valid. i.e. if there are
   * the correct number of args, they are in the correct format, etc.
   * 
   * @param arguments the arguments
   * @return true if the args are valid and false otherwise.
   */
  public boolean areValidArguments(ArrayList<String> arguments);

  /**
   * Returns the help text for this command.
   * 
   * @return the help text for this command.
   */
  public String getHelpText();
}
