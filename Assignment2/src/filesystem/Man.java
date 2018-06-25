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

package filesystem;
public class Man {

  public Man(String cmd) {
    // Print documentation for 'exit' command
    if (cmd == "exit") {
      System.out.println("NAME:");
      System.out.println("  exit - Terminates the current JShell"
          + " process.");
      System.out.println("DESCRIPTION:");
      System.out.println("  Prevents any other code in JShell from"
          + " being run by reaching the exit condition in"
          + " the while loop. The exit condition is if command 'exit'"
          + " is entered by user");
      System.out.println("PARAMETERS:");
      System.out.println("  There are no parameters available for"
          + " this command.");
      System.out.println("RETURNS:");
      System.out.println("  This command does not return anything.");
      System.out.println("EXAMPLE USAGE:");
      System.out.println("  /#: exit");
      System.out.println("    will successfully terminate JShell.");
      System.out.println("  /#: exit p");
      System.out.println("    will not terminate JShell because of the"
          + " additional parameter 'p'.");
      System.out.println("  /#:              exit");
      System.out.println("    will terminate JShell because"
          + " JShell will ignore excess spaces.");
    }
    // Print documentation for 'mkdir' command
    else if (cmd == "mkdir") {
      System.out.println("NAME:");
      System.out.println("  mkdir DIR1 [DIR2] ... [PATH] -"
          + " Create directories");
      System.out.println("DESCRIPTION:");
      System.out.println("  Creates a directory named in the first parameter"
          + " in the location of the directory given in the optional second"
          + " parameter. If no second parameter is given, the directory is"
          + " created in the current directory.");
      System.out.println("PARAMETERS:");
      System.out.println("  DIR1 - The name of the directory. The only valid"
          + " characters for the name are from a-z, A-Z, 0-9.");
      System.out.println("  DIR2 - The name of a second directory."
          + " An optional parameter.");
      System.out.println("  [PATH] - The path that the user wants the "
          + "directory(ies) to be created in. The path may be a relative"
          + " path or a full path. An optional parameter.);
      System.out.println("RETURNS:");
      System.out.println("  This command does not return anything.");
      System.out.println("EXAMPLE USAGE:");
      System.out.println("  /#: mkdir Dir1");
      System.out.println("    will create a directory named Dir1 in the"
          + " current directory.");
      System.out.println("  /#: mkdir Dir1 Dir2 Dir3");
      System.out.println("    will create a directory named Dir1, "
          + "a directory named Dir2, a directory named Dir3 in the"
          + " current directory.");
      System.out.println("  /#: mkdir Dir1 /Dir2/");
      System.out.println("    will create a directory named Dir1 inside the"
          + " directory named Dir2 that is located in the current"
          + " directory.");
      System.out.println(" If Dir2 does not exist in the current directory,"
          + " it will look for Dir2 in the root directory. If Dir2 can not "
          + "be found, the command will fail.");
    }
    else if (cmd == "cd") {
      System.out.println("NAME:");
      System.out.println("  cd DIR - Change the current directory");
      System.out.println("DESCRIPTION:");
      System.out.println("  Changes the current directory to DIR.");
      System.out.println("PARAMETERS:");
      System.out.println("  DIR - The directory to be changed to."
          + " It may be a full path or a relative path.");
      System.out.println("RETURNS:");
      System.out.println("  This command does not return anything.");
      System.out.println("EXAMPLE USAGE:");
      System.out.println("  /#: cd /Dir1");
      System.out.println("    will change the current directory to Dir1"
          + " in the root directory.");
      System.out.println("  /#: cd ./Dir1/Dir2");
      System.out.println("    will change the current directory to Dir2, "
          + " which is located in Dir1 which is located in the current"
          + "directory.");
      System.out.println("  /#: cd ..");
      System.out.println("    will change the current directory to its"
          + "parent directory.");
    }
    else if (cmd == "ls") {
      System.out.println("NAME:");
      System.out.println("  ls [PATH ...]"
          + " - Lists files and directories");
      System.out.println("DESCRIPTION:");
      System.out.println(" Recursively lists all files and directories"
          + " inside the given paths, or inside the current directory"
          + " if no directory is given.");
      System.out.println("PARAMETERS:");
      System.out.println("  PATH - The relative or full path that the user"
          + " wants to perform ls on.");
      System.out.println("RETURNS:");
      System.out.println("  This command returns all files and directories"
          + " inside.");
      System.out.println("EXAMPLE USAGE:");
      System.out.println("  /#: ls");
      System.out.println("    will recursively return all files and"
          + "directories in the current directory.");
      System.out.println("  /#: ls /PATH/");
      System.out.println("    will recursively return all files and"
          + "directories in the directory /PATH/.");
      System.out.println("  /#: ls /PATH1/ /PATH2/");
          System.out.println("    will recursively return all files and"
              + "directories in the directory /PATH1/ and /PATH2/.");
    }
    else if (cmd == "pwd") {
      System.out.println("NAME:");
      System.out.println("  pwd - Prints current working directory");
      System.out.println("DESCRIPTION:");
      System.out.println("  Prints the full path of the current working"
          + "directory.");
      System.out.println("PARAMETERS:");
      System.out.println("  There are no parameters available for"
          + " this command.");
      System.out.println("RETURNS:");
      System.out.println("  This command does not return anything.");
      System.out.println("EXAMPLE USAGE:");
      System.out.println("  /#: exit");
      System.out.println("    will successfully terminate JShell.");
      System.out.println("  /#: exit p");
      System.out.println("    will not terminate JShell because of the"
          + " additional parameter 'p'.");
      System.out.println("  /#:              exit");
      System.out.println("    will terminate JShell because"
          + " JShell will ignore excess spaces.");
    }
    else if (cmd = "pushd") {
      
    }
    else if (cmd = "popd") {
      
    }
    else if (cmd = "history") {
      
    }
    else if (cmd = "cat") {
      
    }
    else if (cmd = "echo") {
      
    }
    else if (cmd = "man") {
      
    }
    else if (cmd = "find") {
      
    }
    else if (cmd = "tree") {
      
    }
  }
  
  
}
