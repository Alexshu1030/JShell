package commands;

import java.util.ArrayList;
import shell.JShellWindow;

public class Cat implements Command{
  private String command = "cat";
  
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    for (int i = 0; i < arguments.size(); i++) {
      // We need to replace the argument at i with a File object
      //System.out.println(arguments.get(i).getFileContents());
      // make sure we are not at the last file
      if (i != arguments.size()-1) {
        System.out.print("\n\n\n");
      }
    }
    
    return false;
  }
  
  public String getCommandName() {
    return command;
  }
  public boolean areValidArguments(ArrayList<String> arguments) {
    
    return (arguments.size() >= 1);
  }
  
  public String getHelpText() {
    Man.manCat();
    return "";
  }
}
