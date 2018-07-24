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
import java.util.Scanner;
import shell.JShellWindow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Web {

  /**
   * Grabs the content from the specified URL as a string and returns it.
   * 
   * @param jShell the window the command is being run on.
   * @param arguments the arguments of the command the first of which is a URL
   * @return the content from the URL as a string
   */
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
        URLConnection connection = urlcontents.openConnection();
        Scanner s = new Scanner(urlcontents.openStream());
        // put the contents into the return variable
        // close s to prevent a resource leak
        BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) 
          result += line + "\n";
        in.close();
        s.close();
      } catch (IOException e) {
        result = "Invalid URL";
      }
    }
    return result;
  }

}
