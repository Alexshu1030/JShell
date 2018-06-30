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
  // set the command name as mkdir to be used for calling
  private String commandName = "mkdir";
  private String helpText = "Not finished...";
  
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    // get the instance of explorer
    FileExplorer explorer = jShell.getFileExplorer();
    // the path to be created is the first argument
    String path = arguments.get(0);
    // get the parent dir of the specified path
    Directory parentDir = explorer.getParentDirectory(path);
    // get the dir name to be added
    String dirName = Path.getFileName(path);
    // add the directory and make it the child of the parentdir
    Directory newDir = new Directory(dirName, parentDir);
    parentDir.addFile(newDir);
    
    return true;
  }

  public String getCommandName() {
    // return the command name
    return commandName;
  }

  public boolean areValidArguments(ArrayList<String> arguments) {
    
    boolean isValid = false;
    // if the argument has 1 arg return true
    if (arguments.size() == 1) {
        isValid = true;
    }
    
    return isValid;
  }

  public String getHelpText() {
    // Create a Man object to access the man commands
    Man man = new Man();
    // Call the appropriate command
    man.manMkdir();
    return "";
  }
}
