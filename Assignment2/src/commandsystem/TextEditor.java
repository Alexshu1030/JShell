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

import exceptions.InvalidPathException;
import filesystem.*;

public class TextEditor {

  /**
   * Writes the specified text into the file at filePath. If not file exists
   * at that path one is created. If a file exists and append is true the
   * the text is added to the end of the file. If append is false then the
   * file is overwritten.
   * 
   * @param explorer the FileExplorer being used.
   * @param filePath the path of the file.
   * @param text the text to be written.
   * @param append whether to append or overwrite the text
   */
  public static void writeText(FileExplorer explorer, String filePath, 
      String text, boolean append) throws InvalidPathException {
    
    File file = explorer.getOrCreateFile(filePath);
    
    if (append) {
      // Append the text to the end of the file
      String fileContents = (String)file.getFileContents();
      file.setFileContents(fileContents + text);
    }
    else {
      // Replace the current file content with the text
      file.setFileContents(text);
    }
  }
}
