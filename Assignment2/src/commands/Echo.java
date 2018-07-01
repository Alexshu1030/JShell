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
    int numOfArgs = arguments.size();
    String arg1 = arguments.get(0);
    int lastQuoteMark = arg1.length() - 1;
    arg1 = arg1.substring(1, lastQuoteMark);

    FileExplorer explorer = jShell.getFileExplorer();
    if (numOfArgs == 1) {
      System.out.println(arg1);
    } else if (numOfArgs == 3) {
      String arg2 = arguments.get(1);
      String arg3 = arguments.get(2);

      if (arg2.equals(">")){
        Directory dir = explorer.getWorkingDirectory();
        // If the file exists
        if (dir.getFile(arg3) != null) {
          // Get the file at the given name and overwrite file contents
          File outfile = dir.getFile(arg3);
          outfile.setFileContents(arg2);
        }
        else {
          // Create a new file with the new contents in the current directory
          File outfile = new File(arg3, dir, arg1);
          try {
            dir.addFile(outfile);
          } catch (Exception NullPointerException) {
            return false;
          }
        }
        
      }
      else if (arg2.equals(">>")) {
        Directory dir = explorer.getWorkingDirectory();
        // If the file exists
        if (dir.getFile(arg3) != null) {
          // Get the file at the given name and in the current directory
          // and overwrite file contents
          File outfile = dir.getFile(arg3);
          String contents = (String) outfile.getFileContents();
          contents.concat(arg1);
          outfile.setFileContents(contents);
        }
        else {
          // Create a new file with the given name and no contents
          File outfile = new File(arg3, dir, "");
          // Set the file to the contents to be appended
          outfile.setFileContents(arg1);
        }
        
      }
    }
    return true;
  }
  
  public String getCommandName() {
    return command;
  }
  
  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean isValid = false;
    int numOfArgs = arguments.size();
    if (numOfArgs == 1) {
      String arg = arguments.get(0);
      int secArgLen = arg.length();
      // echo's STRING input must have quotations around it
      if (arg.substring(0, 1).equals("\"") &&
          arg.substring(secArgLen - 1, secArgLen).equals("\"")) {
        isValid = true;
      }
    } else if (numOfArgs == 3) {
      String arg1 = arguments.get(0);
      String arg2 = arguments.get(1);
      // Otherwise the second argument must be the > or >>
      int secArgLen = arg1.length();
      if (arg2.equals(">>") || arg2.equals(">")) {
        // echo's STRING input must have quotations around it
        if (arg1.substring(0, 1).equals("\"") &&
            arg1.substring(secArgLen - 1, secArgLen).equals("\"")) {
          isValid = true;
        }
      }
    }
    
    return isValid;
  }
    
  
  public String getHelpText() {
    
    return helpText;
  }
}
