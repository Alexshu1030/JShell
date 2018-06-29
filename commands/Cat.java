package commands;

import java.util.ArrayList;
import shell.JShellWindow;

public class Cat {
  private String command = "cat";
  public boolean Run(JShellWindow jShell, ArrayList<String> arguments) {
    for (int i = 0; i < arguments.size(); i++) {
      // We need to replace the argument at i with a File object
      System.out.println(arguments.get(i).getFileContents());
      // make sure we are not at the last file
      if (i != arguments.size()-1) {
        System.out.print("\n\n\n");
      }
    }
  }
  
  public String GetCommandName() {
    return command;
  }
  public boolean AreValidArguments(ArrayList<String> arguments) {
    
    return (arguments.size() >= 1);
  }
  
  public void GetHelpText() {
    Man.manCat();
  }
}
