package commands;

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
  }
  
  public void addMessage(String message, String spacing) {
    
    if (this.message.equals(""))
      this.message = message;
    else
      this.message += spacing + message;
  }
  
  public boolean errorOccured() {
    
    return errorOccured;
  }
  
  public void registerError(String errorMessage) {
    
    errorOccured = true;
    
    // If there is already an error registered then we want to add the new one
    // to the next line.
    if (this.errorMessage.equals(""))
      this.errorMessage = errorMessage;
    else
      this.errorMessage += "\n" + errorMessage;
  }
  
  public void registerError(int argument, String errorMessage) {
    
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
}
