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
import driver.JShell;
import shell.JShellWindow;

public class Commands {

  private static Command[] commands = new Command[] {new Exit(), new Pwd(), new Ls(), 
      new Mkdir(), new Cd()};
  
  public static boolean Run(JShellWindow jShell, String command) {
    
    ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(
        Split(command)));
    
    boolean succeeded = false;
    
    if (arguments.size() > 0) {
      
      String commandName = arguments.remove(0);
      
      int i = 0;
      boolean found = false;
     
      while (i < commands.length && !found) {
        if (commands[i].IsValidCommand(commandName, arguments)) {
          succeeded = commands[i].Run(jShell, arguments);
          found = true;
        }
        else
          i++;
      }
    }
    
    return succeeded;
  }
  
  public static boolean IsValidCommand(String command) {
    
    ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(
        Split(command)));
    
    boolean isValid = false;
    
    if (arguments.size() > 0) {
      
      String commandName = arguments.remove(0);
    
      int i = 0;
     
      while (i < commands.length && !isValid) {
        if (commands[i].IsValidCommand(commandName, arguments))
          isValid = true;
        else
          i++;
      }
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
