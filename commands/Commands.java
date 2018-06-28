package commands;

public class Commands {

  public static boolean IsValidCommand(String command) {
    
    // Split the command by whitespace
    String[] splitCommand;
    
    
    return CommandExists(splitCommand[0]) && CommandArgumentsAreValid(command);
  }
  
  private static boolean CommandExists(String commandName) {
    
    return false;
  }
  
  private static boolean CommandArgumentsAreValid(String command) {
    
    return false;
  }
}
