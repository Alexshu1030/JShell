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
import filesystem.Directory;
import filesystem.File;
public class Echo implements Command {
  
  private String command = "echo";
  private String helpText = "NAME:\n" +
      "  echo STRING [> OUTFILE |>> OUTFILE] - "
      + "Print or add STRING to standard output\n" +
  "DESCRIPTION:\n" +
  "  Prints STRING to the JShell if STRING is the"
      + " only parameter provided. If OUTFILE is provided, STRING will"
      + " overwrite whatever is in OUTFILE if > is used, or append "
      + "to OUTFILE if >> is used. If OUTFILE does not exist, it "
      + "will be created.\n" +
  "PARAMETERS:\n" +
  "  STRING - The string that will be sent to"
      + " standard output. Must be surrounded by double quotes\n" +
  "  >  - Overwrite OUTFILE's contents with STRING. "
      + "Optional parameter.\n" +
  "  >>  - Append OUTFILE's contents with STRING. "
      + "Optional parameter.\n" +
  "  OUTFILE  - The file that STRING will output to"
      + "depending on whether > or >> is used. OUTFILE will be created"
      + " if it does not already exist. Optional parameter\n" +
  "RETURNS:\n" +
  "  Will print STRING if STRING is the only "
      + "parameter.\n" +
  "EXAMPLE USAGE:\n" +
  "  /#: echo \"Hello\"\n" +
  "    will display Hello\n" +
  "If FILE1 contains 'hello'\n" +
  "  /#: echo \"Hello\" > FILE1\n" +
  "    FILE1 will contain Hello\n" +
  "If FILE1 contains 'hello'\n" +
  "  /#: echo \"Hello\" >> FILE1\n" +
  "    FILE1 will contain helloHello.\n";
  
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    FileExplorer explorer = jShell.getFileExplorer();
    if (arguments.size() == 1) {
      System.out.println(arguments.get(0));
    }
    else if (arguments.size() == 2) {
      if (arguments.get(1).equals(">")|| arguments.get(1).equals(">>")){
        System.out.println(arguments.get(0));
      }
    }
    else if (arguments.size() == 3) {
      if (arguments.get(1).equals(">")){
        Directory dir = explorer.getWorkingDirectory();
        // If the file exists
        if (dir.getFile(arguments.get(2)) != null) {
          // Get the file at the given name and overwrite file contents
          File outfile = dir.getFile(arguments.get(2));
          outfile.setFileContents(arguments.get(1));
        }
        else {
          // Create a new file with the new contents in the current directory
          File outfile = new File(arguments.get(0),dir, arguments.get(2));
        }
        
      }
      else if (arguments.get(1).equals(">>")) {
        Directory dir = explorer.getWorkingDirectory();
        // If the file exists
        if (dir.getFile(arguments.get(2)) != null) {
          // Get the file at the given name and in the current directory
          // and overwrite file contents
          File outfile = dir.getFile(arguments.get(2));
          String contents = (String) outfile.getFileContents();
          contents.concat(arguments.get(0));
          outfile.setFileContents(contents);
        }
        else {
          // Create a new file with the given name and no contents
          File outfile = new File(arguments.get(0),dir, "");
          // Set the file to the contents to be appended
          outfile.setFileContents(arguments.get(0));
        }
        
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
    
    return helpText;
  }
}
