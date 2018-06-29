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
package shell;
import java.util.Scanner;
import commands.Commands;
import filesystem.FileExplorer;
import java.util.ArrayList;

public class JShellWindow {
  
  private FileExplorer fileExplorer = new FileExplorer();
  private boolean terminate = false;
  private ArrayList<String> log = new ArrayList<String>();
  
  public void addInputtoLog(String input) {
    log.add(input);
  }
  
  public ArrayList<String> getLog() {
    return log;
  }

  public void setTerminate(boolean terminate) {
    this.terminate = terminate;
  }
  
  public boolean getTerminate() {
    return terminate;
  }

  public FileExplorer GetFileExplorer() {
    
    return fileExplorer;
  }

}
