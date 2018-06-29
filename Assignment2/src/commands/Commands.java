package commands;

import java.util.ArrayList;
import java.util.Arrays;
import shell.JShellWindow;

public class Commands {

  // This is a list of the valid commands
  private static Command[] commands = new Command[] {new Exit(), new Pwd(), new Ls(), 
      new Mkdir(), new Cd()};
  
  public static boolean Run(JShellWindow jShell, String commandText) {
    
    // Split the command into it's parts (i.e. separate blocks of text, quotes
    // and etc. )
    ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(
        Split(commandText)));
    
    boolean succeeded = false;
    
    // There must be at least one element in the arguments for it to be a
    // valid command
    if (arguments.size() > 0) {
      
      // The command name must be the first element
      String commandName = arguments.remove(0);
      Command command = GetCommand(commandName);
      
      if (command != null) {
        if (command.AreValidArguments(arguments)) {
          // We have found the command and it's arguments are valid. We can
          // now run the command
          succeeded = command.Run(jShell, arguments);
        }
      }
    }
    
    return succeeded;
  }
  
  public static Command GetCommand(String name) {
    
    int i = 0;
    boolean found = false;
    Command command = null;
   
    // Iterate over the commands until the correct one has been found
    while (i < commands.length && !found) {
      // Check if the name of the current command is the one we want
      if (commands[i].GetCommandName().equals(name)) {
        // It is. We can now exit the loop
        command = commands[i];
        found = true;
      }
      else
        i++; // It isn't. Go to the next command
    }
    
    return command;
  }
  
  private static String[] Split(String text) {

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
