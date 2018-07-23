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
  
  private String message = "";
  private String errorMessage = "";
  private boolean errorOccured = false;

  private ArrayList<String> arguments;
 
  public Result() {
    
  }
  
  public Result(ArrayList<String> arguments) {
    
    this.arguments = arguments;
  }
  
  public String getMessage() {
    
    return message;
  }
  
  public String getErrorMessage() {
    
    return errorMessage;
  }
  
  public void addMessage(String message) {
    
    
    if (this.message.equals(""))
      this.message = message;
    else
      this.message += "\n" + message;
    
    //this.message += message + "\n";
  }
  
  public void addMessage(String message, String spacing) {
    
    if (this.message.equals(""))
      this.message += message;
    else
      this.message += message + spacing;
  }
  
  public boolean errorOccured() {
    
    return errorOccured;
  }
  
  public void logError(String errorMessage) {
    
    errorOccured = true;
    
    // If there is already an error registered then we want to add the new one
    // to the next line.
    if (this.errorMessage.equals(""))
      this.errorMessage = errorMessage;
    else
      this.errorMessage += "\n" + errorMessage;
  }
  
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
