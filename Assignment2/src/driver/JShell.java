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

    while (!window.getTerminate()) {
      
      System.out.print(prompt);
      String input = in.nextLine();
      input = input.trim();
      window.addInputtoLog(input);
      
      if (!Commands.run(window, input)) {
        System.out.println(errorMessage);
      }
    }
    
    in.close();
  }
}