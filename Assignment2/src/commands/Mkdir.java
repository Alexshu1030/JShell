package commands;
import java.util.ArrayList;
import filesystem.*;
import shell.JShellWindow;
public class Mkdir implements Command {

  private String commandName = "mkdir";
  private String helpText = "Not finished...";
  
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    
    FileExplorer explorer = jShell.GetFileExplorer();
    
    String path = arguments.get(0);
    Directory parentDir = explorer.getDirectory(path);
    String dirName = Path.getFileName(path);
    
    Directory newDir = new Directory(dirName, parentDir);
    parentDir.addFile(newDir);
    
    return true;
  }

  public String GetCommandName() {
    
    return commandName;
  }

  public boolean IsValidCommand(String commandName,
      ArrayList<String> arguments) {
    
    boolean isValid = false;
    
    if (this.commandName.equals(commandName)) {
      if (arguments.size() == 1) {
        isValid = true;
      }
    }
    
    return isValid;
  }

  public String GetHelpText() {
   
    return helpText;
  }
}
