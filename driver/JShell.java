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
import commandsystem.Commands;
import commandsystem.Result;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileExplorer;
import filesystem.Path;
import shell.JShellWindow;

public class JShell {

  /**
   * prompt the prompt for the user to start typing
   */
  private static String prompt = "/# ";

  /**
   * The main method. It intialized the command has table and contains an
   * instance of the JShellWindow.
   * 
   * @param args the args for the method
   * @param arguments the arguments of the command the first of which is a URL
   * @return the content from the URL as a string
   */
  public static void main(String[] args) {
    
    // Initializes the command hash table
    Commands.initializeCommandHashTable();
    // Create a new JShell window and scanner
    JShellWindow window = new JShellWindow();
    Scanner in = new Scanner(System.in);

    // While the window hasn't been terminated, continuously read user input
    // and attempt to run it as a command
    while (!window.getTerminate()) {

      System.out.print(prompt);
      // Get user input
      String input = in.nextLine();
      // Log the input
      window.addInputtoLog(input);
      
      // Attempt to run the command. If it fails print the error message.
      Result result = Commands.run(window, input);
      System.out.print(result.getMessage());
      
      if (result.errorOccured())
        System.out.println(result.getErrorMessage());
    }

    in.close();
  }
}
