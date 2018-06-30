package commands;

import java.util.ArrayList;
import filesystem.FileExplorer;
import shell.JShellWindow;

public class Pwd implements Command {

  private String commandName = "pwd";
  private int numOfArguments = 0;
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

  public boolean areValidArguments(ArrayList<String> arguments) {

    return arguments.size() == numOfArguments;
  }

  public String getHelpText() {

    return helpText;
  }

  public boolean run(JShellWindow jShell, ArrayList<String> arguments) {

    FileExplorer fileExplorer = jShell.getFileExplorer();

    String workingDirPath = fileExplorer.getWorkingDirectory().getFullPath();

    System.out.println(workingDirPath);

    return true;
  }

  public String getCommandName() {

    return commandName;
  }
}