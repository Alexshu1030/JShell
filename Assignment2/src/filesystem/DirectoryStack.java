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
package filesystem;

import java.util.ArrayList;
import filesystem.Directory;
import filesystem.FileExplorer;
import shell.JShellWindow;
public class DirectoryStack {
  private ArrayList<Directory> directoryStack = new ArrayList<Directory>();
  public static DirectoryStack stack = new DirectoryStack();
  
  public void pushd(JShellWindow jShell, Directory directory) {
    FileExplorer explorer = jShell.getFileExplorer();
    // Add the current directory to the top of the stack 
    directoryStack.add(0,explorer.getWorkingDirectory());
    // Sets the current directory to the one we added
    explorer.setWorkingDirectory(directory);
  }
  public void popd(JShellWindow jShell) {
    FileExplorer explorer = jShell.getFileExplorer();
    
    if (directoryStack.isEmpty()) {
      System.out.println("The directory stack is empty. Can't pop");
    }
    else {
      // Sets the directory at the top of the stack to be the current directory
      explorer.setWorkingDirectory(directoryStack.get(0));
      // Remove the directory at the top of the stack
      directoryStack.remove(0);
    }
  }
  
}
