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
package commandsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import commands.*;
import commands.Number;
import exceptions.FileNotFoundException;
import exceptions.InvalidPathException;
import filesystem.File;
import shell.JShellWindow;

public class Commands {

  /**
   * commands[] The list of valid commands
   */
  private static Command[] commands = new Command[] {new History(), new Exit(),
      new Pwd(), new Ls(), new Mkdir(), new Cd(), new Tree(), new Echo(),
      new Find(), new Cat(), new Man(), new Pushd(), new Popd(), new Mv(),
      new Number(), new Curl(), new Cp()};


  private static Hashtable<String, String> commandHashTable =
      new Hashtable<String, String>();

  public static void initializeCommandHashTable() {

    commandHashTable.put("cat", "Cat");
    commandHashTable.put("cd", "Cd");
    commandHashTable.put("cp", "Cp");
    commandHashTable.put("curl", "Curl");
    commandHashTable.put("echo", "Echo");
    commandHashTable.put("exit", "Exit");
    commandHashTable.put("find", "Find");
    commandHashTable.put("grep", "Grep");
    commandHashTable.put("history", "History");
    commandHashTable.put("ls", "Ls");
    commandHashTable.put("man", "Man");
    commandHashTable.put("mkdir", "Mkdir");
    commandHashTable.put("!", "Number");
    commandHashTable.put("mv", "Mv");
    commandHashTable.put("popd", "Popd");
    commandHashTable.put("pushd", "Pushd");
    commandHashTable.put("pwd", "Pwd");
    commandHashTable.put("tree", "Tree");
  }
  
  /**
   * Returns true if execution of this class is successful. Execution print or
   * add string to standard output, or overwrites or appends a string to a file
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be listed
   * @return result true if execution was successful
   */
  public static Result run(JShellWindow jShell, String commandText) {
    // split at quotations for echo parameter 1
    commandText.split("\"");
    // Split the command into it's parts (i.e. separate blocks of text, quotes,
    // etc.)
    ArrayList<String> arguments = split(commandText);
    
    Result result = new Result();

    // There must be at least one element in the arguments for it to be a
    // valid command
    if (arguments.size() > 0) {
      
      String commandName = arguments.remove(0);
      Command command = getCommand(commandName);
      
      if (command != null) {      
        if (isRedirected(arguments)) {
          if (command.canBeRedirected()) {
            // The command can be redirected and it has been.
            // Get the redirection arguments
            String outFilePath = arguments.remove(arguments.size() - 1);
            String operationType = arguments.remove(arguments.size() - 1);
            // Run the command storing the result in a separate class
            Result redirectResult = command.run(jShell, arguments);
            
            // Try to write the result message to the outFile path
            try {
              TextEditor.writeText(jShell.getFileExplorer(), outFilePath,
                  redirectResult.getMessage(), operationType.equals(">>"));
              // If we reach this point the text was successfully written.
              // We now want to added any errors that occurred during this
              // process to the return of this command so it is forwarded
              // to the JShellWindow
              result.addErrors(redirectResult);
            }
            catch (InvalidPathException e) {
              result.logError("Invalid outFile path.");
            }
          }
          else
            result.logError("Command can not be redirected.");
        }
        else {
          // The command has not been redirected so we can run it normally
          result = command.run(jShell, arguments);
        }
      }
      else
        result.logError("Command does not exist.");
    }
    else {
      result.logError("Invalid input.");
    }
    
    return result;
  }

  /**
   * Returns the command with the specified name.
   * 
   * @param name the name of the command
   * @return the command with the specified name
   */
  public static Command getCommand(String name) {

    int i = 0;
    boolean found = false;
    Command command = null;
    // Iterate over the commands until the correct one has been found
    while (i < commands.length && !found) {
      // Check if the name of the current command is the one we want
      if (commands[i].getCommandName().equals(name)) {
        // It is. We can now exit the loop
        command = commands[i];
        found = true;
      } else
        i++; // It isn't. Go to the next command
    }

    return command;
  }
  
  private static boolean isRedirected(ArrayList<String> arguments) {
    
    boolean isRedirected = false;
    
    if (arguments.size() > 1) {
      if (arguments.get(arguments.size() - 2).equals(">") ||
          arguments.get(arguments.size() - 2).equals(">>")) {
        isRedirected = true;
      }
    }
    
    return isRedirected;
  }

  /**
   * Splits the text by spaces and groups it by quotations. Blocks of text in
   * quotations will be kept as one group. These groups are then split by
   * whitespace.
   * 
   * @param text the text to be split
   * @return the text split into groups
   */
  private static ArrayList<String> split(String text) {

    ArrayList<String> splits = new ArrayList<String>();
    int lastSplitIndex = 0;

    for (int i = 0; i < text.length(); i++) {

      char currentChar = text.charAt(i);

      if (i == 0 && currentChar == '!') {
        // If the first char is a exclamation point then we want to create a
        // split for it
        splits.add("!");
        lastSplitIndex = i + 1;
      }
      if (i == text.length() - 1) {
        // If we are at the end of the string then we want to split here no
        // matter what
        String split = text.substring(lastSplitIndex, text.length());
        splits.add(split);

      } else if (currentChar == ' ') {

        String split = text.substring(lastSplitIndex, i);
        splits.add(split);

        // The current char is a space. We want to skip to the next non-space
        while (currentChar == ' ' && i != text.length() - 1) {
          currentChar = text.charAt(++i);
        }

        lastSplitIndex = i;
        // Now that we have reached the end of the spaces we want to go back
        // one index. When the loop ends it will iterate back to the current
        // index.
        i--;

      } else if (currentChar == '"') {

        // We want to get the next quotation mark *after* this one so go to
        // the next character.
        currentChar = text.charAt(++i);
        // The current char is a quotation mark. We want to skip to the end
        // quotation mark.
        while (currentChar != '"' && i != text.length() - 1) {
          currentChar = text.charAt(++i);
        }

        // If we are at the end of the string then we want to create a split.
        if (i == text.length() - 1) {
          String split = text.substring(lastSplitIndex, text.length());
          splits.add(split);
        }
      }
    }

    return splits;
  }
}
