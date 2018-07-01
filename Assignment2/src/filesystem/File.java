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
public class File {
  protected String fileName;
  protected Directory fileDirectory;
  protected Object fileContents;
  public File(String name, Directory directory, Object contents) {
    // Constructor for file class
    setFileName(name);
    setFileDirectory(directory);
    setFileContents(contents);
  }
  
  /**
   * Sets the name of this file
   * @param name this is the new name of the file
   */
  public void setFileName(String name) {
    this.fileName = name;
  }
  
  /**
   * Sets the directory that this file is in
   * @param directory the new directory the file is in
   */
  public void setFileDirectory(Directory directory) {
    this.fileDirectory = directory;
  }
  
  /**
   * Sets the contents of the file
   * @param contents the new contents of the file
   */
  public void setFileContents(Object contents) {
    this.fileContents = contents;
  }
  
  /**
   * Gets the name of this file
   * @return returns the name of this file
   */
  public String getFileName() {
    return this.fileName;
  }
  
  /**
   * Gets the directory that this file is in
   * @return Returns the directory this file is in
   */
  public Directory getFileDirectory() {
    return this.fileDirectory;
  }
  
  /**
   * Gets the contents of this file
   * @return the contents of this file
   */
  public Object getFileContents() {
    return this.fileContents;
  }
  
  /**
   * Returns whether or not this file is a directory
   * @return true if this file is a directory and false if it is not
   */
  public boolean isDirectory() {
    return false; 
  }
  
  /**
   * Returns the path to this file from the root directory
   * @return returns the path to this file from the root directory
   */
  public String getFullPath() {
    
    String path = "/";
    
    if (this.getFileDirectory() != null)
      path = getFullPathHelper(this);
      
    return path;
  }
  
  private String getFullPathHelper(File file) {
    
    String path = file.getFileName();
    
    if (file.getFileDirectory() != null) {    
      path = getFullPathHelper(file.getFileDirectory()) + "/" + path;
    }

    return path;
  }
}
