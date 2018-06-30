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
import filesystem.*;
import shell.JShellWindow;
public class Mkdir implements Command {

  private String commandName = "mkdir";
  private String helpText = "Not finished...";
  
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    
    FileExplorer explorer = jShell.getFileExplorer();
    
    String path = arguments.get(0);
    Directory parentDir = explorer.getParentDirectory(path);
    String dirName = Path.getFileName(path);
    
    Directory newDir = new Directory(dirName, parentDir);
    parentDir.addFile(newDir);
    
    return true;
  }

  public String getCommandName() {
    
    return commandName;
  }

  public boolean areValidArguments(ArrayList<String> arguments) {
    
    boolean isValid = false;
    
    if (arguments.size() == 1) {
        isValid = true;
    }
    
    return isValid;
  }

  public String getHelpText() {
   
    Man.manMkdir();
    return "";
  }
}
