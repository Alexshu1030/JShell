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

import java.util.ArrayList;
import exceptions.*;

public class Directory extends File {
  // make a new arraylist of files
  /**
   * listOfFiles a list of files in the directory
   */
  ArrayList<File> listOfFiles = new ArrayList<File>();

  // construct a new directory with its contents as the list of files
  public Directory(String name, Directory parentDirectory) {
    super(name, parentDirectory, null);
    this.fileContents = listOfFiles;
  }


  /**
   * Adds the file into this directory.
   * 
   * @param file this is the file that will be added to the directory
   */
  public void addFile(File file) {
    listOfFiles.add(file);
  }


  /**
   * Removes the file from this directory
   * 
   * @param file this is the file to be removed from the directory
   */
  public void removeFile(File file) {
    listOfFiles.remove(file);
  }

  /**
   * Finds and returns the file with the specified name.
   * 
   * @param fileName the name of the file you are looking for
   * @return Returns the file with the specified name if found. Otherwise it
   *         returns null.
   * @throws FileNotFoundException 
   */
  public File getFile(String fileName) throws FileNotFoundException {
    // iterate through list of files to find the destination file
    int index = 0;
    // set the file to be returned to be null
    File nextFile = null;
    // Iterate over the current directory looking for the file with the name
    // of the next directory
    while (index < listOfFiles.size() && nextFile == null) {

      // Get the name of the current file we are looking at
      String nextFileName = listOfFiles.get(index).getFileName();

      // If we have found the next directory exit the loop. Otherwise go to
      // the next file in the current directory.
      if (fileName.equals(nextFileName))
        nextFile = listOfFiles.get(index);
      else
        index++;
    }
    
    if (nextFile == null)
      throw new FileNotFoundException();
    
    // return the found file, or null if nothing is found
    return nextFile;
  }

  /**
   * Returns whether or not this is a directory
   * 
   * @return true
   */
  public boolean isDirectory() {
    return true;
  }

  /**
   * Returns whether or not this is the root directory.
   * 
   * @return result true if this is the root directory and false if it is not
   */
  public boolean isRootDirectory() {

    boolean result = (fileDirectory == null);
    return result;
  }
}
