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

public class FileExplorer {

  private static Directory rootDirectory;
  
  private Directory workingDirectory;
  
  // Default constructor
  public FileExplorer() {
    
    //root = new Directory();
  }
  
  public static Directory getRootDirectory() {
    
    return rootDirectory;
  }
  
  public Directory getWorkingDirectory() {
    
    return workingDirectory;
  }
  
  public void setWorkingDirectory(Directory newWD) {
    
    workingDirectory = newWD;
  }
  
  // Returns the file at the given path
  public File getFile(String path) {
    
    Directory dir = getDirectory(path);
    String fileName = Path.getFileName(path);
    File file = dir.getFile(fileName);
    
    return file;
  }
  
  public Directory getDirectory(String path) {
    
    String dirPath = Path.removeFileName(path);

    Directory rootDir;
    
    if (Path.isAbsolute(path))
      rootDir = rootDirectory;
    else
      rootDir = workingDirectory;
    
    return getDirectoryHelper(rootDir, dirPath);
  }
  
  private Directory getDirectoryHelper(Directory curDir, String relPath) {
    
    Directory dir;
    
    if (relPath.equals("")) {
      dir = curDir;
    }
    else {
      
      String nextDirName = Path.getRootDirectory(relPath);
      Directory nextDir = (Directory)curDir.getFile(nextDirName);
      
      if (nextDir != null) {
        String newRelPath = Path.getSubPath(relPath);
        dir = getDirectoryHelper(nextDir, newRelPath);
      }
      else {
        dir = null;
      }
    }
    
    return dir;
  }

  
  // Adds the file at the given path
  public void addFile(String path, File file) {
    
    Directory dir = getDirectory(path);
    dir.addFile(file);
  }
  
  // Returns whether that path exists in the file explorer
  public boolean pathExists(String path) {
    
    File file = getFile(path);
    
    return file != null;
  }
}
