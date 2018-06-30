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
import filesystem.FileExplorer;
import filesystem.File;
public class Echo implements Command {
  private String command = "echo";
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    if (arguments.size() == 1) {
      System.out.println(arguments.get(0));
    }
    else if (arguments.size() == 2) {
      if (arguments.get(1) == ">" || arguments.get(1) == ">>") {
        System.out.println(arguments.get(0));
      }
    }
    else if (arguments.size() == 3) {
      if (arguments.get(1) == ">") {
       // We need to see if the file/argument3 exists in the current folder then 
       // set its contents to the first argument i.e. arguments.get(0)
      }
      else if (arguments.get(1) == ">>") {
     // We need to see if the file/argument3 exists in the current folder then
     // put the current contents in a string and append a line separator to
     // it (which is a requirement) then append argument1 to the string.
     // Finally set the contents of the file to be the string
      }
    }
    return true;
  }
  
  public String getCommandName() {
    return command;
  }
  
  public boolean areValidArguments(ArrayList<String> arguments) {
    
    return (arguments.size() <= 3 && arguments.size() >= 1);
  }
    
  
  public String getHelpText() {
    Man.manEcho();
    return "";
  }
}
