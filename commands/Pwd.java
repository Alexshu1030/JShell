package commands;

import java.util.ArrayList;
import driver.JShell;
import filesystem.FileExplorer;

public class Pwd implements Command {

  private String commandName = "pwd";
  private int numOfArguments = 1;
  private String helpText = "NAME:\n"
      + "  pwd - Prints current working directory\n"
      + "DESCRIPTION:\n"
      + "  Prints the full path of the current working directory.\n"
      + "PARAMETERS:\n"
      + "  There are no parameters available for"
      + " this command.\n"
      + "RETURNS:\n"
      + "  This command does not return anything.\n"
      + "EXAMPLE USAGE:\n"
      + "  /#: exit\n"
      + "    will successfully terminate JShell.\n"
      + "  /#: exit p\n"
      + "    will not terminate JShell because of the "
      + " additional parameter 'p'.\n"
      + "  /#:              exit\n"
      + "    will terminate JShell because\n"
      + " JShell will ignore excess spaces.\n";
  
  public boolean IsValidCommand(ArrayList<String> arguments) {
    
    boolean isValid = false;
    
    if (arguments.size() == numOfArguments) {
      if (arguments.get(0).equals(commandName)) {
        isValid = true;
      }
    }
    
    return isValid;
  }

  public String GetHelpText() {
    
    return helpText;
  }

  public void Run(JShell jShell, ArrayList<String> arguments) {
    
    FileExplorer fileExplorer = jShell.GetFileExplorer();
    
    String workingDirPath = fileExplorer.getWorkingDirectory().GetFullPath();
    
    System.out.println(workingDirPath);
  }
}
