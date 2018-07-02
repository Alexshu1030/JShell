package commands;

import java.util.ArrayList;
import filesystem.DirectoryStack;
import shell.JShellWindow;

public class Popd implements Command{
  
  private String helpText = "NAME:\n" +
      "  popd - Sets the current working directory "
      + "to the one at the top of the directory stack.\n" +
  "DESCRIPTION:\n" +
  "  Removes the directory at the top of the "
      + "directory stack and sets the current working directory to "
      + "that directory.\n" +
  "PARAMETERS:\n" +
  "  There are no parameters available for this"
      + " command.\n" +
  "RETURNS:\n" +
  "  Will return an error message if the "
      + "directory stack is empty.\n" +
  "EXAMPLE USAGE:\n" +
  "If the directory stack is not empty.\n" +
  "  /#: popd\n" +
  "    will change the current working directory to "
      + "the one at the top of the stack.\n" +
  "If the directory stack is empty.\n" +
  "  /#: popd\n" +
  "    Will return an error message.\n";

  public String run(JShellWindow jShell, ArrayList<String> arguments) {
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
