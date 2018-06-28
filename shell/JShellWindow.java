package shell;
import java.util.Scanner;
import commands.Commands;
import filesystem.FileExplorer;

public class JShellWindow {
  
  private FileExplorer fileExplorer = new FileExplorer();
  
  public FileExplorer GetFileExplorer() {
    
    return fileExplorer;
  }
}
