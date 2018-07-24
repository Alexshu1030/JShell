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
package commandsystem;

import java.util.ArrayList;

public class Result {
  
  /**
   * message the message being stored by the command
   */
  private String message = "";
  /**
   * errorMessage all of the error messages produced during execution of the
   * command
   */
  private String errorMessage = "";
  /**
   * errorOccured whether an error occurred during execution of the command
   */
  private boolean errorOccured = false;

  /**
   * arguments the arguments used with the command
   */
  private ArrayList<String> arguments;
 
  /**
   * The default constructor.
   */
  public Result() {
    
  }
  
  /**
   * A constructor using the arguments of the command being run
   * 
   * @param arguments the arguments
   */
  public Result(ArrayList<String> arguments) {
    
    this.arguments = arguments;
  }
  
  
  /**
   * Returns the message stored by the command.
   * 
   * @return the message store by the command.
   */
  public String getMessage() {
    
    return message;
  }
  
  /**
   * The error message produced by the command.
   * 
   * @return the error message produced by the command.
   */
  public String getErrorMessage() {
    
    return errorMessage;
  }
  
  /**
   * Adds a message to the current messages followed by a new line
   * 
   * @param message the message
   */
  public void addMessage(String message) {
    
    this.message += message + "\n";
  }
  
  /**
   * Adds a message to the current messages with the spacing string
   * separating them.
   * 
   * @param message the message
   * @param spacing the spacing in between the messages
   */
  public void addMessage(String message, String spacing) {
    
    if (this.message.equals(""))
      this.message += message;
    else
      this.message += spacing + message;
  }
  
  /**
   * Returns whether an error occurred.
   * 
   * @return whether an error occurred.
   */
  public boolean errorOccured() {
    
    return errorOccured;
  }
  
  /**
   * Logs the specified error setting errorOccurred to true.
   * 
   * @param errorMessage the error message
   */
  public void logError(String errorMessage) {
    
    errorOccured = true;
    
    // If there is already an error registered then we want to add the new one
    // to the next line.
    if (this.errorMessage.equals(""))
      this.errorMessage = errorMessage;
    else
      this.errorMessage += "\n" + errorMessage;
  }
  
  /**
   * Logs the specified error setting errorOccurred to true. It also adds
   * some information at the end of the error about the specified argument.
   * 
   * @param errorMessage the error message
   */
  public void logError(int argument, String errorMessage) {
    
    errorOccured = true;
    
    // Add the argument to the end of the error so the user can see which on it
    // applies to.
    errorMessage += " (Argument " + argument + ": " +
    arguments.get(argument) + ")";
    // If there is already an error registered then we want to add the new one
    // to the next line.
    if (this.errorMessage.equals(""))
      this.errorMessage = errorMessage;
    else
      this.errorMessage += "\n" + errorMessage;
  }
  
  /**
   * Adds the errors from the specified result class to this class.
   * 
   * @param result the result class to add the errors from.
   */
  public void addErrors(Result result) {

    // If either an error has already occurred in this class or in the class
    // we are combining with then this should be true.
    errorOccured = errorOccured || result.errorOccured;
    
    if (this.errorMessage.equals(""))
      errorMessage = result.errorMessage;
    else
      errorMessage += "\n" + result.errorMessage;
  }
}
