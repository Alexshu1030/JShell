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
  private static Directory rootDirectory = new Directory("", null);
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

  /**
   * Gets the file at the specified path
   * 
   * @param path the path of the file
   * @return the file at that path
   */
  public File getFile(String path) throws FileNotFoundException{

    // Get the directory the file is in
    Directory dir = getParentDirectory(path);
    File file = null;

    if (dir != null) {
      // Get the name of the file and get it from the directory.
      String fileName = Path.getFileName(path);
      file = dir.getFile(fileName);
    }

    return file;
  }

  /**
   * Gets the directory at the specified path
   * 
   * @param path the path of the directory
   * @return the directory at that path
   */
  public Directory getDirectory(String path) throws FileNotFoundException{

    File file = getFile(path);
    Directory dir = null;

    if (file != null && file.isDirectory()) {
      dir = (Directory) file;
    }

    return dir;
  }

  /**
   * Returns the directory that the file at the specified path is in.
   * 
   * @param path the path of the file
   * @return the directory that the file is in
   */
  public Directory getParentDirectory(String path)
      throws FileNotFoundException{

    // Get the path of the directory that the file is in
    // Note that at this point the file may not actually exist but we will
    // still return the directory that it would be contained in as if it did.
    String dirPath = Path.removeFileName(path);

    // Stores the root directory of this path
    Directory rootDir;

    if (Path.isAbsolute(path)) {
      // The path is absolute so we want to start at the root directory and
      // work our way to the file.
      rootDir = rootDirectory;
      // Make the path relative to the root directory. If the path is of
      // length 0, then it is the root directory.
      if (dirPath.length() > 0)
        dirPath = dirPath.substring(1);
    } else {
      // The path is relative so we want to start in the working directory
      // and work our way to the file.
      rootDir = workingDirectory;
    }

    // Use the helper method.
    return getDirectoryHelper(rootDir, dirPath);
  }

  private Directory getDirectoryHelper(Directory curDir, String relPath) 
      throws FileNotFoundException{

    Directory dir;

    if (relPath.equals("")) {
      // Base case. We are at the end of the relative path. The current
      // The current directory that we are in is the one we are looking for.
      dir = curDir;
    } else {

      // Get the next directory we want to look in
      String nextDirName = Path.getRootDirectory(relPath);
      Directory nextDir = (Directory) curDir.getFile(nextDirName);

      if (nextDir != null) {
        // We now want to get the path relative to this directory. And then
        // run the method again on that path with that directory.
        String newRelPath = Path.getSubPath(relPath);
        dir = getDirectoryHelper(nextDir, newRelPath);
      } else {
        // The path doesn't exit. Return null.
        dir = null;
      }
    }

    return dir;
  }

  public File getFileNEW(Directory currentDirectory, String relativePath)
      throws FileNotFoundException {

    File dir;

    if (relativePath.equals("")) {
      // We are at the end of the path. The directory we are in is the file we
      // are looking for.
      dir = currentDirectory;
    } else {

      // Get the next directory we want to look in
      String nextDirName = Path.getRootDirectory(relativePath);
      Directory nextDir = (Directory) currentDirectory.getFile(nextDirName);
      // We now want to get the path relative to this directory. And then
      // run the method again on that path with that directory.
      String newRelPath = Path.getSubPath(relativePath);
      dir = getDirectoryHelper(nextDir, newRelPath);
    }

    return dir;
  }

  /**
   * Adds the file into the FileExplorer at the specified path
   * 
   * @param dirPath the path to the directory you want to add the file in
   * @param file the file being added to the FileExplorer
   */
  public void addFile(String dirPath, File file) throws FileNotFoundException{

    // The full path of the file
    String path = dirPath + "/" + file.getFileName();
    // Makes sure there isn't already a file with the same name at that path
    if (!pathExists(path)) {
      // Get the parent directory path and then add the file into that
      // directory.
      Directory dir = getParentDirectory(path);
      dir.addFile(file);
    }
  }

  /**
   * Adds the directory into the FileExplorer at the specified path
   * 
   * @param path the path to the directory
   */
  public void addDirectory(String path) throws FileNotFoundException{

    String dirName = Path.getFileName(path);
    String dirPath = Path.removeFileName(path);
    // Makes sure there isn't already a directory with the same name at that
    // path
    if (!pathExists(path)) {
      // Get the parent directory path and then add the new directory into it
      Directory parentDir = getParentDirectory(path);
      // Check if the parent directory exists.
      if (parentDir != null) {
        Directory childDir = new Directory(dirName, parentDir);
        parentDir.addFile(childDir);
      }
    }
  }

  /**
   * Returns whether the specified path exists
   * 
   * @param path the path you want to check exists
   * @return is true if the path exists and false otherwise
   */
  public boolean pathExists(String path) {

    boolean exists = true;
    
    try {
      // Attempt to get the file at the path
      File file = getFile(path);
    }
    catch (FileNotFoundException e) {
      // The file was not found so it doesn't exist
      exists = false;
    }

    return exists;
  }
}
