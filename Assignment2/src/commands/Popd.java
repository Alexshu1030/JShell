package commands;

import java.util.ArrayList;
import filesystem.DirectoryStack;
import shell.JShellWindow;

public class Popd implements Command{
  
  private String helpText = "NAME:" +
      "  popd - Sets the current working directory "
      + "to the one at the top of the directory stack." +
  "DESCRIPTION:" +
  "  Removes the directory at the top of the "
      + "directory stack and sets the current working directory to "
      + "that directory." +
  "PARAMETERS:" +
  "  There are no parameters available for this"
      + " command." +
  "RETURNS:" +
  "  Will return an error message if the "
      + "directory stack is empty." +
  "EXAMPLE USAGE:" +
  "If the directory stack is not empty." +
  "  /#: popd" +
  "    will change the current working directory to "
      + "the one at the top of the stack." +
  "If the directory stack is empty." +
  "  /#: popd" +
  "    Will return an error message.";

  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    DirectoryStack.stack.popd(jShell);
    return true;
  }

  public String getCommandName() {
    // TODO Auto-generated method stub
    return "popd";
  }

  public boolean areValidArguments(ArrayList<String> arguments) {
    // TODO Auto-generated method stub
    return arguments.size() == 0;
  }

  public String getHelpText() {
    
    return helpText;
  }

}
