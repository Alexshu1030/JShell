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
import commands.Cat;
import commands.Cd;
import commands.Echo;
import commands.Exit;
import commands.Find;
import commands.Ls;
import commands.Man;
import commands.Mkdir;
import commands.Mv;
import commands.Number;
import commands.Popd;
import commands.Pushd;
import commands.Pwd;
import commands.Tree;
import shell.JShellWindow;

public class Commands {

  /**
   * commands[] The list of valid commands
   */
  private static Command[] commands = new Command[] {new History(), new Exit(),
      new Pwd(), new Ls(), new Mkdir(), new Cd(), new Tree(), new Echo(),
      new Find(), new Cat(), new Man(), new Pushd(), new Popd(), new Mv(),
      new Number()};

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
      String commandName;
      // if the first character is ! then Number is the command name
      if (arguments.get(0).charAt(0) == '!') {
        commandName = "!number";
        // set the rest of the command as the actual argument
        String arg = arguments.get(0).substring(1);
        arguments.set(0, arg);
      }
      // The command name must be the first element
      else {
        commandName = arguments.remove(0);
      }
      Command command = getCommand(commandName);
      if (command != null) {
        
     // We have found the command and it's arguments are valid. We can
        // now run the command
        // set type of write into file and file name to null
        String outFile = "";
        String fileName = "";
        // set the found index of > or >> to -1
        int foundIndex = -1;
        // iterate through arguments and get type of write in file, file name
        // and found index
        for (int i = 0; i < arguments.size(); i++) {
          if (arguments.get(i).equals(">")) {
            outFile = ">";
            fileName = arguments.get(i + 1);
            foundIndex = i;
          } else if (arguments.get(i).equals(">>")) {
            outFile = ">>";
            fileName = arguments.get(i + 1);
            foundIndex = i;
          }
        }


        // if there is no > or >> found, run the command normally
        if (outFile.equals("")) {
          result = command.run(jShell, arguments);
        // otherwise
        } else {
          // get the part of the list before the > or >>
          ArrayList<String> putFile =
              (ArrayList<String>) arguments.subList(0, foundIndex);
          // run the part of the list and see if its false
          boolean invalid = false;
          try {
            result = command.run(jShell, putFile);
          } catch (Exception e) {
            invalid = true;
          }
          // if the run was successful
          if (!invalid) {
            // setup a list of commands to be used with echo
            ArrayList<String> echoInto = new ArrayList<String>();
            // add the message, type of write, and file name into the list
            echoInto.add(result.getMessage());
            echoInto.add(outFile);
            echoInto.add(fileName);
            // get the echo command and run it
            command = getCommand("echo");
            command.run(jShell, echoInto);
            // set return to null
            return new Result();
          }
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
