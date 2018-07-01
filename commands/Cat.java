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
import filesystem.Directory;
import filesystem.File;
import filesystem.FileExplorer;
import shell.JShellWindow;

public class Cat implements Command{
  private String command = "cat";
  private String helpText = "NAME:\n" +
      "  cat FILE1 [FILE2 ...] - Displays the contents"
      + " in the given file(s)\n" +
  "DESCRIPTION:\n" +
  "  Displays the contents of the file and "
      + "concatenates the contents of subsequent files to the contents"
      + " of the first.\n" +
  "PARAMETERS:\n" +
  "  FILE1 - The file with the contents to be"
      + " displayed.\n" +
  "  FILE2 - A subsequent file with the contents "
      + "to be displayed by concatenating it to FILE1. An optional"
      + " parameter.\n" +
  "RETURNS:\n" +
  "  Will display the contents of the given files.\n" +
  "EXAMPLE USAGE:\n" +
  "If FILE1 contains 'hello'\n" +
  "  /#: cat FILE1\n" +
  "    will return 'hello'\n" +
  "If FILE1 contains 'hello' and FILE2 contains "
      + "'there'\n" +
  "  /#: cat FILE1 FILE2\n" +
  "    Will return 'hellothere'.\n";
  
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    FileExplorer explorer = jShell.getFileExplorer();
    for (int i = 0; i < arguments.size(); i++) {
      // We need to replace the argument at i with a File object
      String path = arguments.get(i);
      // Get the file at the given path and print it's contents
      File file = explorer.getFile(path);
      System.out.println(file.getFileContents());
      // make sure we do not print 3 line breaks for the last file
      if (i != arguments.size()-1) {
        System.out.print("\n\n\n");
      }
    }
    
    return false;
  }
  
  public String getCommandName() {
    return command;
  }
  public boolean areValidArguments(ArrayList<String> arguments) {
    
    return (arguments.size() >= 1);
  }
  
  public String getHelpText() {
    
    return helpText;
  }
}
