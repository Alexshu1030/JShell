package commands;

import java.util.ArrayList;
import filesystem.DirectoryStack;
import shell.JShellWindow;

public class Popd implements Command{

  @Override
  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {
    DirectoryStack.stack.popd(jShell);
    return true;
  }

  @Override
  public String getCommandName() {
    // TODO Auto-generated method stub
    return "popd";
  }

  @Override
  public boolean areValidArguments(ArrayList<String> arguments) {
    // TODO Auto-generated method stub
    return arguments.size() == 0;
  }

  @Override
  public String getHelpText() {
    // Create a Man object to access the man commands
    Man man = new Man();
    // Call the appropriate command
    man.manPopd();
    return "";
  }

}
