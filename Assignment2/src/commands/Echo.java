package commands;

import java.util.ArrayList;
import shell.JShellWindow;
import filesystem.FileExplorer;
import filesystem.File;
public class Echo implements Command {
  private String command = "echo";
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    if (arguments.size() == 1) {
      System.out.println(arguments.get(0));
    }
    else if (arguments.size() == 2) {
      if (arguments.get(1) == ">" || arguments.get(1) == ">>") {
        System.out.println(arguments.get(0));
      }
    }
    else if (arguments.size() == 3) {
      if (arguments.get(1) == ">") {
       // We need to see if the file/argument3 exists in the current folder then 
       // set its contents to the first argument i.e. arguments.get(0)
      }
      else if (arguments.get(1) == ">>") {
     // We need to see if the file/argument3 exists in the current folder then
     // put the current contents in a string and append a line separator to
     // it (which is a requirement) then append argument1 to the string.
     // Finally set the contents of the file to be the string
      }
    }
    return true;
  }
  
  public String getCommandName() {
    return command;
  }
  
  public boolean areValidArguments(ArrayList<String> arguments) {
    
    return (arguments.size() <= 3 && arguments.size() >= 1);
  }
    
  
  public String getHelpText() {
    Man.manEcho();
    return "";
  }
}
