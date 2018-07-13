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

public class Curl implements Command {
  private String commandName = "curl";
  private int numOfArguments = 1;
  @Override
  public String run(JShellWindow jShell, ArrayList<String> arguments) {
    String url = arguments.get(0);
    String arr[] = url.split("/");
    String outfile = arr[arr.length - 1];
    Web web = new Web();
    // Get the contents of the web page
    String contents = web.run(jShell, arguments);
    // Echo the contents into the file
    Commands.run(jShell, "echo " + contents + " > " + outfile);
    return null;
  }

  @Override
  public String getCommandName() {
    // TODO Auto-generated method stub
    return commandName;
  }

  @Override
  public boolean areValidArguments(ArrayList<String> arguments) {
    // TODO Auto-generated method stub
    return (arguments.size() == numOfArguments);
  }

  @Override
  public String getHelpText() {
    // TODO Auto-generated method stub
    return null;
  }

}
