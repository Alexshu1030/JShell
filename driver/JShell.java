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

public class JShell {

  public static ArrayList<String> commandLog = new ArrayList<String>();

  private static String prompt = "/# ";
  private static String errorMessage = "Invalid command, please try again";

  private static String[] commandNames = new String[] {"exit", "mkdir", "cd", 
      "ls", "pwd", "pushd", "popd", "history", "cat", "echo", "man"};
  private static int specificCase = -1;
  private static int[] commandArgs = new int[] {0, specificCase, 1, specificCase, 0, 1, 0, specificCase, specificCase, specificCase, 1};

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    Scanner in = new Scanner(System.in);
    boolean terminate = false;

    while (!terminate) {

      System.out.print(prompt);
      String input = in.nextLine();
      input = input.trim();
      commandLog.add(input);
      String[] splitInput = Split(input);

      int argumentsLen = splitInput.length;

      // Check if the input is a valid command
      if (IsValidCommand(splitInput)) {        
        // It is the exit command. Leave the loop.
        if (splitInput[0].equals(commandNames[0])) {
          terminate = true;
        } else {
          // Print the command
          System.out.println(splitInput[0]);

          if (argumentsLen > 1) {
            for (int i = 1; i < argumentsLen; i++) {

              System.out.print(splitInput[i]);

              // Adds spaces between the arguments
              if (i < argumentsLen - 1) {
                System.out.print(" ");
              }
            }

            // End this line
            System.out.println();
          }
          else {
            // The command has no arguments and we need to
            // print a blank line
            System.out.println();
          }
        }
      }
      else {
        // The command is not valid. Print the error message.
        System.out.println(errorMessage);
      }
    }
    in.close();
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
}