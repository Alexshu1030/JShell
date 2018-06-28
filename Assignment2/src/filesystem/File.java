package filesystem;
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
  
  private void setFileName(String name) {
    this.fileName = name;
  }
  private void setFileDirectory(Directory directory) {
    this.fileDirectory = directory;
  }
  private void setFileContents(Object contents) {
    this.fileContents = contents;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public Directory getFileDirectory() {
    return this.fileDirectory;
  }
  public Object getFileContents() {
    return this.fileContents;
  }
  public boolean isDirectory() {
    return false; 
  }
  
  public String GetFullPath() {
    
    return "RECURSIVELYGETPREVPATHHERE/" + this.getFileName();
  }
}
