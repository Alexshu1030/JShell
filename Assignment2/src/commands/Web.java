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
import java.util.Scanner;
import shell.JShellWindow;
import java.io.IOException;
import java.net.URL;

public class Web {

  public String grabContents(JShellWindow jShell, ArrayList<String> arguments) {
    
    String result = "";
    String url = arguments.get(0);
    boolean valid;
    // Check if the URL is valid first
    try {
      new URL(url).toURI();
      valid = true;
    }
    catch (Exception e) {
      valid = false; 
    }
    // If it is valid, grab the contents from the url
    if (valid == true) {
      try {
        URL urlcontents = new URL(url);
        Scanner s = new Scanner(urlcontents.openStream());
        // put the contents into a file
        // close s to prevent a resource leak
        result = s.nextLine();
        s.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return result;
  }

}
