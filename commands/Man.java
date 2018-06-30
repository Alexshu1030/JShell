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

public class Man implements Command {
  private String commandName = "man";
  private int numOfArguments = 1;
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    // Print documentation for 'exit' command
    String cmd = arguments.get(0);
    if (cmd.equals("exit")) {
      manExit();

    }
    // Print documentation for 'mkdir' command
    else if (cmd.equals("mkdir")) {
      manMkdir();
    }
    // Print documentation for 'cd' command
    else if (cmd.equals("cd")) {
      manCd();
    }
    // Print documentation for 'ls' command
    else if (cmd.equals("ls")) {
      manLs();
    }
    // Print documentation for 'pwd' command
    else if (cmd.equals("pwd")) {
      manPwd();
    }
    // Print documentation for 'pushd' command
    else if (cmd.equals("pushd")) {
      manPushd();
    }
    // Print documentation for 'popd' command
    else if (cmd.equals("popd")) {
      manPopd();
    }
    // Print documentation for 'history' command
    else if (cmd.equals("history")) {
      manHistory();
    }
    // Print documentation for 'cat' command
    else if (cmd.equals("cat")) {
      manCat();
    }
    // Print documentation for 'echo' command
    else if (cmd.equals("echo")) {
      manEcho();
    }
    // Print documentation for 'man' command
    else if (cmd.equals("man")) {
      manMan();
    }
    // Print documentation for 'find' command
    else if (cmd.equals("find")) {
      manFind();
    }
    // Print documentation for 'tree' command
    else if (cmd.equals("tree")) {
      manTree();
    }
    else {
      
      System.out.println("F");
    }
    return true;
  }
  // Documentation for 'exit' command
  public void manExit() {
    System.out.println("NAME:");
    System.out.println("  exit - Terminates the current JShell"
        + " process.");
    System.out.println("DESCRIPTION:");
    System.out.println("  Prevents any other code in JShell from"
        + " being run by reaching the exit condition in"
        + " the while loop. The exit condition is if command 'exit'"
        + " is entered by user");
    System.out.println("PARAMETERS:");
    System.out.println("  There are no parameters available for"
        + " this command.");
    System.out.println("RETURNS:");
    System.out.println("  This command does not return anything.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: exit");
    System.out.println("    will successfully terminate JShell.");
    System.out.println("  /#: exit p");
    System.out.println("    will not terminate JShell because of the"
        + " additional parameter 'p'.");
    System.out.println("  /#:              exit");
    System.out.println("    will terminate JShell because"
        + " JShell will ignore excess spaces.");
  }
  // Documentation for 'mkdir' command
  public void manMkdir() {
    System.out.println("NAME:");
    System.out.println("  mkdir DIR1 [DIR2] ... [PATH] -"
        + " Create directories");
    System.out.println("DESCRIPTION:");
    System.out.println("  Creates a directory named in the first parameter"
        + " in the location of the directory given in the optional second"
        + " parameter. If no second parameter is given, the directory is"
        + " created in the current directory.");
    System.out.println("PARAMETERS:");
    System.out.println("  DIR1 - The name of the directory. The only valid"
        + " characters for the name are from a-z, A-Z, 0-9.");
    System.out.println("  DIR2 - The name of a second directory."
        + " An optional parameter.");
    System.out.println("  [PATH] - The path that the user wants the "
        + "directory(ies) to be created in. The path may be a relative"
        + " path or a full path. An optional parameter.");
    System.out.println("RETURNS:");
    System.out.println("  This command does not return anything.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: mkdir Dir1");
    System.out.println("    will create a directory named Dir1 in the"
        + " current directory.");
    System.out.println("  /#: mkdir Dir1 Dir2 Dir3");
    System.out.println("    will create a directory named Dir1, "
        + "a directory named Dir2, a directory named Dir3 in the"
        + " current directory.");
    System.out.println("  /#: mkdir Dir1 /Dir2/");
    System.out.println("    will create a directory named Dir1 inside the"
        + " directory named Dir2 that is located in the current"
        + " directory.");
    System.out.println("    If Dir2 does not exist in the current directory,"
        + " it will look for Dir2 in the root directory. If Dir2 can not "
        + "be found, the command will fail.");
  }
  // Documentation for 'cd' command
  public void manCd() {
    System.out.println("NAME:");
    System.out.println("  cd DIR - Change the current directory");
    System.out.println("DESCRIPTION:");
    System.out.println("  Changes the current directory to DIR.");
    System.out.println("PARAMETERS:");
    System.out.println("  DIR - The directory to be changed to."
        + " It may be a full path or a relative path.");
    System.out.println("RETURNS:");
    System.out.println("  This command does not return anything.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: cd /Dir1");
    System.out.println("    will change the current directory to Dir1"
        + " in the root directory.");
    System.out.println("  /#: cd ./Dir1/Dir2");
    System.out.println("    will change the current directory to Dir2, "
        + " which is located in Dir1 which is located in the current"
        + "directory.");
    System.out.println("  /#: cd ..");
    System.out.println("    will change the current directory to its"
        + "parent directory.");
  }
  // Documentation for 'ls' command
  public void manLs() {
    System.out.println("NAME:");
    System.out.println("  ls [PATH ...]"
        + " - Lists files and directories");
    System.out.println("DESCRIPTION:");
    System.out.println(" Recursively lists all files and directories"
        + " inside the given paths, or inside the current directory"
        + " if no directory is given.");
    System.out.println("PARAMETERS:");
    System.out.println("  PATH - The relative or full path that the user"
        + " wants to perform ls on.");
    System.out.println("RETURNS:");
    System.out.println("  This command returns all files and directories"
        + " inside.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: ls");
    System.out.println("    will recursively return all files and "
        + "directories in the current directory.");
    System.out.println("  /#: ls /PATH/");
    System.out.println("    will recursively return all files and "
        + "directories in the directory /PATH/.");
    System.out.println("  /#: ls /PATH1/ /PATH2/");
        System.out.println("    will recursively return all files and"
            + "directories in the directory /PATH1/ and /PATH2/.");
  }
  // Documentation for 'pwd' command
  public void manPwd() {
    System.out.println("NAME:");
    System.out.println("  pwd - Prints current working directory");
    System.out.println("DESCRIPTION:");
    System.out.println("  Prints the full path of the current working "
        + "directory.");
    System.out.println("PARAMETERS:");
    System.out.println("  There are no parameters available for"
        + " this command.");
    System.out.println("RETURNS:");
    System.out.println("  This command does not return anything.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: exit");
    System.out.println("    will successfully terminate JShell.");
    System.out.println("  /#: exit p");
    System.out.println("    will not terminate JShell because of the"
        + " additional parameter 'p'.");
    System.out.println("  /#:              exit");
    System.out.println("    will terminate JShell because"
        + " JShell will ignore excess spaces.");
  }
  // Documentation for 'pushd' command
  public void manPushd() {
    System.out.println("NAME:");
    System.out.println("  pushd DIR - Pushes the current working directory"
        + " into a stack, then change the current working directory"
        + " to DIR.");
    System.out.println("DESCRIPTION:");
    System.out.println("  Saves the current working directory into a "
        + "stack of directories, then changes the current working "
        + "directory to DIR so that the old working directory could be "
        + "returned. The directory stack is dynamic and changes "
        + "depending on pushd and popd commands.");
    System.out.println("PARAMETERS:");
    System.out.println("  DIR - The new current working directory that the"
        + " user may eventually want to return to through the directory"
        + " stack.");
    System.out.println("RETURNS:");
    System.out.println("  This command does not return anything.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: pushd Dir1");
    System.out.println("    will add the current working directory to"
        + " the stack and change the current working directory to Dir1.");
  }
  // Documentation for 'popd' command
  public void manPopd() {
    System.out.println("NAME:");
    System.out.println("  popd - Sets the current working directory "
        + "to the one at the top of the directory stack.");
    System.out.println("DESCRIPTION:");
    System.out.println("  Removes the directory at the top of the "
        + "directory stack and sets the current working directory to "
        + "that directory.");
    System.out.println("PARAMETERS:");
    System.out.println("  There are no parameters available for this"
        + " command.");
    System.out.println("RETURNS:");
    System.out.println("  Will return an error message if the "
        + "directory stack is empty.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("If the directory stack is not empty.");
    System.out.println("  /#: popd");
    System.out.println("    will change the current working directory to "
        + "the one at the top of the stack.");
    System.out.println("If the directory stack is empty.");
    System.out.println("  /#: popd");
    System.out.println("    Will return an error message.");
  }
  // Documentation for 'history' command
  public void manHistory() {
    System.out.println("NAME:");
    System.out.println("  history [number] - Prints recent commands"
        + " entered");
    System.out.println("DESCRIPTION:");
    System.out.println("  Prints recent commands with each command on "
        + "their own line. There will be two columns. The first column "
        + "numbers the commands where the most recent is the highest"
        + " number. The second column contains the commands.");
    System.out.println("PARAMETERS:");
    System.out.println("  number - This parameter lets the user return "
        + "the last 'number' commands entered rather than all commands."
        + " An optional parameter.");
    System.out.println("RETURNS:");
    System.out.println("  Will return a list of commands entered.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("If no other command was entered.");
    System.out.println("  /#: history");
    System.out.println("    1. history");
    System.out.println("If we were to enter the pwd command first.");
    System.out.println("  /#: history");
    System.out.println("    1. pwd");
    System.out.println("    2. history");
    System.out.println("If we were to enter the pwd command twice first.");
    System.out.println("  /#: history 2");
    System.out.println("    1. pwd");
    System.out.println("    2. history");
  }
  // Documentation for 'cat' command
  public void manCat() {
    System.out.println("NAME:");
    System.out.println("  cat FILE1 [FILE2 ...] - Displays the contents"
        + " in the given file(s)");
    System.out.println("DESCRIPTION:");
    System.out.println("  Displays the contents of the file and "
        + "concatenates the contents of subsequent files to the contents"
        + " of the first.");
    System.out.println("PARAMETERS:");
    System.out.println("  FILE1 - The file with the contents to be"
        + " displayed.");
    System.out.println("  FILE2 - A subsequent file with the contents "
        + "to be displayed by concatenating it to FILE1. An optional"
        + " parameter.");
    System.out.println("RETURNS:");
    System.out.println("  Will display the contents of the given files.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("If FILE1 contains 'hello'");
    System.out.println("  /#: cat FILE1");
    System.out.println("    will return 'hello'");
    System.out.println("If FILE1 contains 'hello' and FILE2 contains "
        + "'there'");
    System.out.println("  /#: cat FILE1 FILE2");
    System.out.println("    Will return 'hellothere'.");
  }
  // Documentation for 'echo' command
  public void manEcho() {
    System.out.println("NAME:");
    System.out.println("  echo STRING [> OUTFILE |>> OUTFILE] - "
        + "Print or add STRING to standard output");
    System.out.println("DESCRIPTION:");
    System.out.println("  Prints STRING to the JShell if STRING is the"
        + " only parameter provided. If OUTFILE is provided, STRING will"
        + " overwrite whatever is in OUTFILE if > is used, or append "
        + "to OUTFILE if >> is used. If OUTFILE does not exist, it "
        + "will be created.");
    System.out.println("PARAMETERS:");
    System.out.println("  STRING - The string that will be sent to"
        + " standard output. Must be surrounded by double quotes");
    System.out.println("  >  - Overwrite OUTFILE's contents with STRING. "
        + "Optional parameter.");
    System.out.println("  >>  - Append OUTFILE's contents with STRING. "
        + "Optional parameter.");
    System.out.println("  OUTFILE  - The file that STRING will output to"
        + "depending on whether > or >> is used. OUTFILE will be created"
        + " if it does not already exist. Optional parameter");
    System.out.println("RETURNS:");
    System.out.println("  Will print STRING if STRING is the only "
        + "parameter.");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: echo \"Hello\"");
    System.out.println("    will display Hello");
    System.out.println("If FILE1 contains 'hello'");
    System.out.println("  /#: echo \"Hello\" > FILE1");
    System.out.println("    FILE1 will contain Hello");
    System.out.println("If FILE1 contains 'hello'");
    System.out.println("  /#: echo \"Hello\" >> FILE1");
    System.out.println("    FILE1 will contain helloHello.");
  }
  // Documentation for 'man' command
  public void manMan() {
    System.out.println("hi");
    System.out.println("NAME:");
    System.out.println("  man CMD - Displays documentation for CMD ");
    System.out.println("DESCRIPTION:");
    System.out.println("  Displays name, description, possible parameters"
        + " possible return messages, and example usage of cmd");
    System.out.println("PARAMETERS:");
    System.out.println("  CMD - The command for which the documentation "
        + "will be displayed.");
    System.out.println("RETURNS:");
    System.out.println("  Documentation for CMD");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: man man");
    System.out.println("    will display documentation for man.");
    System.out.println("  /#: man nam");
    System.out.println("    Will return an error message because nam"
        + " is not a valid command.");
  }
  // Documentation for 'find' command
  public void manFind() {
    System.out.println("NAME:");
    System.out.println("  find PATH ... -type [f|d] -name \"STRING\""
        + " - Displays all files or directories with name STRING in PATH.");
    System.out.println("DESCRIPTION:");
    System.out.println("  If -type f then it will find all files with "
        + "name STRING in PATH and display it. If type -d then it will"
        + " find all directories with name STRING in PATH.");
    System.out.println("PARAMETERS:");
    System.out.println("  PATH - The path to find STRING in.");
    System.out.println("  STRING - The name of the file/directory "
        + "the user is looking for");
    System.out.println("  -type [f|d] - f will find files named STRING "
        + "in PATH, d will find directories named STRING in PATH");
    System.out.println(" -name - parameter to specify STRING as the "
        + "name of the file tehey are looking for.");
    System.out.println("RETURNS:");
    System.out.println("  A list of all files/directories in PATH named "
        + "STRING");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("  /#: find /user/Desktop -type d -name \"a\"");
    System.out.println("    will display all directories in Desktop "
        + "named 'a'.");
    System.out.println("  /#: find /user/Desktop -type f -name \"b\"");
    System.out.println("    will display all directories in Desktop " + 
        "named \"b\".");
  }
  // Documentation for 'tree' command
  public void manTree() {
    System.out.println("NAME:");
    System.out.println("  tree - Displays the entire file system as a "
        + "tree");
    System.out.println("DESCRIPTION:");
    System.out.println("Displays the entire file system as a "
        + "tree starting from the root directory. For each level of "
        + "the tree, the subtree is indented");
    System.out.println("PARAMETERS:");
    System.out.println("  There are no parameters available for this"
        + " command.");
    System.out.println("RETURNS:");
    System.out.println("  A text representation of the file system");
    System.out.println("EXAMPLE USAGE:");
    System.out.println("If the root directory contains no subdirectories");
    System.out.println("  /#: tree");
    System.out.println("    will return /");
    System.out.println("If the root directory contains 2 subdirectories A"
        + " and B.");
    System.out.println("  /#: tree");
    System.out.println("   will return: ");
    System.out.println("   /");
    System.out.println("      A");
    System.out.println("      B");
    System.out.println("If the root directory contains 2 subdirectories A"
        + " and B with a sub directory C in A");
    System.out.println("  /#: tree");
    System.out.println("   will return: ");
    System.out.println("   /");
    System.out.println("      A");
    System.out.println("          C");
    System.out.println("      B");
  }

  @Override
  public String getCommandName() {
    return commandName;
  }
  @Override
  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean isValid = false;
    if (arguments.size() == numOfArguments) {
      isValid = true;
    }
    return isValid;
  }
  @Override
  public String getHelpText() {
    // Call the appropriate command
    manMan();
    return "";
  }
  
}
