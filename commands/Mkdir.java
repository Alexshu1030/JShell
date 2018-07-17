// **********************************************************
// Assignment2:
// Student1: Ciaran Hogan
// UTORID user_name: hogancia
// UT Student #: 1003796859
// Author: Ciaran Hogan
//
// Student2: Jeremy Tanuan
// UTORID user_name: tanuanje
// UT Student #: 1003939982
// Author: Jeremy Tanuan
//
// Student3: Aster Wu
// UTORID user_name: wuaster
// UT Student #: 1004175117
// Author: Aster Wu
//
// Student4: Yan Chen Huang
// UTORID user_name: huan1085
// UT Student #: 1004325857
// Author: Yan Chen Huang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package commands;

import java.util.ArrayList;
import exceptions.FileNotFoundException;
import exceptions.PathExistsException;
import filesystem.*;
import shell.JShellWindow;

public class Mkdir implements Command {
  // set the command name as mkdir to be used for calling
  /**
   * commandName The command name of the current class
   */
  private String commandName = "mkdir";
  /**
   * helpText The help text for the current class
   */
  private String helpText = "NAME:\n" + "  mkdir DIR1 [DIR2] ... [PATH] -"
      + " Create directories\n" + "DESCRIPTION:\n"
      + "  Creates a directory named in the first parameter"
      + " in the location of the directory given in the optional second"
      + " parameter. If no second parameter is given, the directory is"
      + " created in the current directory.\n" + "PARAMETERS:\n"
      + "  DIR1 - The name of the directory. The only valid"
      + " characters for the name are from a-z, A-Z, 0-9.\n"
      + "  DIR2 - The name of a second directory." + " An optional parameter.\n"
      + "  [PATH] - The path that the user wants the "
      + "directory(ies) to be created in. The path may be a relative"
      + " path or a full path. An optional parameter.\n" + "RETURNS:\n"
      + "  This command does not return anything.\n" + "EXAMPLE USAGE:\n"
      + "  /#: mkdir Dir1\n" + "    will create a directory named Dir1 in the"
      + " current directory.\n" + "  /#: mkdir Dir1 Dir2 Dir3\n"
      + "    will create a directory named Dir1, "
      + "a directory named Dir2, a directory named Dir3 in the"
      + " current directory.\n" + "  /#: mkdir Dir1 /Dir2/\n"
      + "    will create a directory named Dir1 inside the"
      + " directory named Dir2 that is located in the current" + " directory.\n"
      + " If Dir2 does not exist in the current directory,"
      + " it will look for Dir2 in the root directory. If Dir2 can not "
      + "be found, the command will fail.";

  /**
   * Returns true if execution of command is successful. Execution creates new
   * directories for each argument passed in the arguments
   * 
   * @param jShell the window that will be printed on
   * @param arguments a list containing the directories to be created
   * @return result the output to the shell
   */
  public Result run(JShellWindow jShell, ArrayList<String> arguments) {

    Result result = areValidArguments(arguments);
    
    // If there were no errors in the arguments then we can run the command
    if (!result.errorOccured()) {

      FileExplorer explorer = jShell.getFileExplorer();
      
      // for each element in arguments, create a new directory
      for (int i = 0; i < arguments.size(); i++) {
        
        String path = arguments.get(i);
  
        try {
          explorer.addDirectory(path);
        } catch (FileNotFoundException e) {
          result.logError(i, "The path does not exist.");
        } catch (PathExistsException e) {
          result.logError(i, "A directory already exists at that path.");
        }
      }
    }
    
    return result;
  }

  /**
   * Returns the name of this command
   * 
   * @return commandName this is the command's name
   */
  public String getCommandName() {
    // return the command name
    return commandName;
  }

  /**
   * Checks if the arguments given to the command is valid
   * 
   * @param arguments the list of str arguments passed to the command
   * @return isValid true if the command is valid and vice versa
   */
  public Result areValidArguments(ArrayList<String> arguments) {
    
    Result result = new Result(arguments);
    
    if (arguments.size() == 0) {
      // There must be at least one argument (i.e. a directory name) to create
      // a directory
      result.logError("Invalid number of arguments.");
    }
    else {
      for (int i = 0; i < arguments.size(); i++) {
        
        // Gets the name of the directory to be created
        String dirName = arguments.get(i);
        
        // Logs an error if the directory contains an invalid characters
        if (!containsValidCharacters(dirName)) {
          result.logError(i, "Invalid characters in directory name.");
        }
      }
    }
    
    return result;
  }
  
  private boolean containsValidCharacters(String dirName) {
    
    return dirName.matches("[A-Za-z0-9-/]+");
  }

  /**
   * Returns the help text for this command.
   * 
   * @return the help text for this command
   */
  public String getHelpText() {

    return helpText;
  }
  
  public boolean canBeRedirected() {
    
    return true;
  }
}
