package commandsystem;

import exceptions.InvalidPathException;
import filesystem.*;

public class TextEditor {
  
  private static String separator = "/n/n/n";

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
