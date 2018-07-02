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
import java.util.Arrays;
import shell.JShellWindow;

public class Commands {

  // This is a list of the valid commands
  private static Command[] commands = new Command[] {new History(), new Exit(),
      new Pwd(), new Ls(), new Mkdir(), new Cd(), new Tree(), new Echo(),
      new Find(), new Cat(), new Man(), new Pushd(), new Popd()};
  
  public static String run(JShellWindow jShell, String commandText) {
    // split at quotations for echo parameter 1
    commandText.split("\"");
    // Split the command into it's parts (i.e. separate blocks of text, quotes
    // and etc.)
    ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(
        split(commandText)));
    
    String message = null;
    
    // There must be at least one element in the arguments for it to be a
    // valid command
    if (arguments.size() > 0) {
      
      // The command name must be the first element
      String commandName = arguments.remove(0);
      Command command = getCommand(commandName);
      
      if (command != null) {
        if (command.areValidArguments(arguments)) {
          // We have found the command and it's arguments are valid. We can
          // now run the command
          message = command.run(jShell, arguments);
          
        }
      }
    }
    
    return message;
  }
  
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
      }
      else
        i++; // It isn't. Go to the next command
    }
    
    return command;
  }
  
  private static String[] split(String text) {

    boolean inQuotes = false;
    boolean atTextEnd = false;

    int splits = 0;
 
    for (int i = 0; i < text.length(); i++) {

      if (text.charAt(i) == '"') {

        if (!inQuotes && atTextEnd)
          atTextEnd = false;

        inQuotes = !inQuotes;
      }

      if (!inQuotes) {
        if (atTextEnd) {
          if (text.charAt(i) != ' ')
            atTextEnd = false;
        }
        if (text.charAt(i) == ' ' && !atTextEnd || i == text.length() - 1) {
          atTextEnd = true;
          splits++;
        }
      }
    }


    inQuotes = false;
    atTextEnd = false;

    int currentSplit = 0;
    String[] splitText = new String[splits];

    int beginIndex = 0;
    int numQuotes = text.length() - text.replace("\"", "").length();
    if (numQuotes%2 == 0) {

      for (int i = 0; i < text.length(); i++) {

        if (text.charAt(i) == '"') {

          if (!inQuotes) {
            atTextEnd = false;
            beginIndex = i;
          }
          inQuotes = !inQuotes;
        }

        if (!inQuotes) {
          if (atTextEnd) {
            if (text.charAt(i) != ' ') {
              beginIndex = i;
              atTextEnd = false;
            }
          }
          else {
            if (text.charAt(i) == ' ') {
              atTextEnd = true;
              splitText[currentSplit] = text.substring(beginIndex, i);
              currentSplit++;
            }
          }

        }
        if (i == text.length() - 1) {
          splitText[currentSplit] = text.substring(beginIndex);
        }
      }
    }
    return splitText;
  }
}
