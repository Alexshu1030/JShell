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

import exceptions.*;

public class FileExplorer {

  // This is static so multiple instances of the FileExplorer will have
  // access to the same file system .
  private static Directory rootDirectory = new Directory("");
  /**
   * workingDirectory a Directory object that is currently used
   */
  private Directory workingDirectory;

  public FileExplorer() {

    setWorkingDirectory(rootDirectory);
  }

  /**
   * Gets the root directory of the FileExplorer
   * 
   * @return returns the root directory
   */
  public static Directory getRootDirectory() {

    return rootDirectory;
  }

  /**
   * Gets the working directory for this FileExplorer
   * 
   * @return returns the working directory
   */
  public Directory getWorkingDirectory() {

    return workingDirectory;
  }

  /**
   * Sets the working directory for this FileExplorer
   * 
   * @param newWD the new working directory
   */
  public void setWorkingDirectory(Directory newWD) {

    workingDirectory = newWD;
  }
  
  public File getFile(String path) throws FileNotFoundException {
    
    Directory rootDir;

    if (Path.isAbsolute(path)) {
      // The path is absolute so we want to start at the root directory and
      // work our way to the file.
      rootDir = rootDirectory;
      // Make the path relative to the root directory. If the path is of
      // length 0, then it is the root directory.
      if (path.length() > 0)
        path = path.substring(1);
    } else {
      // The path is relative so we want to start in the working directory
      // and work our way to the file.
      rootDir = workingDirectory;
    }

    // Use the helper method.
    return getFileHelper(rootDir, path);
  }

  private File getFileHelper(File file, String path)
      throws FileNotFoundException {
    
    if (path.equals("")) {
      // We have reached the end of the path. The current file is the one we
      // are looking for.
      return file;
    }
    else if (file.isDirectory()) {
      
      // We are not at the end of the path. As such, we must be in a directory
      // as a non directory can not contain a file.
      Directory dir = (Directory) file;
      
      // Get the next directory in the path
      String nextDirName = Path.getRootDirectory(path);
      Directory nextDir = dir.getDirectory(nextDirName);
      
      // Get the path relative to nextDir
      String relPath = Path.getSubPath(path);
      
      // Rerun the method on this directory using the path relative to it
      return getFileHelper(nextDir, relPath);
    }
    else {
      // The file is not a directory and we are not at the end of the path. The
      // path does not exist.
      throw new FileNotFoundException();
    }

  }

  public Directory getDirectory(String path) throws FileNotFoundException {
    
    File file = getFile(path);
    
    if (file.isDirectory())
      return (Directory)file;
    else
      throw new FileNotFoundException();
  }

  public void addFile(String dirPath, File file) throws InvalidPathException, 
  PathExistsException {

    try {
      Directory parentDir = getDirectory(dirPath);
      parentDir.addFile(file);
    }
    catch (FileNotFoundException e) {
      throw new InvalidPathException();
    }
  }

  /*
   * This method is just a specialized case of the addFile method. It allows
   * you to just pass in the path and it will create the directory with that
   * path.
   */
  public void addDirectory(String path) throws InvalidPathException,
  PathExistsException {
    
    String dirName = Path.getFileName(path);
    String parentDirPath = Path.removeFileName(path);
    addFile(parentDirPath, new Directory(dirName));
  }
}
