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
package driver;

import java.util.ArrayList;
import java.util.Scanner;
import commands.Commands;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileExplorer;
import filesystem.Path;
import shell.JShellWindow;

public class JShell {

  private static String prompt = "/# ";
  private static String errorMessage = "Invalid command, please try again";
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    JShellWindow window = new JShellWindow();
    
    Scanner in = new Scanner(System.in);

    Commands.run(window, "mkdir A");
    Commands.run(window, "mkdir B");
    Commands.run(window, "mkdir C");
    Commands.run(window, "mkdir A/A1");
    Commands.run(window, "mkdir A/A2");
    Commands.run(window, "mkdir A/A1/A11");
    Commands.run(window, "mkdir A/A1/A12");
    Commands.run(window, "mkdir B/B1");
    Commands.run(window, "mkdir B/B2");
    Commands.run(window, "mkdir B/B3");
    
    while (!window.getTerminate()) {
      
      System.out.print(prompt);
      String input = in.nextLine();
      input = input.trim();
      window.addInputtoLog(input);
      
      Commands.run(window, input);
      
    }
    
    in.close();
  }

  /*
  private static boolean IsValidCommand(String[] input) {

    boolean isCommand = false;
    int inputLen = input.length;

    // If there is no input then it can't be a command
    if (inputLen > 0) {
      // Check if the first split matches any of the commands
      for (int i = 0; i < commandNames.length; i++) {
        if (commandNames[i].equals(input[0])) {
          // A command has entered. Now we have to make sure that there are
          // the correct number of arguments
          if (commandArgs[i] == specificCase) {
            // If command is mkdir or cat, then there must be 1 or more arguments
            if ((i == 1 || i == 8 ) && inputLen >= 2) {
              isCommand = true;
              // if command is ls, it is always valid
            } else if (i == 3) {
              isCommand = true;
              // if command is history, either no argument or integer argument
            } else if (i == 7) {
              // no argument
              if (inputLen == 1) {
                isCommand = true;
                // check if argument is an integer
              } else if (inputLen == 2) {
                try {
                  Integer.parseInt(input[1]);
                } catch (Exception NumberFormatException) {
                  return isCommand;
                }
                isCommand = true;
              }
              // if command is echo, it is valid if printing on shell
            } else if (i == 9) {
              if (inputLen == 2) {
                int secArgLen = input[1].length();
                // echo's STRING input must have quotations around it
                if (input[1].substring(0, 1).equals("\"") &&
                    input[1].substring(secArgLen - 1, secArgLen).equals("\"")) {
                  isCommand = true;
                }
              } else if (inputLen == 4) {
                // Otherwise the second argument must be the > or >>
                int secArgLen = input[1].length();
                if (input[2].equals(">>") || input[2].equals(">")) {
                  // echo's STRING input must have quotations around it
                  if (input[1].substring(0, 1).equals("\"") &&
                      input[1].substring(secArgLen - 1, secArgLen).equals("\"")) {
                    isCommand = true;
                  }
                }
              }
            }
          } else if (commandArgs[i] == inputLen - 1) {
            // The command name is correct and there are the right number of
            // arguments. This is a command.
            isCommand = true;
          }
        }
      }
    }
    return isCommand;
  }
  */
}