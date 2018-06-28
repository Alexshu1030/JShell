package commands;

import java.util.ArrayList;
import java.util.Arrays;
import driver.JShell;
import shell.JShellWindow;

public class Commands {

  private static Command[] commands = new Command[] {new Pwd()};
  
  public static boolean Run(JShellWindow jShell, String command) {
    
    ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(
        Split(command)));
    
    int i = 0;
    boolean found = false;
    boolean succeeded = false;
   
    while (i < commands.length && !found) {
      if (commands[i].IsValidCommand(arguments)) {
        succeeded = commands[i].Run(jShell, arguments);
        found = true;
      }
      else
        i++;
    }
    
    return succeeded;
  }
  
  public static boolean IsValidCommand(String command) {
    
    ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(
        Split(command)));
    
    int i = 0;
    boolean isValid = false;
   
    while (i < commands.length && !isValid) {
      if (commands[i].IsValidCommand(arguments))
        isValid = true;
      else
        i++;
    }
    
    return isValid;
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
