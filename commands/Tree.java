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

public class Tree implements Command {
  /**
   * commandName The command name of the current class
   */
  private String commandName = "tree";
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n" +
      "  tree - Displays the entire file system as a "
      + "tree\n" +
  "DESCRIPTION:\n" +
  "Displays the entire file system as a "
      + "tree starting from the root directory. For each level of "
      + "the tree, the subtree is indented\n" +
  "PARAMETERS:\n" +
  "  There are no parameters available for this"
      + " command.\n" +
  "RETURNS:\n" +
  "  A text representation of the file system\n" +
  "EXAMPLE USAGE:\n" +
  "If the root directory contains no subdirectories\n" +
  "  /#: tree\n" +
  "    will return /\n" +
  "If the root directory contains 2 subdirectories A"
      + " and B.\n" +
  "  /#: tree\n" +
  "   will return: \n" +
  "   /\n" +
  "      A\n" +
  "      B\n" +
  "If the root directory contains 2 subdirectories A"
      + " and B with a sub directory C in A\n" +
  "  /#: tree\n" +
  "   will return: \n" +
  "   /\n" +
  "      A\n" +
  "          C\n" +
  "      B\n";

  /**
   * Returns true if there are no arguments given
   * 
   * @return isValid this is true if there are no arguments, o/w false
   */
  public boolean areValidArguments(ArrayList<String> arguments) {
    boolean isValid = arguments.isEmpty();
    return isValid;
  }


  /**
   * Prints help text that includes documentation of History
   */
  public String getHelpText() {

    return helpText;
  }


  /**
   * Returns true if execution of "tree" is successful. Execution prints a
   * diagram of the directory hierarchy in the shell
   * 
   * @param jShell this is the window that will be printed on
   * @param arguments this a list containing null
   * @return true this is true if execution is successful
   */
  public String run(JShellWindow jShell, ArrayList<String> arguments) {

    Directory rootDir = FileExplorer.getRootDirectory();
    // The double back slashes are to add the backslash at the beginning
    // that represents the root directory
    String treeRep = "\\" + getTreeRepresentation(rootDir, 0);

    return treeRep;
  }

  /**
   * Returns the string representation of the tree directory hierarchy
   * 
   * @param file this is the current directory
   * @param depth this is the depth of the current directory
   * @return text this is the string representation of the tree
   */
  private String getTreeRepresentation(File file, int depth) {

    // Indent the line according to the depth and then added the file name
    // and go to the next line
    String text = repeat("\t", depth) + file.getFileName() + "\n";

    // If the file is a directory then we need to print the sub files
    if (file.isDirectory()) {

      // The the sub files in the directory
      Directory dir = (Directory) file;
      ArrayList<File> files = (ArrayList<File>) dir.getFileContents();

      // Go through each of them and run this method on them with the
      // increased depth
      for (int i = 0; i < files.size(); i++) {

        text += getTreeRepresentation(files.get(i), depth + 1);
      }
    }

    return text;
  }

  /**
   * Returns a string containing sufficient indentations to build string
   * representation of the depth of the current directory
   * 
   * @param string this is "\t"
   * @param times this is the amount of indents
   * @return newString this is the final string with appropriate indents
   */
  private String repeat(String string, int times) {

    String newString = "";

    // Add the <string> to <newString>, <times> times;
    for (int i = 0; i < times; i++) {
      newString += string;
    }

    return newString;
  }

  /**
   * Returns the help text for this command.
   * 
   * @return the help text for this command
   */
  public String getCommandName() {

    return commandName;
  }

}
