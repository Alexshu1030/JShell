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

  // This is static so multiple instances of the FileExplorer will have
  // access to the same directory.
  private static Directory rootDirectory = new Directory("", null);
  
  // This is not static because each FileExplorer should be able to have a
  // different working directory.
  private Directory workingDirectory;
  
  public FileExplorer() {
    
    setWorkingDirectory(rootDirectory);
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
  
  // Returns the file at the given path.
  public File getFile(String path) {
    
    // Get the directory the file is in
    Directory dir = getParentDirectory(path);
    // Get the name of the file and get it from the directory.
    String fileName = Path.getFileName(path);
    File file = dir.getFile(fileName);
    
    return file;
  }
  
  public Directory getParentDirectory(String path) {
    
    // Get the path of the directory that the file is in
    String dirPath = Path.removeFileName(path);

    Directory rootDir;
    
    if (Path.isAbsolute(path))
      // The path is absolute so we want to start at the root directory and
      // work our way to the file.
      rootDir = rootDirectory;
    else
      // The path is relative so we want to start in the workign directory
      // and work our way to the file.
      rootDir = workingDirectory;
    
    // Use the helper method.
    return getDirectoryHelper(rootDir, dirPath);
  }
  
  private Directory getDirectoryHelper(Directory curDir, String relPath) {
    
    Directory dir;
    
    if (relPath.equals("")) {
      // Base case. We are at the end of the relative path. The current
      // The current directory that we are in is the one we are looking for.
      dir = curDir;
    }
    else {
      
      // Get the next directory we want to look in
      String nextDirName = Path.getRootDirectory(relPath);
      Directory nextDir = (Directory)curDir.getFile(nextDirName);
      
      if (nextDir != null) {
        // We now want to get the path relative to this directory. And then
        // run the method again on that path with that directory.
        String newRelPath = Path.getSubPath(relPath);
        dir = getDirectoryHelper(nextDir, newRelPath);
      }
      else {
        // The path doesn't exit. Return null.
        dir = null;
      }
    }
    
    return dir;
  }

  
  // Adds the file at the given path
  public void addFile(String path, File file) {
    
    // Get the parent directory path and then add the file into that
    // directory.
    Directory dir = getParentDirectory(path);
    dir.addFile(file);
  }
  
  // Returns whether that path exists in the file explorer
  public boolean pathExists(String path) {
    
    //Get the file and check that it isn't null.
    File file = getFile(path);
    
    return file != null;
  }
}
